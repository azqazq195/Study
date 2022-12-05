import {
  Entity,
  Index,
  ManyToOne,
  OneToMany,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { UserEntity } from './user.entity';
import { UserRolePermissionEntity } from './user-role-permission.entity';
import { plainToClass } from 'class-transformer';
import { Role } from './enum/role.enum';
import { BaseColumn } from '../../../../shared/decorator/column.decorator';
import { Permission } from './enum/permission.enum';

@Entity({
  name: 'users_roles',
})
@Index(['user', 'role'], { unique: true })
export class UserRoleEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'role',
    length: 20,
    comment: 'role',
  })
  readonly role: Role;

  @ManyToOne(
    // line break
    () => UserEntity,
    (user) => user.userRoles,
    {
      onDelete: 'CASCADE',
      orphanedRowAction: 'delete',
    },
  )
  readonly user: UserEntity;

  @OneToMany(
    // line break
    () => UserRolePermissionEntity,
    (userRolePermission) => userRolePermission.userRole,
    {
      eager: true,
      cascade: true,
    },
  )
  readonly userRolePermissions: UserRolePermissionEntity[];

  static from(role: Role, permissions: Permission[]): UserRoleEntity {
    const userRolePermissions = [];
    for (const permission of permissions) {
      userRolePermissions.push(UserRolePermissionEntity.from(permission));
    }

    return plainToClass(this, {
      role,
      userRolePermissions,
    });
  }
}
