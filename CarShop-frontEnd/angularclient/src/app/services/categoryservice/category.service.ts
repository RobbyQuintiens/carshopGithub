import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Model} from '../../entities/model';
import {Category} from '../../entities/category';
import {Brand} from '../../entities/brand';

@Injectable()
export class CategoryService {
  private categoryUrl: string;
  category: Category[];

  constructor(private http: HttpClient) {
    this.categoryUrl = 'http://localhost:8081/admin/api/services/categories';
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public listAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.categoryUrl);
  }
}
