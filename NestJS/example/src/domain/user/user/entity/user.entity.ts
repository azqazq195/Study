import {
  DeepPartial,
  Entity,
  OneToMany,
  OneToOne,
  PrimaryGeneratedColumn,
  Repository,
} from 'typeorm';
import { DATABASE_ENGINE } from '../../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../../shared/base/entity/base-time.entity';
import { BaseColumn } from '../../../../shared/decorator/column.decorator';
import { UserRoleEntity } from './user-role.entity';
import { Mutable } from '../../../../shared/utils/mutable.type';
import { InjectRepository } from '@nestjs/typeorm';
import { plainToClass } from 'class-transformer';

@Entity({
  name: 'users',
  engine: DATABASE_ENGINE,
})
export class UserEntity extends BaseTimeEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'username',
    length: 15,
    comment: 'username',
    unique: true,
  })
  readonly username: string;

  @BaseColumn({
    type: 'varchar',
    name: 'password',
    length: 100,
    comment: 'password',
  })
  readonly password: string;

  @BaseColumn({
    type: 'varchar',
    name: 'name',
    length: 15,
    comment: 'name',
  })
  readonly name: string;

  @BaseColumn({
    type: 'int',
    name: 'age',
    comment: '나이',
  })
  readonly age: number;

  @OneToMany(
    // line break
    () => UserRoleEntity,
    (userRole) => userRole.user,
    {
      eager: true,
      cascade: true,
    },
  )
  readonly userRoles?: UserRoleEntity[];

  public setRoles(userRoles: UserRoleEntity[]): void {
    const mutableThis = this as Mutable<UserEntity>;
    mutableThis.userRoles = userRoles;
  }

  static from(args: DeepPartial<UserEntity>): UserEntity {
    return plainToClass(this, args);
  }
}
