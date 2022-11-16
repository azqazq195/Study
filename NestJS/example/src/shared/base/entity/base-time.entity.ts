import { DateTimeString } from '../../utils/dayjs';
import {
  CreatedAtBaseColumn,
  DeletedAtBaseColumn,
  UpdatedAtBaseColumn,
} from '../../decorator/column.decorator';

export class BaseTimeEntity {
  @CreatedAtBaseColumn({
    type: 'datetime',
    name: 'created_at',
    comment: '생성일시',
  })
  readonly createdAt: DateTimeString;
  @UpdatedAtBaseColumn({
    type: 'datetime',
    name: 'updated_at',
    comment: '수정일시',
  })
  readonly updatedAt: DateTimeString;
  @DeletedAtBaseColumn({
    type: 'datetime',
    name: 'deleted_at',
    nullable: true,
    comment: '삭제일시',
  })
  readonly deletedAt: DateTimeString | undefined;
}
