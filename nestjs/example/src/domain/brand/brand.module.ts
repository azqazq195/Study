import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { BrandEntity } from './entity/brand.entity';
import { BrandService } from './service/brand.service';
import { BrandController } from './controller/brand.controller';

@Module({
  imports: [TypeOrmModule.forFeature([BrandEntity])],
  providers: [BrandService],
  controllers: [BrandController],
})
export class BrandModule {}
