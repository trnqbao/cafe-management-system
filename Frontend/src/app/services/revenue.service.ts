import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RevenueService {
  

  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  add(data: any) {
    return this.httpClient.post(this.url + "/revenue/add", data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  getDailyRevenue() {
    return this.httpClient.get(this.url + "/revenue/getDailyRevenue")
  }

  getDailyProducts() {
    return this.httpClient.get(this.url + "/revenue/getDailyProducts")
  }

  getWeeklyRevenue() {
    return this.httpClient.get(this.url + "/revenue/getWeeklyRevenue")
  }

  getMonthlyRevenue() {
    return this.httpClient.get(this.url + "/revenue/getMonthlyRevenue")
  }

  getTopDrinksLast7Days() {
	  return this.httpClient.get(this.url + "/revenue/getProductFrequencyLast7Days")
  }
}
