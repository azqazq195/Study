import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { Expose, plainToClass } from 'class-transformer';
import { JoinColumn } from 'typeorm';
import { UpdateProductDto } from '../controller/dto/updateProduct.dto';
import { CreateProductDto } from '../controller/dto/createProduct.dto';
import { BaseEntity } from '../../../shared/base.entity';
import { BrandEntity } from '../../brand/entity/brand.entity';

type Mutable<Type> = {
  -readonly [Key in keyof Type]: Type[Key];
};

@Entity({
  name: 'products',
})
export class ProductEntity extends BaseEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  @Expose({ toClassOnly: true })
  readonly id: number;

  @ManyToOne(() => BrandEntity, (brand) => brand.products, {
    nullable: false,
    lazy: true,
  })
  @JoinColumn({ name: 'brand_id' })
  readonly brand: BrandEntity;

  @Column({
    type: 'varchar',
    name: 'name',
    comment: '상품명',
    length: 255,
    nullable: false,
  })
  @Expose()
  readonly name: string;

  @Column({
    type: 'tinyint',
    name: 'status',
    comment: '판매 상태',
    width: 1,
  })
  @Expose()
  readonly statusCode: number;

  @Column({
    type: 'text',
    name: 'detail',
    nullable: true,
    comment: '상세정보',
  })
  @Expose()
  readonly detail?: string;

  public changeBrand(brand: BrandEntity) {
    const mutableThis = this as Mutable<ProductEntity>;
    mutableThis.brand = brand;
  }

  public update(updateDto: UpdateProductDto): void {
    const mutableThis = this as Mutable<ProductEntity>;
    mutableThis.name = updateDto.name;
  }

  static from(args: CreateProductDto): ProductEntity {
    return plainToClass(ProductEntity, args, { excludeExtraneousValues: true });
  }
}
