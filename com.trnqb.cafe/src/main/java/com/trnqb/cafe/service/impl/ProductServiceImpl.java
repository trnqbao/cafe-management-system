package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.ProductDTO;
import com.trnqb.cafe.entity.Category;
import com.trnqb.cafe.entity.Product;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.ProductRepository;
import com.trnqb.cafe.service.ProductService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productRepository.save(mapToEntity(requestMap, false));
                    return CafeUtils.getResponseEntity("Product added successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        try {
            return new ResponseEntity<>(productRepository.findAll()
                    .stream().map(product -> mapToDTO(product, new ProductDTO()))
                    .toList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, true)) {
                    Optional<Product> optional = productRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if (optional.isPresent()) {
                        Product product = mapToEntity(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        if (requestMap.get("img") == null) {
                            product.setImg(product.getImg());
                        }

                        productRepository.save(product);
                        return CafeUtils.getResponseEntity("Product has been updated.", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Product Id does not exist.", HttpStatus.OK);
                    }
                } else {
                    return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> optional = productRepository.findById(id);
                if (optional.isPresent()) {
                    productRepository.deleteById(id);
                    return CafeUtils.getResponseEntity("Product has been deleted.", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Product Id does not exist.", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> optional = productRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent()) {
                    productRepository.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("Product Status has been updated.", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Product Id does not exist.", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProductByCategory(Integer id) {
        try {
            return new ResponseEntity<>(productRepository.findAllByCategoryIdAndStatus(id, "true")
                    .stream().map(product -> mapToDTO(product, new ProductDTO()))
                    .toList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Integer id) {
        try {
            return new ResponseEntity<>(productRepository
                    .findById(id).map(product -> mapToDTO(product, new ProductDTO()))
                    .orElseThrow(() -> new RuntimeException("Product not found with ID")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ProductDTO mapToDTO(Product product, ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStatus(product.getStatus());
        productDTO.setCategoryID(product.getCategory().getId());
        productDTO.setCategoryName(product.getCategory().getName());
        productDTO.setImg(product.getImg());
        return productDTO;
    }

    private Product mapToEntity(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        if (requestMap.get("img") == null) {
            product.setImg(CafeConstants.DEFAULT_IMG);
        } else {
            product.setImg(requestMap.get("img"));
        }

        return product;
    }

    private boolean validateProductMap(Map<String, String> requestMap, boolean validateID) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateID) {
                return true;
            } else if (!validateID) {
                return true;
            }
        }
        return false;
    }
}
