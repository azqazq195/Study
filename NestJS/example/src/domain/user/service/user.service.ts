import { BadRequestException, Injectable } from '@nestjs/common';
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

  findOneByUserId(userId: string): Promise<UserEntity> {
    return this.userRepository.findOne(
      { userId: userId },
      { relations: ['roles'] },
    );
  }

  @Transactional()
  async create(createUserDto: CreateUserDto): Promise<UserEntity> {
    const check = await this.userRepository.findOne({
      userId: createUserDto.userId,
    });
    if (check) {
      throw new BadRequestException('이미 존재하는 유저 아이디 입니다.');
    }

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
