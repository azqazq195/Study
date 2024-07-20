import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { ProductEntity } from '../entity/product.entity';
import { ProductService } from '../service/product.service';
import { CreateProductDto } from './dto/createProduct.dto';
import { UpdateProductDto } from './dto/updateProduct.dto';

@Controller('products')
export class ProductController {
  constructor(private readonly productService: ProductService) {}

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<ProductEntity> {
    return this.productService.findOne(id);
  }

  @Get()
  async findAll(): Promise<ProductEntity[]> {
    return this.productService.findAll();
  }

  @Post()
  async save(
    @Body() createProductDto: CreateProductDto,
  ): Promise<ProductEntity> {
    return this.productService.insert(createProductDto);
  }

  @Put(':id')
  async update(
    @Param('id') id: number,
    @Body() updateProductDto: UpdateProductDto,
  ): Promise<ProductEntity> {
    return this.productService.update(id, updateProductDto);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<string> {
    await this.productService.delete(id);
    return 'done';
  }
}
