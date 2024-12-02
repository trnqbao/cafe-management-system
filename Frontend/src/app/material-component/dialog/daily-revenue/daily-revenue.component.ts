import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ManageFinancialComponent } from '../../manage-financial/manage-financial.component';

@Component({
  selector: 'app-daily-revenue',
  templateUrl: './daily-revenue.component.html',
  styleUrls: ['./daily-revenue.component.scss']
})
export class DailyRevenueComponent implements OnInit {
  dataSource: any;
  data: any;
  
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
public dialogRef: MatDialogRef<ManageFinancialComponent>) { }

  ngOnInit(): void {
    this.data = this.dialogData.data;
    this.dataSource = JSON.parse(this.dialogData.data);
    console.log(this.dialogData.data);
  }

}
