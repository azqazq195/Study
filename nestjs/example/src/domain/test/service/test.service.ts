import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { AEntity } from '../entity/a.entity';
import { BEntity } from '../entity/b.entity';
import { DeleteResult, FindOptionsWhere, Repository } from 'typeorm';
import { CreateADto } from '../controller/dto/create-a.dto';
import { UpdateADto } from '../controller/dto/update-a.dto';
import { Permission } from '../../user/user/entity/enum/permission.enum';

@Injectable()
export class TestService {
  constructor(
    @InjectRepository(AEntity)
    private readonly aRepository: Repository<AEntity>,
    @InjectRepository(BEntity)
    private readonly bRepository: Repository<BEntity>,
  ) {}

  // * relations 을 정의하지 않아도 eager: true 이면 알아서 가져온다. N + 1 아님
  findAll(): Promise<AEntity[]> {
    return this.aRepository.find();
  }

  // * relations 을 정의하지 않아도 eager: true 이면 알아서 가져온다. N + 1 아님
  async findOneById(id: number): Promise<AEntity> {
    return await this.aRepository.findOneOrFail({
      where: { id },
    });
  }

  async insert(createADto: CreateADto): Promise<AEntity> {
    return await this.aRepository.save({ ...createADto });
  }

  async update(id: number, updateADto: UpdateADto): Promise<AEntity> {
    const a = await this.findOneById(id);
    const updateA = await this.aRepository.create({ id, ...updateADto });
    for (const b of a.bs) {
      await this.bRepository.remove(b);
    }
    await this.aRepository.merge(a, updateA);
    return await this.aRepository.save(a);
  }

  async delete(id: number): Promise<DeleteResult> {
    return this.aRepository.delete({ id });
  }
}
