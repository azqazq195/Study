import {
  BaseEntity,
  BeforeInsert,
  Entity,
  OneToMany,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { DATABASE_ENGINE } from '../../../shared/config/configuration';
import { BaseColumn } from '../../../shared/decorator/column.decorator';
import { BEntity } from './b.entity';

@Entity({
  name: 'tb_a',
  engine: DATABASE_ENGINE,
})
export class AEntity extends BaseEntity {
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

  @OneToMany(
    // line break
    () => BEntity,
    (b) => b.a,
    {
      // * true: find 에서 relation 설정 없이도 불러올 수 있다.
      // * false: 저장은 정상적으로 진행되나, find 시 별도로 relation 을 지정해 주어야 한다.
      // * N + 1 문제가 발생하지 않으며, eager 로드 시 lazy 로드를 실행하지 않는다. bs 를 조회하는 경우 undefined.
      eager: true,

      // * lazy 는 jpa 처럼 동작하지 않는 듯.
      // ! 사용하지 말자.
      // lazy: false,
      cascade: true,
    },
  )
  readonly bs: BEntity[];

  @BeforeInsert()
  private async deleteBs() {
    console.log('before insert');
    for (const b of this.bs) {
      await b.remove();
    }
  }
}
