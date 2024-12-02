import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { CategoryService } from 'src/app/services/category.service';
import { CustomerService } from 'src/app/services/customer.service';
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
  customerInfo = {
    phoneNumber: '',
    name: '',
    email: '',
    point: 0,
    discount: ''
    // ... other fields
  };
  phoneNumber: string = '';
  selectedDiscount: string = '';
  discounts: { value: string; label: string }[] = [];
  

  constructor(private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private snackbarService: SnackbarService,
    private billService: BillService,
    private revenueService: RevenueService,
    private customerService: CustomerService,
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
      point: [null],
      discount: [null],
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

  

  add() {
    const formData = this.manageOrderForm.value;

    const existingProduct = this.dataSource.find((e: { id: number }) => e.id === formData.product.id);

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
      let total_quantity: number = 0;
      for (let i = 0; i < this.dataSource.length; i++) {
        var product_data = {
          productName: this.dataSource[i].name,
          quantity: this.dataSource[i].quantity,
          total: this.dataSource[i].total
        }
        total_quantity += Number(product_data.quantity);
        this.revenueService.add(product_data).subscribe();
      }


      if (data.name && data.email  && data.phoneNumber) {
        var customer_data = {
          name: formData.name,
          email: formData.email,
          phoneNumber: formData.phoneNumber,
          point: total_quantity,
          discount: formData.discount
        }
        this.customerService.add(customer_data).subscribe();
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

  onPhoneNumberChange() {
    if (this.phoneNumber.length !== 10) {
      return;
    }
    
    if (!this.customerInfo.name && !this.customerInfo.email) {
      this.customerService.getCustomerByPhoneNumber(this.phoneNumber).subscribe((res: any) => {
        this.customerInfo = res;
        console.log(this.customerInfo)
        if (this.customerInfo.point >= 5 && this.customerInfo.point <= 10) {
          this.discounts.push({ value: '5', label: 'Free 1 Cafe' });
        } else if (this.customerInfo.point >= 10) {
          this.discounts.push({ value: '10', label: 'Free 1 Tea/Juice' });
        }
      })
    }

  }

  onNameChange() {
    this.updateDiscountsAndPoint();
  }

  onEmailChange() {
    this.updateDiscountsAndPoint();
  }


  updateDiscountsAndPoint() {
    this.customerInfo.point = 0;
    this.discounts = [];
  }

}
