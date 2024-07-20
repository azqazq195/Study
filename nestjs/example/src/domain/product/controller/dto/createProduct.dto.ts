import { IsNumber, IsOptional, IsString } from 'class-validator';
import { ProductEntity } from '../../entity/product.entity';
import { plainToClass } from 'class-transformer';
import { Type } from '@nestjs/common';

export class CreateProductDto {
  @IsString()
  readonly name: string;

  @IsNumber()
  readonly statusCode: number;

  @IsOptional()
  @IsString()
  readonly detail?: string;

  @IsNumber()
  readonly brandId: number;
}
