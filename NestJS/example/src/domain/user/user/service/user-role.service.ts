import { Injectable } from '@nestjs/common';
import { DeleteResult, Repository } from 'typeorm';
import { UserRoleEntity } from '../entity/user-role.entity';
import { CreateUserRoleDto } from '../controller/dto/create-user-role.dto';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class UserRoleService {
  constructor(
    @InjectRepository(UserRoleEntity)
    private readonly userRoleRepository: Repository<UserRoleEntity>,
  ) {}

  createAll(roleDtos: CreateUserRoleDto[]): UserRoleEntity[] {
    return roleDtos.map((roleDto) => {
      const role = roleDto.role;
      const userRolePermissions = roleDto.userRolePermissions;
      const permissions = [];
      userRolePermissions.forEach((value) => permissions.push(value));
      return UserRoleEntity.from(role, permissions);
    });
  }

  async delete(userRole: UserRoleEntity): Promise<UserRoleEntity> {
    return await this.userRoleRepository.remove(userRole);
  }

  async deleteAllByUserId(id: number): Promise<DeleteResult> {
    return await this.userRoleRepository.delete({
      user: {
        id: id,
      },
    });
  }
}
