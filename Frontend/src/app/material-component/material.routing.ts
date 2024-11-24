import { Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { ManageCategoryComponent } from './manage-category/manage-category.component';
import { RouteGuardService } from '../services/route-guard.service';
import { ManageProductComponent } from './manage-product/manage-product.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ManageBillComponent } from './manage-bill/manage-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';
import { ManageCustomerComponent } from './manage-customer/manage-customer.component';
import { ManageFinancialComponent } from './manage-financial/manage-financial.component';


export const MaterialRoutes: Routes = [
    {
        path: 'category',
        component: ManageCategoryComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN']
        }
    },

    {
        path: 'product',
        component: ManageProductComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN']
        }
    },

    {
        path: 'order',
        component: ManageOrderComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN', 'USER']
        }
    },

    {
        path: 'bill',
        component: ManageBillComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN', 'USER']
        }
    },

    {
        path: 'user',
        component: ManageUserComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN']
        }
    },

    {
        path: 'customer',
        component: ManageCustomerComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN', 'USER']
        }
    },

    {
        path: 'revenue',
        component: ManageFinancialComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['ADMIN']
        }
    }

];
