import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { BrandListComponent } from './components/lists/brand-list/brand-list.component';
import {BrandService} from './services/brandservice/brand.service';
import {ModelService} from './services/modelservice/model.service';
import {CategoryService} from './services/categoryservice/category.service';
import { ModelListComponent } from './components/lists/model-list/model-list.component';
import {RouterModule} from '@angular/router';
import { AppRoutes } from './app.routes';
import { HomeComponent } from './components/home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AddModelComponent } from './components/add-model/add-model.component';
import {BrandstofService} from './services/brandstofservice/brandstof.service';
import { BrandUpdateComponent } from './components/lists/brand-update/brand-update.component';
import { SalesComponent } from './components/charts/sales/sales.component';

import { ChartModule } from 'angular2-chartjs';
import { AddBrandComponent } from './components/add-brand/add-brand.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ProductListComponent } from './components/lists/product-list/product-list.component';
import {ProductService} from './services/productservice/product.service';



@NgModule({
  declarations: [
    AppComponent,
    BrandListComponent,
    ModelListComponent,
    HomeComponent,
    AddModelComponent,
    BrandUpdateComponent,
    SalesComponent,
    AddBrandComponent,
    NotificationComponent,
    ProductListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    AppRoutes,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    ChartModule
  ],
  providers: [BrandService, ModelService, CategoryService, BrandstofService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
