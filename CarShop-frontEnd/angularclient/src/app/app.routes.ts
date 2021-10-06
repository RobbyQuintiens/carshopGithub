import {Routes, RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {BrandListComponent} from './components/lists/brand-list/brand-list.component';
import {ModelListComponent} from './components/lists/model-list/model-list.component';
import {HomeComponent} from './components/home/home.component';
import {BrandUpdateComponent} from './components/lists/brand-update/brand-update.component';
import {ProductListComponent} from './components/lists/product-list/product-list.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'brands',
    component: BrandListComponent
  },
  {
    path: 'brands/edit/:id',
    component: BrandUpdateComponent
  },
  {
    path: 'models',
    component: ModelListComponent
  },
  {
    path: 'products',
    component: ProductListComponent
  }
];

export const AppRoutes = RouterModule.forRoot(routes, {relativeLinkResolution: 'legacy'});
