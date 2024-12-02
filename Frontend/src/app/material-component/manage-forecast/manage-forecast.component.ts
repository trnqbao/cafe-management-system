import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js'

@Component({
  selector: 'app-manage-forecast',
  templateUrl: './manage-forecast.component.html',
  styleUrls: ['./manage-forecast.component.scss']
})
export class ManageForecastComponent implements OnInit {
  forecastData = [
    { date:   
 '2024-12-04', revenue: 1000000 },
    { date: '2024-12-05', revenue: 1200000 },
    // ... more forecast data points
    { date: '2024-12-31', revenue: 2800000 }
  ];

  topDrinks: { product: string, frequency: number }[] = [
    { product: 'French Press', frequency: 30 },
    { product: 'Cold Brew', frequency: 25 },
    { product: 'Macchiato', frequency: 20 },
    { product: 'Cappuccino', frequency: 18 },
    { product: 'Latte', frequency: 15 }
  ];

  constructor() { Chart.register(...registerables)}

  ngOnInit(): void {
    this.loadCharts();
  }

  loadCharts() {
    this.createForecastChart();
    this.createTopDrinksChart();
  }

  createForecastChart() {
    const ctx = document.getElementById('forecastChart') as HTMLCanvasElement;

    const chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.forecastData.map(data => data.date),
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
            beginAtZero: true,
            ticks: {
              callback: function(value, index, values) {
                return value.toLocaleString('vi-VN',   
 { style: 'currency', currency: 'VND'   
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
							text: 'Revenue (VND)'
						}
  
          }
        }
      }
    });
  }

}
