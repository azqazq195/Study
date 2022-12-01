import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { RoleEntity } from '../entity/role.entity';
import { Repository } from 'typeorm';
import { Role } from '../enum/role.enum';
import { PermissionService } from '../../permission/service/permission.service';
import { UserRoleEntity } from '../../user/entity/user-role.entity';

@Injectable()
export class RoleService {
  constructor(
    @InjectRepository(RoleEntity)
    private readonly roleRepository: Repository<RoleEntity>,
    private readonly permissionService: PermissionService,
  ) {
    for (const key in Role) {
      this.roleRepository.save({
        value: Role[key],
      });
    }
  }

  find(ids: number[]): Promise<RoleEntity[]> {
    return this.roleRepository.findByIds(ids);
  }

  findOneByValue(value: string): Promise<RoleEntity> {
    return this.roleRepository.findOneOrFail({
      where: {
        value: value,
      },
    });
  }

  // async getUserRoles(
  //   userRoleDtos: CreateUserRoleDto[],
  // ): Promise<UserRoleEntity[]> {
  //   const result = userRoleDtos.map((userRoleDto) => {
  //     const permissions =
  //       this.permissionService.findByCreateUserRolePermissionDto(
  //         userRoleDto.permissionDtos,
  //       );
  //     const userRoleEntity: UserRoleEntity = {
  //       userRolePermissions,
  //     };
  //
  //     return true;
  //   });
  // }

  // async findByCreateUserRoleDto(
  //   roleDtos: CreateUserRoleDto[],
  // ): Promise<RoleEntity[]> {
  //   const roles: RoleEntity[] = [];
  //
  //   for (const roleDto of roleDtos) {
  //     const role = await this.roleRepository.findOneOrFail({
  //       where: { value: roleDto.value },
  //     });
  //
  //     const permissions =
  //       await this.permissionService.findByCreateUserRolePermissionDto(
  //         roleDto.permissionDtos,
  //       );
  //
  //     role.changePermissions(permissions);
  //     console.log(permissions);
  //     roles.push(role);
  //   }
  //
  //   return roles;
  //
  //   // const roles = await this.roleRepository.find({
  //   //   where: { value: roleDtos.map((dto) => dto.value) },
  //   // });
  //
  //   return roles;
  // }
}
