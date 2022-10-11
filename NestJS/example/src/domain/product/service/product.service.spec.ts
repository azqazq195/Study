import { ProductService } from './product.service';
import { Test, TestingModule } from '@nestjs/testing';
import { DatabaseModule } from '../../../database/database.module';
import { ProductModule } from '../product.module';
import { ProductEntity } from '../entity/product.entity';
import { BrandEntity } from '../../brand/entity/brand.entity';
import { BrandService } from '../../brand/service/brand.service';
import { BrandModule } from '../../brand/brand.module';
import { initializeTransactionalContext } from 'typeorm-transactional-cls-hooked';
import { CreateProductDto } from '../controller/dto/createProduct.dto';

describe('ProductService', () => {
  let module: TestingModule;
  let productService: ProductService;
  let brandService: BrandService;

  beforeAll(async () => {
    initializeTransactionalContext();
    module = await Test.createTestingModule({
      imports: [DatabaseModule, ProductModule, BrandModule],
    }).compile();
    productService = module.get(ProductService);
    brandService = module.get(BrandService);
  });

  afterAll(async () => {
    await module.close();
  });

  test('prepared', async () => {
    expect(productService).toBeDefined();
    expect(brandService).toBeDefined();
  });

  function getBrandEntity(index: number): BrandEntity {
    return BrandEntity.from({
      name: `brand${index}`,
      companyNumber: `number${index}`,
      isOpen: true,
      is24Open: false,
    });
  }

  // function getProductEntity(index: number, brand: BrandEntity): ProductEntity {
  //   const dto = new CreateProductDto({
  //     toProductEntity(): ProductEntity {},
  //   });
  // }

  test('getProductEntity', async () => {
    const dto = new CreateProductDto('name', 1, 1, 'detail');
    console.log(dto);
    const entity = ProductEntity.from(dto);
    console.log(entity);
  });

  // test('findOne', async () => {
  //   // given
  //   const brand = getBrandEntity(1);
  //   await brandService.save(brand);
  //
  //   const product = getProductEntity(1, brand);
  //   await productService.insert(product);
  //
  //   // when
  //   const storedProduct = await productService.findOne(product.id);
  //
  //   // then
  //   expect(storedProduct.id).toBe(product.id);
  // });
  //
  // test('findAll', async () => {
  //   // given
  //   const brand = getBrandEntity(1);
  //   await brandService.save(brand);
  //
  //   const products: ProductEntity[] = [
  //     getProductEntity(1, brand),
  //     getProductEntity(2, brand),
  //   ];
  //
  //   for (const product of products) {
  //     await productService.save(product);
  //   }
  //
  //   expect((await productService.findAll()).length).toBe(products.length);
  //   products.forEach((value) => {
  //     expect(value.id).not.toBeNull();
  //   });
  //
  //   const brand2 = getBrandEntity(2);
  //   await brandService.save(brand2);
  //   products[0].changeBrand(brand2);
  //   await productService.save(products[0]);
  //
  //   const storedProduct = await productService.findOne(products[0].id);
  //   console.log(storedProduct);
  //   console.log(await storedProduct.brand);
  //   console.log(storedProduct);
  //
  //   console.log((await products[0].brand).id);
  //   const storedBrand2 = await brandService.findOne(
  //     (
  //       await products[0].brand
  //     ).id,
  //   );
  //   storedBrand2.addProductEntity(products[1]);
  //   await brandService.save(storedBrand2);
  //   console.log(storedBrand2);
  // });
});
