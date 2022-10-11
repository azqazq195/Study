import { Injectable } from '@nestjs/common';
import { UserEntity } from '../entity/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(UserEntity)
    private readonly userRepository: Repository<UserEntity>,
  ) {}

  findAll(): Promise<UserEntity[]> {
    return this.userRepository.find();
  }

  findOne(id: number): Promise<UserEntity> {
    return this.userRepository.findOneOrFail({ id: id });
  }

  @Transactional()
  async save(user: UserEntity): Promise<UserEntity> {
    return this.userRepository.save(user);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.userRepository.delete({ id: id });
  }
}
