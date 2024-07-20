import { ProductEntity } from '../entity/product.entity';
import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';
import { UpdateProductDto } from '../controller/dto/updateProduct.dto';
import { raw } from 'express';
import { CreateProductDto } from '../controller/dto/createProduct.dto';

@Injectable()
export class ProductService {
  constructor(
    @InjectRepository(ProductEntity)
    private readonly productRepository: Repository<ProductEntity>,
  ) {}

  findAll(): Promise<ProductEntity[]> {
    return this.productRepository.find({ order: { id: 'DESC' } });
  }

  findOne(id: number): Promise<ProductEntity> {
    const product = this.productRepository.findOneOrFail({
      where: {
        id: id,
      },
    });
    return product.then((value) => value);
  }

  @Transactional()
  async insert(createProductDto: CreateProductDto): Promise<ProductEntity> {
    return this.productRepository.save(createProductDto);
  }

  @Transactional()
  async update(
    id: number,
    updateProductDto: UpdateProductDto,
  ): Promise<ProductEntity> {
    const product = await this.findOne(id);
    product.update(updateProductDto);
    return this.productRepository.save(product);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.productRepository.delete({ id: id });
  }
}
