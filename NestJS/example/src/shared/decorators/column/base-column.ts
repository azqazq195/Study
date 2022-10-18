import { Column, ColumnOptions, ColumnType } from 'typeorm';

interface RequiredColumnOptions extends ColumnOptions {
  type: ColumnType;
  name: string;
  comment: string;
}

export function BaseColumn(options: RequiredColumnOptions): PropertyDecorator {
  return Column(options);
}
