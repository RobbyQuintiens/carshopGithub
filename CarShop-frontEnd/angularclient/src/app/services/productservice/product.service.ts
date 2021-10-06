import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Brand} from '../../entities/brand';
import {Product} from '../../entities/product';
import {Model} from '../../entities/model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private producturl: string;
  product: Product[];

  constructor(private http: HttpClient) {
    this.producturl = 'http://localhost:8081/admin/api/services/products';
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public listAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.producturl);
  }

  public delete(id: number): Observable<Product> {
    let endpoint = this.producturl + '/delete/' + id;
    return this.http.delete<Product>(endpoint);
  }

  public getProduct(id: number): Observable<Product> {
    let endpoint =  this.producturl + '/' + id;
    return this.http.get<Product>(endpoint);
  }

  public addProduct(product: Product): Observable<any> {
    const addProductUrl = `${this.producturl}${'/create'}`;
    const body = JSON.stringify(product);
    console.log(body);
    return this.http.post<Product>(addProductUrl, body, this.httpOptions);
  }


}
