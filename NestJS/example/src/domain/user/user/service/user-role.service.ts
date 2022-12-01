import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { UserRoleEntity } from '../entity/user-role.entity';
import { CreateUserRoleDto } from '../controller/dto/create-user-role.dto';
import { RoleService } from '../../role/service/role.service';
import { UserEntity } from '../entity/user.entity';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class UserRoleService {
  constructor(
    @InjectRepository(UserRoleEntity)
    private readonly userRoleRepository: Repository<UserRoleEntity>,
    private readonly roleService: RoleService,
  ) {}

  async addRole(
    user: UserEntity,
    createUserRoleDtos: CreateUserRoleDto[],
  ): Promise<void> {
    createUserRoleDtos.map(async (createUserRoleDto) => {
      const role = await this.roleService.findOneByValue(
        createUserRoleDto.value,
      );
      const userRole = UserRoleEntity.from({
        user,
        role,
      });
      await this.userRoleRepository.save(userRole);
    });
  }
}
