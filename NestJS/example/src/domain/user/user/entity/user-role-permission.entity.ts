import { Entity, ManyToOne } from 'typeorm';
import { UserRoleEntity } from './user-role.entity';
import { PermissionEntity } from '../../permission/entity/permission.entity';
import { plainToClass } from 'class-transformer';

@Entity({
  name: 'users_roles_permissions',
})
export class UserRolePermissionEntity {
  @ManyToOne(
    // line break
    () => UserRoleEntity,
    (userRole) => userRole.userRolePermissions,
    {
      primary: true,
    },
  )
  readonly userRole?: UserRoleEntity;

  @ManyToOne(
    // line break
    () => PermissionEntity,
    (permission) => permission.userRolePermissions,
    {
      primary: true,
    },
  )
  readonly permission: PermissionEntity;

  static from(args: Partial<UserRolePermissionEntity>) {
    return plainToClass(this, args);
  }
}
