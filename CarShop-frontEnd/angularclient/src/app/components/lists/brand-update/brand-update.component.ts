import { Component, OnInit } from '@angular/core';
import {BrandService} from '../../../services/brandservice/brand.service';
import {Brand} from '../../../entities/brand';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {first} from 'rxjs/operators';
import {Observable} from 'rxjs/internal/Observable';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-brand-update',
  templateUrl: './brand-update.component.html',
  styleUrls: ['./brand-update.component.css']
})
export class BrandUpdateComponent implements OnInit {

  brand: Brand = new Brand();
  country: string;
  name: string;
  id: number;
  updateForm: FormGroup;
  closeModal: string;

  constructor(private brandService: BrandService, private route: ActivatedRoute,
              private formbuilder: FormBuilder,private modalService: NgbModal,
              private router: Router) { }

  private getBrand(id: number): Observable<Brand>{
    return this.brandService.getBrand(id);
  }

  updateBrand() {
    this.brand.name = this.updateForm.value.name;
    this.brand.country = this.updateForm.value.country;

    this.brandService.updateBrand(this.id, this.brand).subscribe(res => {
      console.log(res);
      this.router.navigate(['brands']);
      this.modalService.dismissAll();
    });
  }

  updateBrandModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getBrand(this.id).subscribe({
      next: brand =>{
        this.name = brand.name;
        this.country = brand.country;
        this.updateForm = this.formbuilder.group({
          name: [this.name],
          country: [this.country]
        });
      }
    });



  }

}
