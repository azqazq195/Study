import {
  ArrayNotEmpty,
  IsArray,
  IsNumber,
  IsString,
  ValidateNested,
} from 'class-validator';
import { OmitType } from '@nestjs/mapped-types';
import { UserEntity } from '../../entity/user.entity';
import { Type } from 'class-transformer';
import { CreateUserRoleDto } from './create-user-role.dto';

export class CreateUserDto extends OmitType(UserEntity, ['id', 'userRoles']) {
  @IsString()
  readonly username: string;

  @IsString()
  readonly password: string;

  @IsString()
  readonly name: string;

  @IsNumber()
  readonly age: number;

  @IsArray()
  @ArrayNotEmpty()
  @ValidateNested()
  @Type(() => CreateUserRoleDto)
  readonly roleDtos: CreateUserRoleDto[];
}
