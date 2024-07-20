import { BadRequestException, Injectable } from '@nestjs/common';
import { UserEntity } from '../entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { CreateUserDto } from '../controller/dto/create-user.dto';
import { UpdateUserDto } from '../controller/dto/update-user.dto';
import { UserRoleService } from './user-role.service';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    private readonly userRepository: Repository<UserEntity>,
    private readonly userRoleService: UserRoleService,
  ) {}

  findAll(): Promise<UserEntity[]> {
    return this.userRepository.find();
  }

  async findOneById(id: number): Promise<UserEntity> {
    return await this.userRepository.findOneOrFail({
      where: { id: id },
      relations: ['userRoles', 'userRoles.userRolePermissions'],
    });
  }

  async insert(createUserDto: CreateUserDto): Promise<UserEntity> {
    const user = this.userRepository.create({ ...createUserDto });
    return await this.userRepository.save(user);
  }

  async update(id: number, updateUserDto: UpdateUserDto): Promise<UserEntity> {
    // * orphaned row action 은 insert 이후 진행된다.
    // * (user, role) 은 unique 이므로 지워지기 전에 insert 가 되면 DUPLICATED 에러 발생.
    // * insert 전에 role 을 지워주어야 한다.
    await this.userRoleService.deleteAllByUserId(id);
    const user = this.userRepository.create({ id, ...updateUserDto });
    return await this.userRepository.save(user);
  }

  async delete(id: number): Promise<void> {
    await this.userRepository.delete({ id: id });
  }

  async isExist(username: string): Promise<boolean> {
    const check = await this.userRepository.findOne({
      where: { username: username },
    });
    return !!check;
  }
}
