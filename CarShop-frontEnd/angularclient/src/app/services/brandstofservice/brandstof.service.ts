import { Injectable } from '@angular/core';
import {Brand} from '../../entities/brand';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Brandstof} from '../../entities/brandstof';
import {Transmissie} from '../../entities/transmissie';
import {Aandrijving} from '../../entities/aandrijving';
import {Conditie} from '../../entities/conditie';

@Injectable()
export class BrandstofService {

  private brandstofTransmissieUrl: string;

  constructor(private http: HttpClient) {
    this.brandstofTransmissieUrl = 'http://localhost:8081/admin/api/services/brandstoftransmissie';
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public listAllBrandstof(): Observable<Brandstof[]> {
    return this.http.get<Brandstof[]>(this.brandstofTransmissieUrl + '/brandstof');
  }

  public listAllTransmissies(): Observable<Transmissie[]> {
    return this.http.get<Transmissie[]>(this.brandstofTransmissieUrl + '/transmissie');
  }

  public listAllAandrijving(): Observable<Transmissie[]> {
    return this.http.get<Aandrijving[]>(this.brandstofTransmissieUrl + '/aandrijving');
  }

  public listAllCondities(): Observable<Conditie[]> {
    return this.http.get<Conditie[]>(this.brandstofTransmissieUrl + '/conditie');
  }
}
