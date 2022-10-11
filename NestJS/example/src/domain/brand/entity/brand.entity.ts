import {
  Column,
  CreateDateColumn,
  DeleteDateColumn,
  Entity,
  OneToMany,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';
import { plainToClass } from 'class-transformer';
import { ProductEntity } from '../../product/entity/product.entity';

type Mutable<Type> = {
  -readonly [Key in keyof Type]: Type[Key];
};

@Entity({
  name: 'brands',
})
export class BrandEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id!: number;

  @OneToMany(() => ProductEntity, (product) => product.brand)
  readonly products!: ProductEntity[];

  // @Column({
  //   type: 'int',
  //   name: 'user_id',
  //   comment: '브랜드 소유자 유저 id',
  // })
  // userId!: number;

  @Column({
    type: 'varchar',
    name: 'name',
    unique: true,
    comment: '브랜드명',
    length: 255,
  })
  readonly name!: string;

  @Column({
    type: 'varchar',
    name: 'company_number',
    comment: '브랜드 소유자 사업자등록번호',
    length: 255,
  })
  readonly companyNumber!: string;

  @Column({
    type: 'boolean',
    name: 'is_open',
    comment: '오픈 여부',
    width: 1,
  })
  readonly isOpen!: boolean;

  @Column({
    type: 'boolean',
    name: 'is_24_open',
    comment: '24 시간 open 여부',
    width: 1,
  })
  readonly is24Open!: boolean;

  @CreateDateColumn({
    type: 'datetime',
    name: 'created_at',
    comment: '생성일시',
  })
  readonly createdAt!: Date;

  @UpdateDateColumn({
    type: 'datetime',
    name: 'updated_at',
    comment: '수정일시',
  })
  readonly updatedAt!: Date;

  @DeleteDateColumn({
    type: 'datetime',
    name: 'deleted_at',
    nullable: true,
    comment: '삭제일시',
  })
  readonly deletedAt!: Date | null;

  public addProductEntity(product: ProductEntity) {
    this.products.push(product);
  }

  public changeProductEntity(products: ProductEntity[]) {
    const mutableThis = this as Mutable<BrandEntity>;
    mutableThis.products = products;
  }

  static from(args: Partial<BrandEntity>) {
    return plainToClass(BrandEntity, args);
  }
}
