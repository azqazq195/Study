import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';
import { BrandEntity } from '../entity/brand.entity';

@Injectable()
export class BrandService {
  constructor(
    @InjectRepository(BrandEntity)
    private readonly brandRepository: Repository<BrandEntity>,
  ) {}

  findAll(): Promise<BrandEntity[]> {
    return this.brandRepository.find();
  }

  findOne(id: number): Promise<BrandEntity> {
    return this.brandRepository.findOneOrFail({
      where: {
        id: id,
      },
    });
  }

  @Transactional()
  async save(brand: BrandEntity): Promise<BrandEntity> {
    return this.brandRepository.save(brand);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.brandRepository.softDelete({ id: id });
  }
}
