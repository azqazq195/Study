import {
  ArrayNotEmpty,
  IsArray,
  IsString,
  ValidateNested,
} from 'class-validator';
import { PickType } from '@nestjs/mapped-types';
import { Type } from 'class-transformer';
import { RoleEntity } from '../../../role/entity/role.entity';
import { Role } from '../../../role/enum/role.enum';
import { CreateUserRolePermissionDto } from './create-user-role-permission.dto';

export class CreateUserRoleDto extends PickType(RoleEntity, [
  'id',
  'userRoles',
]) {
  @IsString()
  readonly value: Role;

  @IsArray()
  @ArrayNotEmpty()
  @ValidateNested()
  @Type(() => CreateUserRolePermissionDto)
  readonly permissionDtos: CreateUserRolePermissionDto[];
}
