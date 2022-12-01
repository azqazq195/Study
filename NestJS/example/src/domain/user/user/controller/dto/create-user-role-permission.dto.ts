import { PickType } from '@nestjs/mapped-types';
import { PermissionEntity } from '../../../permission/entity/permission.entity';
import { IsString } from 'class-validator';
import { Permission } from '../../../permission/enum/permission.enum';

export class CreateUserRolePermissionDto extends PickType(PermissionEntity, [
  'id',
  'userRolePermissions',
]) {
  @IsString()
  readonly value: Permission;
}
