import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { UserRoleEntity } from '../entity/user-role.entity';
import { Repository } from 'typeorm';

@Injectable()
export class UserRoleService {
  constructor(
    @InjectRepository(UserRoleEntity)
    private readonly roleRepository: Repository<UserRoleEntity>,
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

  find(ids: number[]): Promise<UserRoleEntity[]> {
    return this.roleRepository.findByIds(ids);
  }
}
