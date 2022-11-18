import { BaseTimeEntity } from '../../base/entity/base-time.entity';
import { DefaultEntity } from '../../database/decorator/entity.decorator';
import { Entity, ManyToMany, PrimaryGeneratedColumn } from 'typeorm';
import { BaseColumn } from '../../decorator/column.decorator';
import { UserEntity } from '../../../domain/user/entity/user.entity';
import { UserRoleEntity } from '../../../domain/user/entity/user-role.entity';
import { DATABASE_ENGINE } from '../../config/configuration';

// @DefaultEntity('permission')
@Entity({
  name: 'permission',
  engine: DATABASE_ENGINE,
})
export class RolePermissionEntity extends BaseTimeEntity {
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

  @ManyToMany(() => UserRoleEntity, (role) => role.permissions, {
    nullable: true,
    lazy: false,
  })
  readonly roles: UserRoleEntity[];
}
