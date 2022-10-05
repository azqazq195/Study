import { Inject, Injectable } from '@nestjs/common';
import { UserEntity } from '../entity/user.entity';
import { Connection, Repository, Transaction } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Transactional } from 'typeorm-transactional-cls-hooked';

type Entity = UserEntity;

const Entity = UserEntity;

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(Entity)
    private readonly userRepository: Repository<Entity>,
    private readonly connection: Connection,
  ) {}

  findAll(): Promise<Entity[]> {
    return this.userRepository.find();
  }

  findOne(id: number): Promise<Entity> {
    return this.userRepository.findOneOrFail({ id: id });
  }

  @Transactional()
  async save(user: Entity): Promise<Entity> {
    return this.userRepository.save(user);
  }

  @Transactional()
  async delete(id: number): Promise<void> {
    await this.userRepository.delete({ id: id });
  }
}
