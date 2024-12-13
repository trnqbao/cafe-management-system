import { Injectable } from "@angular/core";

export interface Menu {
    state: string;
    name: string;
    type: string;
    icon: string;
    role: string;
}

const MENU_ITEMS = [
    { state: 'dashboard', name: 'Dashboard', type: 'link', icon: 'dashboard', role: '' },
    { state: 'category', name: 'Manage Category', type: 'link', icon: 'category', role: 'ADMIN' },
    { state: 'product', name: 'Manage Product', type: 'link', icon: 'inventory', role: 'ADMIN' },
    { state: 'order', name: 'Manage Order', type: 'link', icon: 'shopping_cart', role: '' },
    { state: 'bill', name: 'Manage Bill', type: 'link', icon: 'backup_table', role: '' },
    { state: 'user', name: 'Manage User', type: 'link', icon: 'people', role: 'ADMIN' }, 
    { state: 'customer', name: 'Manage Customer', type: 'link', icon: 'people_outline', role: '' },
    { state: 'revenue', name: 'Accounting Report', type: 'link', icon: 'bar_chart', role: 'ADMIN' },
    { state: 'forecast', name: 'Financial Forecasting', type: 'link', icon: 'insert_chart', role: 'ADMIN' },
    { state: 'dailyRevenue', name: 'Report', type: 'link', icon: 'bar_chart', role: 'USER' }
]
@Injectable()
export class MenuItems {
    getMenuItem(): Menu[] {
        return MENU_ITEMS;
    }
}
