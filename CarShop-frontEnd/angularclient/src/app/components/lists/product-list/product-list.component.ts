import {Component, OnInit, OnChanges, SimpleChanges} from '@angular/core';
import {Product} from '../../../entities/product';
import {ProductService} from '../../../services/productservice/product.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Brand} from '../../../entities/brand';
import {BrandService} from '../../../services/brandservice/brand.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Model} from '../../../entities/model';
import {Transmissie} from '../../../entities/transmissie';
import {BrandstofService} from '../../../services/brandstofservice/brandstof.service';
import {Conditie} from '../../../entities/conditie';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  closeModal: string;
  products: Product[];
  product: Product;
  brands: Brand[];
  brand: Brand;
  models: Model[];
  model: Model;
  transmissies: Transmissie[];
  transmissie: Transmissie;
  condities: Conditie[];
  conditie: Conditie;
  formValue: FormGroup;
  brandId: number;

  constructor(private productService: ProductService,
              private brandService: BrandService,
              private brandstofService: BrandstofService,
              private modalService: NgbModal,
              private formbuilder: FormBuilder) {
  }

  changeBrand(event): void {
    this.brandId = event.target.value;
    this.listAllModelsByBrand(this.brandId);
  }

  listAll(): void {
    this.productService.listAll().subscribe(product =>
      this.products = product);
  }

  listAllTransmissions(): void {
    this.brandstofService.listAllTransmissies().subscribe(transmissie => {
      console.log(transmissie);
      this.transmissies = transmissie;
    });

  }

  listAllBrands(): void {
    this.brandService.listAll().subscribe(brand =>
      this.brands = brand);
  }

  listAllCondities(): void {
    this.brandstofService.listAllCondities().subscribe(conditie => {
      this.condities = conditie;
      console.log(conditie);
    });
  }

  addProduct(): void {
    this.product.model = parseInt(this.formValue.value.model);
    this.product.bouwjaar = this.formValue.value.year;
    this.product.conditie = this.formValue.value.conditie;
    this.product.aantalKm = this.formValue.value.km;
    this.product.koetswerkkleur = this.formValue.value.color;
    this.product.aantalDeuren = this.formValue.value.doors;
    this.product.transmissie = this.formValue.value.transmission;
    this.product.prijs = this.formValue.value.price;
    this.product.afbeelding = this.formValue.value.file;
    this.productService.addProduct(this.product).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll();
    });
  }

  openProductAddModel(addContent) {
    this.listAllBrands();
    this.listAllTransmissions();
    this.listAllCondities();
    this.modalService.open(addContent, {centered: true, size: 'lg'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
      console.log(this.brand.id);
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  listAllModelsByBrand(id: number): void {
    this.brandService.getModelsByBrand(id).subscribe(model =>
      this.models = model);
  }

  delete(product: Product): void {
    this.productService.delete(product.id).subscribe(() => {
      this.listAll();
      this.modalService.dismissAll();
    });
  }

  deleteProductModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed`;
    });
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {
        this.formValue.patchValue({
          fileSource: reader.result
        });

      };
    }
  }

  ngOnInit(): void {
    this.listAll();
    this.brandId = 0;
    this.product = new Product();
    this.formValue = this.formbuilder.group({
      brand: [''],
      model: [''],
      conditie: [''],
      year: ['', Validators.required, Validators.minLength(4), Validators.maxLength],
      doors: [''],
      transmission: [''],
      color: [''],
      hp: [''],
      km: [''],
      price: [''],
      aantalKm: [''],
      file: ['']
    });
  }

}
