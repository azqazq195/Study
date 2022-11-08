import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { RoleEntity } from '../entity/role.entity';
import { Repository } from 'typeorm';

@Injectable()
export class RoleService {
  constructor(
    @InjectRepository(RoleEntity)
    private readonly roleRepository: Repository<RoleEntity>,
  ) {
    roleRepository.save({
      id: 1,
      name: '관리자',
    });
    roleRepository.save({
      id: 2,
      name: '일반유저',
    });
    roleRepository.save({
      id: 3,
      name: '손님',
    });
  }

  find(ids: number[]): Promise<RoleEntity[]> {
    return this.roleRepository.findByIds(ids);
  }
}
