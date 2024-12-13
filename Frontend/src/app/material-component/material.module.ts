import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialRoutes } from './material.routing';
import { MaterialModule } from '../shared/material-module';
import { ViewBillProductsComponent } from './dialog/view-bill-products/view-bill-products.component';
import { ConfirmationComponent } from './dialog/confirmation/confirmation.component';
import { ChangePasswordComponent } from './dialog/change-password/change-password.component';
import { ManageCategoryComponent } from './manage-category/manage-category.component';
import { CategoryComponent } from './dialog/category/category.component';
import { ManageProductComponent } from './manage-product/manage-product.component';
import { ProductComponent } from './dialog/product/product.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ManageBillComponent } from './manage-bill/manage-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';
import { ManageCustomerComponent } from './manage-customer/manage-customer.component';
import { ManageFinancialComponent } from './manage-financial/manage-financial.component';
import { DailyRevenueComponent } from './dialog/daily-revenue/daily-revenue.component';
import { ManageForecastComponent } from './manage-forecast/manage-forecast.component';
import { ManageDailyRevenueByStaffComponent } from './manage-daily-revenue-by-staff/manage-daily-revenue-by-staff.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MaterialRoutes),
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule
  ],
  providers: [],
  declarations: [
    ViewBillProductsComponent,
    ConfirmationComponent,
    ChangePasswordComponent,
    ManageCategoryComponent,
    CategoryComponent,
    ManageProductComponent,
    ProductComponent,
    ManageOrderComponent,
    ManageBillComponent,
    ManageUserComponent,
    ManageCustomerComponent,
    ManageFinancialComponent,
    DailyRevenueComponent,
    ManageForecastComponent,
    ManageDailyRevenueByStaffComponent
  ]
})
export class MaterialComponentsModule { }
