import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js'

@Component({
  selector: 'app-manage-forecast',
  templateUrl: './manage-forecast.component.html',
  styleUrls: ['./manage-forecast.component.scss']
})
export class ManageForecastComponent implements OnInit {
  forecastData = [
    { date: '2024-12-14', revenue: 1100000 },
    { date: '2024-12-15', revenue: 1250000 },
    { date: '2024-12-16', revenue: 1150000 },
    { date: '2024-12-17', revenue: 1450000 },
    { date: '2024-12-18', revenue: 1700000 },
    { date: '2024-12-19', revenue: 1965000 },
    { date: '2024-12-20', revenue: 1700000 }
  ];

  topDrinks: { product: string, frequency: number }[] = [
    { product: 'French Press', frequency: 30 },
    { product: 'Cold Brew', frequency: 25 },
    { product: 'Macchiato', frequency: 20 },
    { product: 'Cappuccino', frequency: 18 },
    { product: 'Latte', frequency: 15 }
  ];

  constructor() { Chart.register(...registerables) }

  ngOnInit(): void {
    this.loadCharts();
  }

  loadCharts() {
    this.createForecastChart();
    this.createTopDrinksChart();
  }

  createForecastChart() {
    const ctx = document.getElementById('forecastChart') as HTMLCanvasElement;

    const revenueValues = this.forecastData.map(data => data.revenue);
    const min = Math.min(...revenueValues);
    const max = Math.max(...revenueValues);

    const dayNamesWithDates = this.forecastData.map(item => {
			const dateObject = new Date(item.date);
			return formatDate(dateObject, 'EEE - dd/MM', 'en-US');
		});
    const chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: dayNamesWithDates,
        datasets: [{
          label: 'Projected Revenue (VND)',
          data: this.forecastData.map(data => data.revenue),
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: false,
        
            ticks: {
            
              callback: function (value, index, values) {
                return value.toLocaleString('vi-VN',
                  {
                    style: 'currency', currency: 'VND'
                  });
              }
            }
          }
        }
      }
    });
  }

  createTopDrinksChart() {
    const ctx = document.getElementById('topDrinksChart') as HTMLCanvasElement;

    const chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: this.topDrinks.map(drink => drink.product),
        datasets: [{
          label: 'Frequency',
          data: this.topDrinks.map(drink => drink.frequency),
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1,
          barThickness: 'flex',
          barPercentage: 0.5,
          categoryPercentage: 0.9
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: 'Total ordered'
            }

          }
        }
      }
    });
  }

}
