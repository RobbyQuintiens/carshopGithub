import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Brand} from '../../entities/brand';
import {first} from 'rxjs/operators';
import {Router} from '@angular/router';
import {BrandService} from '../../services/brandservice/brand.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.css']
})
export class AddBrandComponent implements OnInit {

  @Input() formValue: FormGroup;

  brand: Brand;

  closeModal: string;
  name: string;
  country: string;
  id: number;

  constructor(private brandService: BrandService, private router: Router, private formbuilder: FormBuilder, private modalService: NgbModal) { }

  addBrand() {
    this.brand.name = this.formValue.value.name;
    this.brand.country = this.formValue.value.country;

    // this.brandService.addBrand(this.brand).pipe(first()).subscribe(() => {
    //   this.listAll();
    //   this.modalService.dismissAll();
    // });
  }

  openModalAddBrand(addContent) {
    this.modalService.open(addContent, {centered: true}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
      this.formValue.reset();
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  ngOnInit(): void {
    this.brand = new Brand();
    this.formValue = this.formbuilder.group({
      name: [''],
      country: ['']
    });
  }



}
