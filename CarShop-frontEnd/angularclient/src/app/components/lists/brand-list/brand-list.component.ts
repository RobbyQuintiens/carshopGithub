import { Component, OnInit } from '@angular/core';
import {Brand} from '../../../entities/brand';
import {BrandService} from '../../../services/brandservice/brand.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs/internal/Observable';
import {first, map, take} from 'rxjs/operators';
import {Subscription} from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-brand-list',
  templateUrl: './brand-list.component.html',
  styleUrls: ['./brand-list.component.css']
})
export class BrandListComponent implements OnInit {

  brands: Brand[];
  brand: Brand;
  closeModal: string;
  formValue: FormGroup;
  name: string;
  country: string;
  id: number;

  constructor(private brandService: BrandService, private modalService: NgbModal, private router: Router, private formbuilder: FormBuilder, private route: ActivatedRoute) { }

  delete(brand: Brand): void {
    this.brandService.deleteBrand(brand.id).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll()
    });
  }

  deleteBrandModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  addBrand() {
    this.brand.name = this.formValue.value.name;
    this.brand.country = this.formValue.value.country;

    this.brandService.addBrand(this.brand).pipe(first()).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll();
    });
  }

  openModalAddBrand(addContent) {
    this.modalService.open(addContent, {centered: true}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
      this.formValue.reset();
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  listAll(): void {
    this.brandService.listAll().subscribe(brand => {
      this.brands = brand;
    });
  }

  ngOnInit() {
    this.listAll();
    this.brand = new Brand();
    this.formValue = this.formbuilder.group({
      name: [''],
      country: ['']
    });
  }
}
