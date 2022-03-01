import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AngularFireDatabase, AngularFireList} from "@angular/fire/compat/database";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {FileUpload} from "../interface/FileUpload";
import {finalize} from "rxjs/operators";
import {IEmployeeCreate} from "../interface/IEmployeeCreate";
import {Employee} from "../interface/Employee";
import {TokenStorageService} from "../security/service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private API_CREATE: string = 'http://localhost:8080/employee/createEmployee';
  private API_EDIT: string = 'http://localhost:8080/employee/updateEmployee';
  private API_DETAIL: string = 'http://localhost:8080/employee/viewEmployee';
  private basePath = '/imgEmployee';
  httpOptions: any;

  constructor(public httpClient: HttpClient, private db: AngularFireDatabase, private storage: AngularFireStorage, private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer` + this.tokenStorage.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }),
    };
  }


  pushFileToStorage(fileUpload: FileUpload): Observable<string> {
    const filePath = `${this.basePath}/${fileUpload.file.name}`;
    const storageRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, fileUpload.file);

    uploadTask.snapshotChanges().pipe(
      finalize(() => {
        storageRef.getDownloadURL().subscribe(downloadURL => {
          fileUpload.url = downloadURL;
          fileUpload.name = fileUpload.file.name;
          this.saveFileData(fileUpload);
        });
      })
    ).subscribe();
    return this.storage.ref(this.basePath + "/" + fileUpload.file.name).getDownloadURL();
  }

  private saveFileData(fileUpload: FileUpload): void {
    this.db.list(this.basePath).push(fileUpload);
  }

  getFiles(numberItems: number): AngularFireList<FileUpload> {
    return this.db.list(this.basePath, ref =>
      ref.limitToLast(numberItems));
  }

  createEmployee(employee: IEmployeeCreate): Observable<HttpEvent<any>> {
    console.log(employee)
    return this.httpClient.post<IEmployeeCreate>(this.API_CREATE, employee, this.httpOptions);
  }

  getEmployeeById(employeeId: string): Observable<any> {
    return this.httpClient.get<any>(this.API_DETAIL + '/' + employeeId, this.httpOptions);
  }

  editEmployee(employeeId: string, employee: Employee): Observable<any> {
    return this.httpClient.put<any>(this.API_EDIT + '/' + employeeId, employee, this.httpOptions);
  }

  public API: string = "http://localhost:8080/employee";

  getAllEmployee(): Observable<any> {
    return this.httpClient.get<any>(this.API + '/listEmployee', this.httpOptions);
  }

  getAllAddress(): Observable<any> {
    return this.httpClient.get<any>(this.API + '/listAddress', this.httpOptions);
  }

  searchEmployee(idEmp: string, dateStart: string, dateEnd: string, workStart: string, workEnd: string,
                 address: string, positionId: string): Observable<any> {
    return this.httpClient.get<any>(this.API + '/searchEmployee?idEmp=' + idEmp + '&dateStart=' +
      dateStart + '&dateEnd=' + dateEnd + '&workStart=' + workStart + '&workEnd=' + workEnd +
      '&address=' + address + '&positionId=' + positionId, this.httpOptions);
  }

  getsearchEmployee(idEmp: string, dateStart: string, dateEnd: string, workStart: string, workEnd: string,
                    address: string, positionId: string, page: number): Observable<any> {
    return this.httpClient.get<any>(this.API + '/searchEmployee?idEmp=' + idEmp + '&dateStart=' +
      dateStart + '&dateEnd=' + dateEnd + '&workStart=' + workStart + '&workEnd=' + workEnd +
      '&address=' + address + '&positionId=' + positionId + '&page=' + page, this.httpOptions);
  }

  deleteEmployee(id: string): Observable<any> {
    return this.httpClient.delete<any>(this.API + '/deleteEmployee/' + id, this.httpOptions);
  }

  getEmployeeByIdDelete(employeeId: string | null): Observable<any> {
    return this.httpClient.get<any>(this.API + '/viewEmployee/' + employeeId, this.httpOptions);
  }

}
