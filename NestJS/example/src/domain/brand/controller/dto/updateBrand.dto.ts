import { IsBoolean, IsNumber, IsString } from 'class-validator';
import { BrandEntity } from '../../entity/brand.entity';

export class UpdateBrandDto {
  @IsNumber()
  readonly id!: number;

  @IsString()
  readonly name!: string;

  @IsString()
  readonly companyNumber!: string;

  @IsBoolean()
  readonly isOpen!: boolean;

  @IsBoolean()
  readonly is24Open!: boolean;

  toBrandEntity(): BrandEntity {
    return BrandEntity.from(this);
  }
}
