import {Component, OnInit} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from "chart.js";
import {Label} from "ng2-charts";
import {StatisticalServiceService} from "../../services/statistical-service.service";

@Component({
  selector: 'app-view-main',
  templateUrl: './view-main.component.html',
  styleUrls: ['./view-main.component.css']
})

export class ViewMainComponent  implements OnInit {
  mode: string= 'computer';
  time: string = '1week';
  messageErrorStart: string = '';
  statusErrorStart: boolean= false;
  messageErrorEnd: string = '';
  statusErrorEnd: boolean= false;
  startTime: string = '2020-12-31';
  endTime: string = '';
  labelY: string = "Giờ chơi";
  labelX: string = "Máy tính";
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: Label[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = false;
  barChartPlugins = [];
  barChartData: ChartDataSets[] = [{
    data: [],
    label: '' ,
    backgroundColor: 'rgb(102, 102, 255)',
    borderColor: 'rgb(255, 99, 132)'
  }];
  isData: boolean = true;
  constructor(private service: StatisticalServiceService) { }

  ngOnInit(): void {
    this.reset();
  }

  reset(): void{
    let now = new Date();
    this.startTime = ''+now.getFullYear()+ '-' + (now.getMonth()+1<10?'0'+now.getMonth()+1:now.getMonth()+1) + '-' +
      (now.getDate()<10?'0'+now.getDate():now.getDate());
    this.endTime = this.startTime;
    this.statusErrorEnd = false;
    this.statusErrorStart = false;
    this.barChartData = [];
    this.barChartLabels = [];
    this.barChartLegend = false;
    this.isData = true;
  }

  changeMode(mode: string){
    this.reset();
    this.mode = mode;
    switch (mode) {
      case 'computer':
        this.labelY = 'Giờ chơi';
        this.labelX = 'Máy tính';
        break;
      case 'month':
        this.labelY = 'Tiền';
        this.labelX = 'Tháng';
        break;
      case 'account':
        this.labelY = '';
        this.labelX = 'Tài khoản';
        break;
    }
  }

  onSubmit(){
    if(this.validatingValue()){
      this.barChartLabels = [];
      switch (this.mode) {
        case 'computer':
          this.viewByComputer();
          break;
        case 'month':
          this.viewByMonth();
          break;
        case 'account':
          this.viewByAccount();
          break;
      }
      this.barChartLegend = true;
    }
  }

  validatingValue(): boolean{
    const regexDate = /^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/g;
    let startTime = new Date(this.startTime);
    let endTime = new Date(this.endTime);
    let now = new Date();
    let betweenDay = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24);
    let betweenStart = (now.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24);
    let betweenEnd = (now.getTime() - endTime.getTime()) / (1000 * 60 * 60 * 24);
    this.statusErrorStart = false;
    this.statusErrorEnd = false;
    if(betweenStart < 0){
      this.statusErrorStart = true;
      this.messageErrorStart = 'Ngày bắt đầu phải bé hơn hoặc bằng ngày hiện tại';
    }else if(betweenStart > 7306){
      this.statusErrorStart = true;
      this.messageErrorStart = 'Trang web chỉ thống kê trong khoảng thời gian 20 năm';
    }
    if(betweenEnd < 0 && this.mode !== 'account'){
      this.statusErrorEnd = true;
      this.messageErrorEnd = 'Ngày kết thúc phải bé hơn hoặc bằng ngày hiện tại';
    }else if(betweenStart > 7306 && this.mode !== 'account'){
      this.statusErrorEnd = true;
      this.messageErrorEnd = 'Trang web chỉ thống kê trong khoảng thời gian 20 năm';
    }
    if(this.statusErrorEnd || this.statusErrorStart){
      return false;
    }
    if(betweenDay < 1 && this.mode !== 'account'){
      this.statusErrorEnd = true;
      this.messageErrorEnd = 'Ngày kết thúc phải lớn hơn ngày bắt đầu 24h';
      return false;
    }
    if(betweenDay > 7305 && this.mode !== 'account'){
      this.statusErrorEnd = true;
      this.messageErrorEnd = 'Trang web chỉ thống kê trong khoảng thời gian 20 năm';
      return false;
    }
    return true;
  }

  revertDate(date: string): string{
    let str: string[] = date.split('-');
    return str[2] + '-' + str[1] + '-' + str[0];
  }

  viewByComputer(){
    this.service.getDataByComputer(this.startTime, this.endTime).subscribe((data) =>{
      let datas: number[] = [];
      data.forEach((computer) => {
        this.barChartLabels.push(computer.name);
        datas.push(computer.time);
      });
      this.barChartData = [{
        data: datas,
        label: 'Giờ chơi' ,
        backgroundColor: 'rgb(102, 102, 255)',
        borderColor: 'rgb(102, 102, 255)'
      }];
      this.isData = true;
    }, error => {
      this.isData = false;
    });
  }

  viewByMonth(){
    this.service.getDataByMonth(this.startTime, this.endTime).subscribe((data) =>{
      let totalMoneyComputer: number[] = [];
      let totalMoneyService: number[] = [];
      data.forEach(month => {
        this.barChartLabels.push(month.nameMonth);
        totalMoneyComputer.push(month.totalMoneyComputer);
        totalMoneyService.push(month.totalMoneyService);
      });
      this.barChartData = [{
        data: totalMoneyComputer,
        label: 'Máy tính' ,
        backgroundColor: 'rgb(102, 102, 255)',
        borderColor: 'rgb(102, 102, 255)'
      },{
        data: totalMoneyService,
        label: 'Dịch vụ' ,
        backgroundColor: 'rgb(255, 153, 0)',
        borderColor: 'rgb(255, 153, 0)'
      }];
      this.isData = true;
    }, error => {
      this.isData = false;
    })
  }

  viewByAccount(){
    this.service.getDataByAccount(this.startTime, this.time).subscribe(data => {
      let totalMoney: number[] = [];
      let totalTime: number[] = [];
      data.forEach(account => {
        this.barChartLabels.push(account.name);
        totalMoney.push(account.money);
        totalTime.push(account.time);
      });
      this.barChartData = [{
        data: totalTime,
        label: 'Giờ chơi' ,
        backgroundColor: 'rgb(102, 102, 255)',
        borderColor: 'rgb(102, 102, 255)'
      },{
        data: totalMoney,
        label: 'Tiền' ,
        backgroundColor: 'rgb(255, 153, 0)',
        borderColor: 'rgb(255, 153, 0)'
      }];
      this.isData = true;
    }, error => {
      this.isData = false;
    })
  }

  setTime(value: string){
    this.time = value;
  }

  setStartTime(value: string){
    if(value !== ''){
      this.startTime = value;
    }
  }

  setEndTime(value: string){
    if(value !== ''){
      this.endTime = value;
    }
  }

}
