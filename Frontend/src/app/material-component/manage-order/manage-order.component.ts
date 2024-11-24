import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { RevenueService } from 'src/app/services/revenue.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstant } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-manage-order',
  templateUrl: './manage-order.component.html',
  styleUrls: ['./manage-order.component.scss']
})
export class ManageOrderComponent implements OnInit {

  displayedColumns: string[] = ['name', 'category', 'price', 'quantity', 'total', 'edit'];
  dataSource: any = [];
  manageOrderForm: any = FormGroup;
  categories: any = [];
  products: any = [];
  price: any;
  totalAmount: number = 0;
  responseMessage: any;

  constructor(private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private snackbarService: SnackbarService,
    private billService: BillService,
    private revenueService: RevenueService,
    private ngxService: NgxUiLoaderService
  ) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.getCategories();
    this.manageOrderForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstant.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstant.emailRegex)]],
      phoneNumber: [null, [Validators.required, Validators.pattern(GlobalConstant.phoneNumberRegex)]],
      paymentMethod: [null, [Validators.required]],
      product: [null, [Validators.required]],
      category: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
      price: [null, [Validators.required]],
      total: [null, [Validators.required]]
    });
  }

  getCategories() {
    this.categoryService.getFilteredCategories().subscribe((res: any) => {
      this.ngxService.stop();
      this.categories = res;
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstant.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
    })
  }

  getProductsByCategory(value: any) {
    this.productService.getProductByCategory(value.id).subscribe((res: any) => {
      this.products = res;
      this.manageOrderForm.controls['price'].setValue('');
      this.manageOrderForm.controls['quantity'].setValue('');
      this.manageOrderForm.controls['total'].setValue(0);
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

  getProductDetails(value: any) {
    this.productService.getProductById(value.id).subscribe((res: any) => {
      this.price = res.price;
      this.manageOrderForm.controls['price'].setValue(res.price);
      this.manageOrderForm.controls['quantity'].setValue('1');
      this.manageOrderForm.controls['total'].setValue(res.price * 1);
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

  setQuantity(value: any) {
    var temp = this.manageOrderForm.controls['quantity'].value;
    if (temp > 0) {
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['price'].value * this.manageOrderForm.controls['quantity'].value)
    } else if (temp != '') {
      this.manageOrderForm.controls['quantity'].setValue('1');
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['price'].value * this.manageOrderForm.controls['quantity'].value)
    }
  }

  validateProductAdd() {
    if (this.manageOrderForm.controls['total'].value === 0
      || this.manageOrderForm.controls['total'].value === null
      || this.manageOrderForm.controls['quantity'].value <= 0) {
      return true;
    } else {
      return false;
    }
  }

  validateSubmit() {
    if (this.totalAmount === 0 || this.manageOrderForm.controls['name'].value === null
      || this.manageOrderForm.controls['phoneNumber'].value === null ||
      this.manageOrderForm.controls['paymentMethod'].value === null
    ) {
      return true;
    } else {
      return false;
    }
  }

  add_v1() {
    var formData = this.manageOrderForm.value;
    var productName = this.dataSource.find((e: { id: number }) => e.id === formData.product.id);
    if (productName === undefined) {
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource.push({ id: formData.product.id, name: formData.product.name, category: formData.category.name, quantity: formData.quantity, price: formData.price, total: formData.total });
      this.dataSource = [...this.dataSource];
      console.log(this.dataSource);

      for (let i = 0; i < this.dataSource.length; i++) {
        console.log(i + ": " + this.dataSource[i].name)
      }

      
      this.snackbarService.openSnackBar(GlobalConstant.productAdded, "success");
    } else if (this.dataSource.some((item: any) => item.name == productName)) {
        console.log("yes")
    } 
    //   else {
    //   this.snackbarService.openSnackBar(GlobalConstant.productExistError, GlobalConstant.error);
    // }


  }

  add() {
    const formData = this.manageOrderForm.value;
  
    const existingProduct = this.dataSource.find((e: { id: number }) => e.id === formData.product.id);
    // const formatter = new Intl.NumberFormat('vi-VN', {
    //   style: 'currency',
    //   currency: 'VND'
    // });

    if (!existingProduct) {
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource.push({ id: formData.product.id, name: formData.product.name, category: formData.category.name, quantity: formData.quantity, price: formData.price, total: formData.total });
      this.dataSource = [...this.dataSource];
      this.snackbarService.openSnackBar(GlobalConstant.productAdded, "success");
    } else {
 
      existingProduct.quantity = parseInt(formData.quantity, 10) + parseInt(existingProduct.quantity, 10);
      existingProduct.total = existingProduct.quantity * existingProduct.price;
      existingProduct.quantity = existingProduct.quantity.toString();
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource = [...this.dataSource];
      this.snackbarService.openSnackBar(GlobalConstant.productQuantityUpdated, "success");
    }
  }

  handleDeleteAction(value: any, element: any) {
    this.totalAmount = this.totalAmount - element.total;
    this.dataSource.splice(value, 1);
    this.dataSource = [...this.dataSource];
  }

  submitAction() {
    var formData = this.manageOrderForm.value;
    var data = {
      name: formData.name,
      email: formData.email,
      phoneNumber: formData.phoneNumber,
      paymentMethod: formData.paymentMethod,
      totalAmount: this.totalAmount,
      productDetails: JSON.stringify(this.dataSource)
    }

    this.ngxService.start();
    this.billService.generateReport(data).subscribe((res: any) => {
      this.downloadFile(res?.uuid);
      console.log(this.dataSource);
      for (let i = 0; i < this.dataSource.length; i++) {
        var data = {
          productName: this.dataSource[i].name,
          quantity: this.dataSource[i].quantity,
          total: this.dataSource[i].total
        }
        this.revenueService.add(data).subscribe();
      }

      this.manageOrderForm.reset();
      this.dataSource = [];
      this.totalAmount = 0;
    }, (error: any) => {
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstant.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
    })

    
  }

  downloadFile(fileName: string) {
    var data = {
      uuid: fileName
    }

    this.billService.getPdf(data).subscribe((res: any) => {
      saveAs(res, fileName + ".pdf");
      this.ngxService.stop();
    })
  }

}
