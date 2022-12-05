import {
  BaseEntity,
  Entity,
  Index,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseColumn } from '../../../shared/decorator/column.decorator';
import { AEntity } from './a.entity';

@Entity({
  name: 'tb_b',
  engine: DATABASE_ENGINE,
})
// * orphaned row actions 'delete' 는 entity 생성 후 소실된 객체에 대해 동작한다.
// * 생성시점에 index 로 인한 오류가 발생하기 때문에 index 를 사용할 수 없음.
@Index(['name', 'a'], { unique: true })
export class BEntity extends BaseEntity {
  @PrimaryGeneratedColumn({
    type: 'int',
    name: 'id',
    comment: 'id',
  })
  readonly id: number;

  @BaseColumn({
    type: 'varchar',
    name: 'name',
    length: 15,
    comment: 'name',
  })
  readonly name: string;

  @ManyToOne(
    // line break
    () => AEntity,
    (a) => a.bs,
    {
      onDelete: 'CASCADE',
      orphanedRowAction: 'delete',
    },
  )
  readonly a: AEntity;
}
