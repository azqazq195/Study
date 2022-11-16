import { Entity, JoinTable, ManyToMany, PrimaryGeneratedColumn } from 'typeorm';
import { plainToClass } from 'class-transformer';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../shared/base/entity/base-time.entity';
import { UserRoleEntity } from './user-role.entity';
import { Mutable } from '../../../shared/utils/mutable.type';
import { BaseColumn } from '../../../shared/decorator/column.decorator';

@Entity({
  name: 'user',
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
    name: 'userId',
    length: 15,
    comment: 'user id',
    unique: true,
  })
  readonly userId: string;

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

  @ManyToMany(() => UserRoleEntity, (role) => role.users, {
    nullable: true,
    lazy: false,
  })
  @JoinTable()
  readonly roles: UserRoleEntity[];

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
