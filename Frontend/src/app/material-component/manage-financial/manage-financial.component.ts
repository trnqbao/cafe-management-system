import { Component, OnInit } from '@angular/core';
import { RevenueService } from 'src/app/services/revenue.service';
import { Chart, registerables } from 'chart.js'
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { formatDate } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DailyRevenueComponent } from '../dialog/daily-revenue/daily-revenue.component';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';

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
	topDrinks: any[] = [];
	dataSource: any;
	last7days: any;

	constructor(
		private ngxService: NgxUiLoaderService,
		private revenueService: RevenueService,
		private dialog: MatDialog,
		private snackbarService: SnackbarService,
		private router: Router
	) { Chart.register(...registerables); }

	ngOnInit(): void {
		this.ngxService.start();
		this.loadRevenueData();
		this.ngxService.stop();
	}

	loadRevenueData() {
		// const formatedDate = this.selectedDate.toISOString().split('T')[0];
		// const selectedMonth = new Date().getMonth() + 1;
		// const selectedYear = new Date().getFullYear();

		this.revenueService.getDailyRevenue().subscribe((res: any) => {
			this.dailyRevenue = this.getVND(res.revenue);
		});


		this.revenueService.getMonthlyRevenue().subscribe((res: any) => {
			this.monthlyRevenue = this.getVND(res.revenue);
		});

		this.revenueService.getWeeklyRevenue().subscribe((res: any) => {
			this.weeklyRevenueData = res;
			this.loadCharts();
		})

	}

	loadCharts() {
		this.createWeeklyRevenueChart();
		this.createOrderTimeChart();
		// this.createOrderedFrequency();
		this.loadTopOrderedDrinks();
		// this.createOrderedFrequency2();
	}

	loadTopOrderedDrinks() {
		this.revenueService.getProductFrequencyLast7Days().subscribe((res: any) => {
	
			this.topDrinks = res;

			
		})
	}
	createOrderedFrequency() {
		const ctx = document.getElementById('chart1') as HTMLCanvasElement;

		const myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [{
					label: 'Dataset 1',
					data: [12, 19, 3, 5, 2, 3, 9],
					borderColor: 'rgb(255, 99, 132)',
					backgroundColor: 'rgba(255, 99, 132, 0.5)',
					yAxisID: 'y'
				},
				{
					label: 'Dataset 2',
					data: [3, 2, 10, 4, 8, 6, 7],
					borderColor: 'rgb(54, 162, 235)',
					backgroundColor: 'rgba(54, 162, 235, 0.5)',
					yAxisID: 'y1'
				}
				]
			},
			options: {

			}
		});
	}

	createOrderedFrequency2() {
		const ctx = document.getElementById('chart2') as HTMLCanvasElement;

		const myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [{
					label: 'Dataset 1',
					data: [12, 19, 3, 5, 2, 3, 9],
					borderColor: 'rgb(255, 99, 132)',
					backgroundColor: 'rgba(255, 99, 132, 0.5)',
					yAxisID: 'y'
				},
				{
					label: 'Dataset 2',
					data: [3, 2, 10, 4, 8, 6, 7],
					borderColor: 'rgb(54, 162, 235)',
					backgroundColor: 'rgba(54, 162, 235, 0.5)',
					yAxisID: 'y1'
				}
				]
			}
		});
	}

	createWeeklyRevenueChart() {
		const ctx = document.getElementById('weeklyRevenueChart') as HTMLCanvasElement;

		if (!ctx) {
			console.error('Canvas element not found');
			return;
		}

		// Calculate minimum and maximum revenue values for effective fitting
		const revenueValues = this.weeklyRevenueData.slice(0, 8).map(item => item.revenue);
		const minRevenue = Math.min(...revenueValues);
		const maxRevenue = Math.max(...revenueValues);

		const dayNamesWithDates = this.weeklyRevenueData.slice(0, 8).map(item => {
			const dateObject = new Date(item.date);
			return formatDate(dateObject, 'EEE - dd/MM', 'en-US');
		});

		this.last7days = dayNamesWithDates[7];

		const weeklyRevenue = this.weeklyRevenueData[this.weeklyRevenueData.length - 1];

		const weeklyRevenueChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: dayNamesWithDates,
				datasets: [{
					label: 'Total revenue',
					data: revenueValues,
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
						text: `Weekly Revenue (Total: ${this.getVND(weeklyRevenue.revenue)}  -  Avarage: ${this.getVND(weeklyRevenue.avg_revenue)}/day)`
					},
					subtitle: {
						display: true,
						text: 'From ' + dayNamesWithDates[7] + '',
						color: 'blue',
						font: {
							size: 12,
							family: 'tahoma',
							weight: 'normal',
							style: 'italic'
						},
						padding: {
							bottom: 10
						}
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
						stacked: true,
						ticks: {
							stepSize: (maxRevenue - minRevenue) / 9
						},
						suggestedMin: minRevenue + 9000,
						suggestedMax: maxRevenue - 9000
					}
				}
			}
		});
	}

	createOrderTimeChart() {
		const ctx = document.getElementById('orderTimeChart') as HTMLCanvasElement;

		if (!ctx) {
			console.error('Canvas element not found');
			return;
		}
		const orderData = [
			{ label: 'Morning', value: 123 },
			{ label: 'Afternoon', value: 155 },
			{ label: 'Evening', value: 300 }
		];

		const totalOrders = orderData.reduce((acc, item) => acc + item.value, 0);
		// Calculate the percentage for each time slot
		const orderPercentages = orderData.map(item => {
			const percentage = (item.value / totalOrders) * 100;
			return `${item.label} (${percentage.toFixed(2)}%)`;
		});

		const myChart = new Chart(ctx, {
			type: 'doughnut',
			data: {
				labels: orderPercentages,
				datasets: [{
					label: 'Order Time',
					data: orderData.map(item => item.value),
					backgroundColor: [
						'#D1D3E2',
						'#8593ED',
						'#5A6ACF'
					],
					hoverOffset: 4,
					borderWidth: 1,

				}]
			},
			options: {
				maintainAspectRatio: false,
				plugins: {
					legend: {
						position: 'bottom'
					},
					title: {
						display: true,
						text: 'Order Time Distribution',
						font: {
							size: 16
						}
					},
					subtitle: {
						display: true,
						text: 'From ' + this.last7days,
						color: 'blue',
						font: {
							size: 12,
							family: 'tahoma',
							weight: 'normal',
							style: 'italic'
						},
						padding: {
							bottom: 10
						}
					}
				}
			}
		});
	}

	getVND(data: any) {
		return data.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	}

	// handleViewAction(value: any) {
	// 	const dialogConfig = new MatTableDataSource();
	// 	dialogConfig.data = {
	// 		data: value
	// 	};
	// 	dialogConfig.width = '100%';
	// 	const dialogRef = this.dialog.open(DailyRevenueComponent, dialogConfig);
	// 	this.router.events.subscribe(() => {
	// 		dialogRef.close();
	// 	});
	// }
}
