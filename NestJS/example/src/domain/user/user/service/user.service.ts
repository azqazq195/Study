import { BadRequestException, Injectable } from '@nestjs/common';
import { UserEntity } from '../entity/user.entity';
import { Repository, Transaction } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';
import { CreateUserDto } from '../controller/dto/create-user.dto';
import { RoleService } from '../../role/service/role.service';
import { UpdateUserDto } from '../controller/dto/update-user.dto';
import { PermissionService } from '../../permission/service/permission.service';
import { UserRoleService } from './user-role.service';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    private readonly userRepository: Repository<UserEntity>,
    private readonly userRoleService: UserRoleService,
    private readonly roleService: RoleService,
    private readonly permissionService: PermissionService,
  ) {}

  findAll(): Promise<UserEntity[]> {
    return this.userRepository.find();
  }

  async findOne(id: number): Promise<UserEntity> {
    const user = await this.userRepository.findOneOrFail(
      { id: id },
      { relations: ['userRoles'] },
    );

    console.log(user);
    return user;
    // return this.userRepository.findOneOrFail(
    //   { id: id },
    //   { relations: ['userRoles', 'userRoles.role'] },
    // );
  }

  async create(createUserDto: CreateUserDto): Promise<UserEntity> {
    if (await this.isExists(createUserDto.username)) {
      throw new BadRequestException('이미 존재하는 유저 아이디 입니다.');
    }

    const tempUser = UserEntity.from(createUserDto);
    const user = await this.userRepository.save(tempUser);
    await this.userRoleService.addRole(user, createUserDto.roleDtos);

    // const userRoles = [];
    // const userRoleDtos = createUserDto.roleDtos;
    // userRoleDtos.map(async (userRoleDto) => {
    //   const role = await this.roleService.findOneByValue(userRoleDto.value);
    //   const userRolePermissionDtos = userRoleDto.permissionDtos;
    //   const permissions =
    //     await this.permissionService.findByCreateUserRolePermissionDto(
    //       userRolePermissionDtos,
    //     );
    //
    //   const userRolePermissions = [];
    //   permissions.map(async (permission) => {
    //     userRolePermissions.push(UserRolePermissionEntity.from({ permission }));
    //   });
    //
    //   userRoles.push(UserRoleEntity.from({ user, role, userRolePermissions }));
    // });
    // user.changeRoles(userRoles);
    // await this.userRepository.save(user);

    // const userRoles: UserRoleEntity[] = userRoleDtos.map(
    //   async (userRoleDto) => {
    //     const role: RoleEntity = await this.roleService.findOneByValue(
    //       userRoleDto.value,
    //     );
    //
    //     // * permission
    //     const userRolePermissions = [];
    //     const permissionDtos = userRoleDto.permissionDtos;
    //     for (const permissionDto of permissionDtos) {
    //       const permission = await this.permissionService.findOneByValue(
    //         permissionDto.value,
    //       );
    //       const userRolePermission = UserRolePermissionEntity.from({
    //         permission,
    //       });
    //       userRolePermissions.push(userRolePermission);
    //     }
    //
    //     // const userRolePermissions = await Promise.all(
    //     //   permissionDtos.map(async (permissionDto) => {
    //     //     const permission = await this.permissionService.findOneByValue(
    //     //       permissionDto.value,
    //     //     );
    //     //     UserRolePermissionEntity.from({ permission });
    //     //   }),
    //     // );
    //
    //     const userRole = UserRoleEntity.from({
    //       role,
    //       userRolePermissions,
    //     });
    //
    //     return userRole;
    //   },
    // );
    return this.findOne(user.id);
  }

  @Transactional()
  async update(id: number, updateUserDto: UpdateUserDto): Promise<UserEntity> {
    const user = UserEntity.from({ id: id, ...updateUserDto });
    // const roles = await this.roleService.find(updateUserDto.roleIds);
    // user.changeRoles(roles);
    return this.userRepository.save(user);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.userRepository.delete({ id: id });
  }

  async isExists(username: string): Promise<boolean> {
    const check = await this.userRepository.findOne({ username: username });
    return !!check;
  }
}
