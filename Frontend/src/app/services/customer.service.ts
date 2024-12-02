import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  add(data: any) {
    return this.httpClient.post(this.url + "/customer/add", data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  getCustomers() {
    return this.httpClient.get(this.url + "/customer/get");
  }

  getCustomerByPhoneNumber(phoneNumber: any) {
    return this.httpClient.get(this.url + "/customer/getByPhoneNumber/" + phoneNumber);
  }
}
