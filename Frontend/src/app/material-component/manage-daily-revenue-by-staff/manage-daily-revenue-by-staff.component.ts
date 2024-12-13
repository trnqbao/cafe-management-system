import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Chart, registerables } from 'chart.js';
import { RevenueService } from 'src/app/services/revenue.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-manage-daily-revenue-by-staff',
  templateUrl: './manage-daily-revenue-by-staff.component.html',
  styleUrls: ['./manage-daily-revenue-by-staff.component.scss']
})
export class ManageDailyRevenueByStaffComponent implements OnInit {

  dailyRevenue: number = 0;
  products: any[] = [];
  dataSource: any;
  responseMessage: any;
  currentDay: any;
  lastDay: any
  
  constructor(
    private ngxService: NgxUiLoaderService,
		private revenueService: RevenueService,
		private dialog: MatDialog,
		private snackbarService: SnackbarService,
		private router: Router
  ) {Chart.register(...registerables); }

  ngOnInit(): void {
    this.ngxService.start();
		this.loadRevenueData();
		this.ngxService.stop();
  }

  loadRevenueData() {
    this.revenueService.getDailyRevenue().subscribe((res: any) => {
      this.dailyRevenue = this.getVND(res.revenue);
      this.currentDay = res.date;
    });

    this.revenueService.getDailyProducts().subscribe((res: any) => {
      this.products = res;
      this.loadChart();
    })
  }

  getVND(data: any) {
		return data.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	}

  loadChart() {
    this.createProductsChart();
  }

  createProductsChart() {
    const cxt = document.getElementById('dailyProductsFrequency') as HTMLCanvasElement;

    if (!cxt) {
      console.error('Canvas alement not found')
      return;
    }

    const frequencyValues = this.products.map(product => product.quantity);
    const minValue = Math.min(...frequencyValues);
    const maxValue = Math.max(...frequencyValues);
    const productsName = this.products.map(item => item.product);
   
    const chart = new Chart(cxt, {
      type: 'bar',
      data: {
        labels: productsName,
        datasets: [{
          label: 'Product',
          data: frequencyValues,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
					borderColor: 'rgba(54, 162, 235, 1)',
					borderWidth: 1,
					barThickness: 'flex',
					barPercentage: 0.5,
					categoryPercentage: 0.9
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: true,
            position: 'bottom'
          },
          title: {
            display: true,
            text: 'The frequency of drinks ordered at ' + this.formatter(this.currentDay),
            font: {
              size: 16
            }
          }
        },
        scales: {
					y: {
						beginAtZero: true,
						title: {
							display: true,
							text: 'Quantity'
						}
					},
					x: {
						stacked: true,
						ticks: {
							stepSize: (maxValue - minValue) / 5
						},
						suggestedMin: minValue + 5000,
						suggestedMax: maxValue - 5000
					}
				}
      }
    })
  }

  formatter(dateString: string): string {
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
  }

}
