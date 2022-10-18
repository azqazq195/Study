import { Entity, PrimaryGeneratedColumn } from 'typeorm';
import { plainToClass } from 'class-transformer';
import { UserResource } from './type/user.type';
import { BaseColumn } from '../../../shared/decorators/column/base-column';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../shared/base/entity/base-time.entity';

@Entity({
  name: 'users',
  synchronize: false,
  engine: DATABASE_ENGINE,
})
export class UserEntity extends BaseTimeEntity implements UserResource {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'name',
    length: 3,
    comment: 'first-name',
  })
  readonly firstName!: string;

  @BaseColumn({
    type: 'varchar',
    name: 'name',
    length: 3,
    comment: 'last-name',
  })
  readonly lastName!: string;

  @BaseColumn({
    type: 'int',
    name: 'age',
    length: 3,
    comment: '나이',
  })
  readonly age!: number;

  static from(args: Partial<UserEntity>) {
    return plainToClass(UserEntity, args);
  }
}
