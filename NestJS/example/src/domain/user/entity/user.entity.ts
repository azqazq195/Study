import { Entity, JoinTable, ManyToMany, PrimaryGeneratedColumn } from 'typeorm';
import { plainToClass } from 'class-transformer';
import { UserResource } from './type/user.type';
import { BaseColumn } from '../../../shared/decorators/column/base-column';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../shared/base/entity/base-time.entity';
import { UserRoleEntity } from './user-role.entity';
import { Mutable } from '../../../shared/utils/mutable.type';

@Entity({
  name: 'user',
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
    name: 'first-name',
    length: 3,
    comment: 'first-name',
  })
  readonly firstName!: string;

  @BaseColumn({
    type: 'varchar',
    name: 'last-name',
    length: 3,
    comment: 'last-name',
  })
  readonly lastName!: string;

  @BaseColumn({
    type: 'int',
    name: 'age',
    comment: '나이',
  })
  readonly age!: number;

  @ManyToMany(() => UserRoleEntity, (role) => role.users, {
    nullable: true,
    lazy: false,
  })
  @JoinTable()
  readonly roles!: UserRoleEntity[];

  public addRole(role: UserRoleEntity): void {
    this.roles.push(role);
  }

  public changeRoles(roles: UserRoleEntity[]): void {
    const mutableThis = this as Mutable<UserEntity>;
    mutableThis.roles = roles;
  }

  static from(args: Partial<UserEntity>) {
    return plainToClass(UserEntity, args);
  }
}
