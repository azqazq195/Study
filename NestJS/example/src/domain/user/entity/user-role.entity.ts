import { Entity, JoinTable, ManyToMany, PrimaryGeneratedColumn } from 'typeorm';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../shared/base/entity/base-time.entity';
import { UserEntity } from './user.entity';
import { BaseColumn } from '../../../shared/decorator/column.decorator';
import { RolePermissionEntity } from '../../../shared/security/entity/role-permission.entity';

@Entity({
  name: 'role',
  engine: DATABASE_ENGINE,
})
export class UserRoleEntity extends BaseTimeEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'name',
    length: 10,
    comment: 'name',
  })
  readonly name: string;

  @ManyToMany(() => UserEntity, (user) => user.roles, {
    nullable: true,
    lazy: false,
  })
  readonly users: UserEntity[];

  @ManyToMany(() => RolePermissionEntity, (permission) => permission.roles, {
    nullable: true,
    lazy: false,
  })
  @JoinTable()
  readonly permissions: RolePermissionEntity[];
}
