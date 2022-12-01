import { Entity, OneToMany, PrimaryGeneratedColumn } from 'typeorm';
import { DATABASE_ENGINE } from '../../../../shared/config/configuration';
import { BaseColumn } from '../../../../shared/decorator/column.decorator';
import { Role } from '../enum/role.enum';
import { UserRoleEntity } from '../../user/entity/user-role.entity';

@Entity({
  name: 'roles',
  engine: DATABASE_ENGINE,
})
export class RoleEntity {
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
  readonly value: Role;

  @OneToMany(() => UserRoleEntity, (userRole) => userRole.role)
  readonly userRoles: UserRoleEntity[];
}
