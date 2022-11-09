import { Entity, ManyToMany, PrimaryGeneratedColumn } from 'typeorm';
import { BaseColumn } from '../../../shared/decorators/column/base-column';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseTimeEntity } from '../../../shared/base/entity/base-time.entity';
import { UserEntity } from './user.entity';

@Entity({
  name: 'role',
  engine: DATABASE_ENGINE,
})
export class RoleEntity extends BaseTimeEntity {
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
  readonly name!: string;

  @ManyToMany(() => UserEntity, (user) => user.roles, {
    nullable: true,
    lazy: false,
  })
  readonly users!: UserEntity[];
}
