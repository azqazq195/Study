import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { PermissionEntity } from '../entity/permission.entity';
import { Permission } from '../enum/permission.enum';
import { CreateUserRolePermissionDto } from '../../user/controller/dto/create-user-role-permission.dto';
import { RoleEntity } from '../../role/entity/role.entity';

@Injectable()
export class PermissionService {
  constructor(
    @InjectRepository(PermissionEntity)
    private readonly permissionRepository: Repository<PermissionEntity>,
  ) {
    for (const key in Permission) {
      this.permissionRepository.save({
        value: Permission[key],
      });
    }
  }

  find(ids: number[]): Promise<PermissionEntity[]> {
    return this.permissionRepository.findByIds(ids);
  }

  findOneByValue(value: string): Promise<PermissionEntity> {
    return this.permissionRepository.findOneOrFail({
      where: {
        value: value,
      },
    });
  }

  async findByCreateUserRolePermissionDto(
    permissionDtos: CreateUserRolePermissionDto[],
  ): Promise<PermissionEntity[]> {
    return this.permissionRepository.find({
      where: { value: [permissionDtos.map((dto) => dto.value)] },
    });
  }
}
