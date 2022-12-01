import {
  Entity,
  JoinColumn,
  ManyToOne,
  OneToMany,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { UserEntity } from './user.entity';
import { RoleEntity } from '../../role/entity/role.entity';
import { UserRolePermissionEntity } from './user-role-permission.entity';
import { plainToClass } from 'class-transformer';

@Entity({
  name: 'users_roles',
})
export class UserRoleEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id?: number;

  @ManyToOne(
    // line break
    () => UserEntity,
    (user) => user.userRoles,
    { primary: true },
  )
  readonly user: UserEntity;

  @ManyToOne(
    // line break
    () => RoleEntity,
    (role) => role.userRoles,
    { primary: true },
  )
  readonly role: RoleEntity;

  @OneToMany(
    // line break
    () => UserRolePermissionEntity,
    (userRolePermission) => userRolePermission.userRole,
    {
      cascade: ['remove'],
      nullable: false,
      lazy: false,
    },
  )
  readonly userRolePermissions: UserRolePermissionEntity[];

  static from(args: Partial<UserRoleEntity>) {
    return plainToClass(UserRoleEntity, args);
  }
}
