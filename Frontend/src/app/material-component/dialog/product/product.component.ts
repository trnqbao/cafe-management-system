import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstant } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  onAddProduct = new EventEmitter();
  onEditProduct = new EventEmitter();
  productForm: any = FormGroup;
  dialogAction: any = "Add";
  action: any = "Add";
  responseMessage: any;
  categories: any = [];
  selectedImage: any;

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    public dialogRef: MatDialogRef<ProductComponent>,
    private categoryService: CategoryService,
    private snackbarService: SnackbarService) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: [null, [Validators.required]],
      categoryId: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
      img: []
    });

    if (this.dialogData.action === "Edit") {
      this.dialogAction = "Edit";
      this.action = "Update";
      // this.productForm.patchValue(this.dialogData.data);

      this.selectedImage = this.dialogData.data.img;

      this.productForm.patchValue({
        name: this.dialogData.data.name,
        categoryId: this.dialogData.data.categoryID, // Assuming category has an id property
        price: this.dialogData.data.price,
        description: this.dialogData.data.description,
        // img: this.selectedImage
      });

      // console.log(this.dialogData.data.img)

       
    }
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getCategories().subscribe((res: any) => {
      this.categories = res;
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstant.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
    })
  }

  handleSubmit() {
    if (this.dialogAction === "Edit") {
      this.edit();
    } else {
      this.add();
    }
  }

  add() {
    var formData = this.productForm.value;
    console.log(formData)
    var data = {
      name: formData.name,
      categoryId: formData.categoryId,
      price: formData.price,
      description: formData.description,
      img: formData.img
    }

    this.productService.add(data).subscribe((res: any) => {
      this.dialogRef.close();
      this.onAddProduct.emit();
      this.responseMessage = res.message;
      this.snackbarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstant.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
    })
  }

  edit() {
    var formData = this.productForm.value;
    console.log("Update: " + formData)
    var data = {
      id: this.dialogData.data.id,
      name: formData.name,
      categoryId: formData.categoryId,
      price: formData.price,
      description: formData.description,
      img: formData.img
    }

    this.productService.update(data).subscribe((res: any) => {
      this.dialogRef.close();
      this.onEditProduct.emit();
      this.responseMessage = res.message;
      this.snackbarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstant.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
    })
  }

  handleImageUpload(event: any) {
    const reader = new FileReader();
    if (event.target.files && event.target.files[0]) {
      const [file] = event.target.files;
      if (file.type.match('image.*')) {
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.selectedImage = reader.result;
          this.productForm.patchValue({ img: "assets/img/" + this.selectedImage }); // Update form value
          console.log('img: ' + this.productForm.img)
        };
      } else {
        // Handle invalid image format
        console.error('Invalid image format. Please select a jpg, jpeg, or png image.');
        this.selectedImage = null;
      }
    }
  }
}
