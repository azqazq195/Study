import { Injectable } from '@nestjs/common';
import { UserEntity } from '../entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';
import { CreateUserDto } from '../controller/dto/createUser.dto';
import { RoleService } from './role.service';
import { UpdateUserDto } from '../controller/dto/updateUser.dto';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    private readonly userRepository: Repository<UserEntity>,
    private readonly roleService: RoleService,
  ) {}

  findAll(): Promise<UserEntity[]> {
    return this.userRepository.find();
  }

  findOne(id: number): Promise<UserEntity> {
    return this.userRepository.findOneOrFail(
      { id: id },
      { relations: ['roles'] },
    );
  }

  @Transactional()
  async create(createUserDto: CreateUserDto): Promise<UserEntity> {
    const user = UserEntity.from(createUserDto);
    const roles = await this.roleService.find(createUserDto.roleIds);
    user.changeRoles(roles);
    return this.userRepository.save(user);
  }

  @Transactional()
  async update(id: number, updateUserDto: UpdateUserDto): Promise<UserEntity> {
    const user = UserEntity.from({ id: id, ...updateUserDto });
    const roles = await this.roleService.find(updateUserDto.roleIds);
    user.changeRoles(roles);
    return this.userRepository.save(user);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.userRepository.delete({ id: id });
  }
}
