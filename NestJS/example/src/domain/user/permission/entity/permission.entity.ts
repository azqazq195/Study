import { Entity, ManyToOne, OneToMany, PrimaryGeneratedColumn } from 'typeorm';
import { DATABASE_ENGINE } from '../../../../shared/config/configuration';
import { BaseColumn } from '../../../../shared/decorator/column.decorator';
import { Permission } from '../enum/permission.enum';
import { UserRoleEntity } from '../../user/entity/user-role.entity';
import { UserRolePermissionEntity } from '../../user/entity/user-role-permission.entity';

@Entity({
  name: 'permissions',
  engine: DATABASE_ENGINE,
})
export class PermissionEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'value',
    length: 100,
    comment: 'value',
  })
  readonly value: Permission;

  @OneToMany(
    () => UserRolePermissionEntity,
    (userRolePermission) => userRolePermission.permission,
  )
  readonly userRolePermissions: UserRolePermissionEntity[];
}
