import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Model} from '../../entities/model';
import {Brand} from '../../entities/brand';

@Injectable()
export class ModelService {
  private modelUrl: string;

  constructor(private http: HttpClient) {
    this.modelUrl = 'http://localhost:8081/admin/api/services/models';
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public listAll(): Observable<Model[]> {
    return this.http.get<Model[]>(this.modelUrl);
  }

  public deleteModel(id: number):  Observable<Model>  {
    const endpoint = this.modelUrl + '/delete/' + id;
    return this.http.delete<Model>(endpoint);
  }

  public addModel(model: Model): Observable<any> {
    const addModelUrl = `${this.modelUrl}${'/create'}`;
    const body = JSON.stringify(model);
    return this.http.post<Model>(addModelUrl, body, this.httpOptions);
  }
}
