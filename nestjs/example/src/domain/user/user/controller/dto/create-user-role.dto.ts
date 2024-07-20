import { Role } from '../../entity/enum/role.enum';
import { UserRoleEntity } from '../../entity/user-role.entity';
import { IsRole } from './decorator/is-role.decorator';
import { OmitType } from '@nestjs/mapped-types';
import { CreateUserRolePermissionDto } from './create-user-role-permission.dto';
import { ArrayNotEmpty, IsArray, ValidateNested } from 'class-validator';
import { Type } from 'class-transformer';

export class CreateUserRoleDto extends OmitType(UserRoleEntity, [
  'id',
  'user',
  'userRolePermissions',
]) {
  @IsRole()
  readonly role: Role;

  @IsArray()
  @ArrayNotEmpty()
  @ValidateNested()
  @Type(() => CreateUserRolePermissionDto)
  readonly userRolePermissions: CreateUserRolePermissionDto[];
}
