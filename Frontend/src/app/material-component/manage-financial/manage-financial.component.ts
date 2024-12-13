import { Component, OnInit } from '@angular/core';
import { RevenueService } from 'src/app/services/revenue.service';
import { Chart, registerables } from 'chart.js';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { formatDate } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DailyRevenueComponent } from '../dialog/daily-revenue/daily-revenue.component';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { BillService } from 'src/app/services/bill.service';

@Component({
	selector: 'app-manage-financial',
	templateUrl: './manage-financial.component.html',
	styleUrls: ['./manage-financial.component.scss'],
})
export class ManageFinancialComponent implements OnInit {
	selectedDate: Date = new Date();
	dailyRevenue: number = 0;
	weeklyRevenueData: any[] = [];
	orderTimeData: any[] = [];
	monthlyRevenue: number = 0;
	last2weeksOrdersData: any[] = [];
	responseMessage: any;
	topDrinks: any[] = [];
	dataSource: any;
	last7days: any;

	constructor(
		private ngxService: NgxUiLoaderService,
		private revenueService: RevenueService,
		private billService: BillService,
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
		this.revenueService.getDailyRevenue().subscribe((res: any) => {
			this.dailyRevenue = this.getVND(res.revenue);
		});

		this.revenueService.getMonthlyRevenue().subscribe((res: any) => {
			this.monthlyRevenue = this.getVND(res.revenue);
		});

		this.revenueService.getWeeklyRevenue().subscribe((res: any) => {
			this.weeklyRevenueData = res;
			this.createWeeklyRevenueChart();
			this.billService.getOrderTimeLast7Days().subscribe((res: any) => {
				this.orderTimeData = res;
				this.createOrderTimeChart();
			});
		});

		

		this.revenueService.getTopDrinksLast7Days().subscribe((res: any) => {
			this.topDrinks = res;
		})

		this.billService.getTotalOrdersLast2Weeks().subscribe((res: any) => {
			this.last2weeksOrdersData = res;
			this.createOrderedFrequency();
		});
	}

	createOrderedFrequency() {
		const ctx = document.getElementById('chart1') as HTMLCanvasElement;

		const middleIndex = Math.floor(this.last2weeksOrdersData.length / 2);
		const firstHalf = this.last2weeksOrdersData.slice(0, middleIndex);
		const secondHalf = this.last2weeksOrdersData.slice(middleIndex);

		const firstHalfDates = firstHalf.map(item => item.date);
		const firstHalfTotals = firstHalf.map(item => item.total);
		const secondHalfDates = secondHalf.map(item => item.date);
		const secondHalfTotals = secondHalf.map(item => item.total);

		const min = Math.min(...firstHalfTotals, ...secondHalfTotals);
		const max = Math.max(...firstHalfTotals, ...secondHalfTotals);

		
		const dateObject1 = formatDate(new Date(firstHalfDates[0]), 'EEE - dd/MM', 'en-US');
		const dateObject2 = formatDate(new Date(secondHalfDates[0]), 'EEE - dd/MM', 'en-US');
		

		const myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: ['', '', '', '', '', '', ''],
				datasets: [{
					label: 'This week',
					data: secondHalfTotals,
					borderColor: 'rgb(255, 99, 132)',
					backgroundColor: 'rgba(255, 99, 132, 0.5)',
					spanGaps: true,
					fill: false,
	
				},
				{
					label: 'Last week',
					data: firstHalfTotals,
					borderColor: 'rgba(100, 193, 255, 0.6)',
					backgroundColor: 'rgba(54, 162, 235, 0.5)',
					spanGaps: true,
					fill: false,

				}
				]
			},
			options: {
				plugins: {
					legend: {
						display: true,
						position: 'bottom'
					}, 
					title: {
						display: true,
						text: `Total daily orders`,
						font: {
							size: 16
						}
					},
					subtitle: {
						display: true,
						text: 'This week from ' + dateObject2 + ' & Last week from ' + dateObject1,
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
					}, 
				},
				scales: {
					y: {
						beginAtZero: true,
						max: max,
						min: min,
						title: {
							display: true,
							text: 'Total Orders Per Day'
						  }
					  }
				}
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
						text: `Weekly Revenue (Total: ${this.getVND(weeklyRevenue.revenue)}  -  Avarage: ${this.getVND(weeklyRevenue.avg_revenue)}/day)`,
						font: {
							size: 16
						}
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

		const orderData = this.orderTimeData.map(item => item.orders);
		const totalOrder = this.orderTimeData.reduce((acc, item) => acc + item.orders, 0);
		const orderPercentages = this.orderTimeData.map(item => {
			const percentage = (item.orders / totalOrder) * 100;
			return `${item.shift} (${percentage.toFixed(2)}%)`;
		});

		const myChart = new Chart(ctx, {
			type: 'doughnut',
			data: {
				labels: orderPercentages,
				datasets: [{
					label: 'Order Time',
					data: orderData,
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

	handleViewAction() {
		
		const dialogRef = this.dialog.open(DailyRevenueComponent, {
		  width: '100%',
		  panelClass: 'full-screen-modal' // Optional: for full-screen modal
		});
	  
		dialogRef.afterClosed().subscribe(result => {
		  // Handle dialog closure
		});
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
