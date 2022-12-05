import { Entity, Index, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { UserRoleEntity } from './user-role.entity';
import { plainToClass } from 'class-transformer';
import { BaseColumn } from '../../../../shared/decorator/column.decorator';
import { Permission } from './enum/permission.enum';

@Entity({
  name: 'users_roles_permissions',
})
@Index(['userRole', 'permission'], { unique: true })
export class UserRolePermissionEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @ManyToOne(
    // line break
    () => UserRoleEntity,
    (userRole) => userRole.userRolePermissions,
    {
      onDelete: 'CASCADE',
      orphanedRowAction: 'delete',
    },
  )
  readonly userRole: UserRoleEntity;

  @BaseColumn({
    type: 'varchar',
    name: 'permission',
    length: 20,
    comment: 'permission',
  })
  readonly permission: Permission;

  static from(permission: Permission): UserRolePermissionEntity {
    return plainToClass(this, {
      permission: permission,
    });
  }
}
