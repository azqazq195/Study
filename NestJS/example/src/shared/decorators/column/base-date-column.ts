import {
  ColumnOptions,
  ColumnType,
  CreateDateColumn,
  DeleteDateColumn,
  UpdateDateColumn,
} from 'typeorm';

interface RequiredBaseDateColumnOptions extends ColumnOptions {
  type: ColumnType;
  name: string;
  comment: string;
  nullable?: boolean;
}

export function CreatedAtBaseColumn(
  options?: RequiredBaseDateColumnOptions,
): ReturnType<typeof CreateDateColumn> {
  return CreateDateColumn({
    type: 'datetime',
    name: 'created_at',
    comment: '생성일시',
    ...options,
  });
}

export function UpdatedAtBaseColumn(
  options?: RequiredBaseDateColumnOptions,
): ReturnType<typeof UpdateDateColumn> {
  return UpdateDateColumn({
    type: 'datetime',
    name: 'updated_at',
    comment: '수정일시',
    ...options,
  });
}

export function DeletedAtBaseColumn(
  options?: RequiredBaseDateColumnOptions,
): ReturnType<typeof DeleteDateColumn> {
  return DeleteDateColumn({
    type: 'datetime',
    name: 'deleted_at',
    comment: '삭제일시',
    nullable: true,
    ...options,
  });
}
