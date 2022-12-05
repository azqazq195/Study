import { Permission } from '../../entity/enum/permission.enum';
import { OmitType } from '@nestjs/mapped-types';
import { UserRolePermissionEntity } from '../../entity/user-role-permission.entity';
import { IsPermission } from './decorator/is-permission.decorator';

export class CreateUserRolePermissionDto extends OmitType(
  UserRolePermissionEntity,
  ['id', 'userRole'],
) {
  @IsPermission()
  readonly permission: Permission;
}
