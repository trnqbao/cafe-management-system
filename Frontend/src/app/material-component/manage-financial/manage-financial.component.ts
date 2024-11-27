import { Component, OnInit } from '@angular/core';
import { RevenueService } from 'src/app/services/revenue.service';
import { Chart, LinearScale, BarElement, BarController, CategoryScale, Decimation, Filler, Legend, Title, Tooltip } from 'chart.js';
import { NgxUiLoaderService } from 'ngx-ui-loader';

@Component({
  selector: 'app-manage-financial',
  templateUrl: './manage-financial.component.html',
  styleUrls: ['./manage-financial.component.scss'],
})
export class ManageFinancialComponent implements OnInit {
  selectedDate: Date = new Date();
  dailyRevenue: number = 0;

  weeklyRevenueData: any[] = [];
  monthlyRevenue: number = 0;
  responseMessage: any;

  constructor(
    private ngxService: NgxUiLoaderService,
    private revenueService: RevenueService,
  ) {Chart.register(BarElement, LinearScale, BarController, CategoryScale, Decimation, Filler, Legend, Title, Tooltip); }

  ngOnInit(): void {
    this.ngxService.start();
    this.loadRevenueData();
    this.ngxService.stop();
  }

  loadRevenueData() {
    const formatedDate = this.selectedDate.toISOString().split('T')[0];
    const selectedMonth = new Date().getMonth() + 1;
    const selectedYear = new Date().getFullYear();

    this.revenueService.getDailyRevenue().subscribe((res: any) => {
      this.dailyRevenue = res.revenue;
    });

    this.revenueService.getWeeklyRevenue().subscribe((res: any) => {
      this.weeklyRevenueData = res;

      const ctx = document.getElementById('myChart') as HTMLCanvasElement;

      if (!ctx) {
        console.error('Canvas element not found');
        return;
      }

      // Calculate minimum and maximum revenue values for effective fitting
      const revenueValues = this.weeklyRevenueData.slice(0, 7).map(item => item.revenue);
      const minRevenue = Math.min(...revenueValues);
      const maxRevenue = Math.max(...revenueValues);

      const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: this.weeklyRevenueData.slice(0, 7).map(item => item.date),
          datasets: [{
            label: 'Total revenue',
            data: this.weeklyRevenueData.slice(0, 7).map(item => item.revenue),
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1,
            barThickness: 'flex'
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              display: true,
            },
            title: {
              display: true,
              text: `Weekly Revenue`
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: 'Revenue (VND)'
              }
            },
            x: {
              ticks: {
                stepSize: (maxRevenue - minRevenue) / 9
              },
              suggestedMin: minRevenue + 9000,
              suggestedMax: maxRevenue - 9000
            }
          }
        }
      });
    });

    this.revenueService.getMonthlyRevenue().subscribe((res: any) => {
      this.monthlyRevenue = res.revenue;
    });
  }
}
