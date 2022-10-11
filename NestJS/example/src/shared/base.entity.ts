import { CreateDateColumn, DeleteDateColumn, UpdateDateColumn } from 'typeorm';
export class BaseEntity {
  @CreateDateColumn({
    type: 'datetime',
    name: 'created_at',
    comment: '생성일시',
  })
  readonly createdAt!: Date;

  @UpdateDateColumn({
    type: 'datetime',
    name: 'updated_at',
    comment: '수정일시',
  })
  readonly updatedAt!: Date;

  @DeleteDateColumn({
    type: 'datetime',
    name: 'deleted_at',
    nullable: true,
    comment: '삭제일시',
  })
  readonly deletedAt!: Date | null;
}
