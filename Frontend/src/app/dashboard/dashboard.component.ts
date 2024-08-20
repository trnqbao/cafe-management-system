import { Component, AfterViewInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';
import { NgxUiLoaderRouterModule, NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstant } from '../shared/global-constants';
@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit {

	responseMessage: any;
	data: any;

	ngAfterViewInit() { }

	constructor(private dashboardService: DashboardService,
		private ngxService: NgxUiLoaderService,
		private snackbarService: SnackbarService
	) {
		this.ngxService.start();
		this.dashboardData();
	}

	dashboardData() {
		this.dashboardService.getDetails().subscribe((res: any) => {
			this.ngxService.stop();
			this.data = res;
		}, (err: any) => {
			this.ngxService.stop();
			console.log(err);
			if (err.error?.message) {
				this.responseMessage = err.error?.message;
			} else {
				this.responseMessage = GlobalConstant.genericError;
			}
			this.snackbarService.openSnackBar(this.responseMessage, GlobalConstant.error);
		})
	}

}
