import { Component, OnInit } from '@angular/core';
import {Model} from '../../../entities/model';
import {ModelService} from '../../../services/modelservice/model.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {CategoryService} from '../../../services/categoryservice/category.service';
import {Category} from '../../../entities/category';
import {BrandService} from '../../../services/brandservice/brand.service';
import {Brand} from '../../../entities/brand';
import {Brandstof} from '../../../entities/brandstof';
import {BrandstofService} from '../../../services/brandstofservice/brandstof.service';
import {Transmissie} from '../../../entities/transmissie';
import {Aandrijving} from '../../../entities/aandrijving';

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.css']
})
export class ModelListComponent implements OnInit {

  models: Model[];
  model: Model;
  categories: Category[];
  category: Category;
  brands: Brand[];
  brand: Brand;
  brandstoffen: Brandstof[];
  brandstof: Brandstof;
  transmissies: Transmissie[];
  transmissie: Transmissie;
  aandrijvingen: Aandrijving[];
  aandrijving: Aandrijving;
  closeModal: string;
  formValue: FormGroup;

  constructor(private modelService: ModelService,
              private categoryService: CategoryService,
              private brandService: BrandService,
              private brandstofService: BrandstofService,
              private modalService: NgbModal,
              private router: Router,
              private formbuilder: FormBuilder) {}

  delete(model: Model): void {
    this.modelService.deleteModel(model.id).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll()
    });
  }

  deleteModelModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  addModel() {
    this.model.name = this.formValue.value.name;
    this.model.generatie = this.formValue.value.generatie;
    this.model.category = parseInt(this.formValue.value.category);
    this.model.brand = parseInt(this.formValue.value.brand);
    this.model.brandstof = parseInt(this.formValue.value.fuel);
    this.model.brandstofScore = this.formValue.value.brandstofscore;
    this.model.aantalPk = parseInt(this.formValue.value.hp);
    this.model.aandrijving = this.formValue.value.aandrijving;
    this.model.co2Emissie = parseInt(this.formValue.value.co2);
    this.model.emissieKlasse = this.formValue.value.emissie;
    this.modelService.addModel(this.model).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll();
    });
  }

  openModalAddModel(addContent) {
    this.listAllCategories();
    this.listAllBrands();
    this.listAllBrandstof();
    this.listAllAandrijvingen();
    this.modalService.open(addContent, {centered: true, size: 'lg'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  listAll(): void {
    this.modelService.listAll().subscribe(model => {
      this.models = model;
    });
  }

  listAllCategories(): void {
    this.categoryService.listAll().subscribe(category => {
      this.categories = category;
    });
  }

  listAllBrands(): void {
    this.brandService.listAll().subscribe(brand => {
      this.brands = brand;
    })
  }

  listAllBrandstof(): void {
    this.brandstofService.listAllBrandstof().subscribe(brandstof => {
      this.brandstoffen = brandstof;
    })
  }

  listAllAandrijvingen(): void {
    this.brandstofService.listAllAandrijving().subscribe(aandrijving => {
      this.aandrijvingen = aandrijving;
    })
  }

  ngOnInit() {
    this.listAll();
    this.model = new Model();
    this.formValue = this.formbuilder.group({
      name: [''],
      generatie: [''],
      category: [''],
      brand: [''],
      fuel: [''],
      brandstofscore: [''],
      hp: [''],
      aandrijving: [''],
      co2: [''],
      emissie: ['']
    });
  }
}
