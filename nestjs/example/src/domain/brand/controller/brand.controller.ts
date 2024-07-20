import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { BrandService } from '../service/brand.service';
import { BrandEntity } from '../entity/brand.entity';
import { CreateBrandDto } from './dto/createBrand.dto';
import { UpdateBrandDto } from './dto/updateBrand.dto';

@Controller('brands')
export class BrandController {
  constructor(private readonly brandService: BrandService) {}

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<BrandEntity> {
    return this.brandService.findOne(id);
  }

  @Get()
  async findAll(): Promise<BrandEntity[]> {
    return this.brandService.findAll();
  }

  @Post()
  async save(@Body() createBrandDto: CreateBrandDto): Promise<BrandEntity> {
    return this.brandService.save(createBrandDto.toBrandEntity());
  }

  @Put()
  async update(@Body() updateBrandDto: UpdateBrandDto): Promise<BrandEntity> {
    return this.brandService.save(updateBrandDto.toBrandEntity());
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<string> {
    await this.brandService.delete(id);
    return 'done';
  }
}
