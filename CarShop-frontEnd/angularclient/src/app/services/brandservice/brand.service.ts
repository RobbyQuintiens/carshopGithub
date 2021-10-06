import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Brand} from '../../entities/brand';
import {Model} from '../../entities/model';

@Injectable()
export class BrandService {
  private brandurl: string;
  brand: Brand[];

  constructor(private http: HttpClient) {
    this.brandurl = 'http://localhost:8081/admin/api/services/brands';
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public listAll(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.brandurl);
  }

  public deleteBrand(id: number): Observable<Brand> {
    let endpoint =  this.brandurl + '/delete/' + id;
    return this.http.delete<Brand>(endpoint);
  }

  public getBrand(id: number): Observable<Brand> {
    let endpoint =  this.brandurl + '/' + id;
    return this.http.get<Brand>(endpoint);
    }

  public addBrand(brand: Brand): Observable<Brand> {
    let endpoint = `${this.brandurl}/${'/create'}`;
    return this.http.post<Brand>(endpoint, brand, this.httpOptions);
  }

  public updateBrand(id: number, brand: Brand): Observable<Brand> {
    let endpoint = this.brandurl + '/edit/' + id;
    return this.http.put<Brand>(endpoint, brand, this.httpOptions);
  }

  public getModelsByBrand(id: number): Observable<Model[]> {
    let endpoint = this.brandurl + '/list/' + id;
    return this.http.get<Model[]>(endpoint);
  }
}
