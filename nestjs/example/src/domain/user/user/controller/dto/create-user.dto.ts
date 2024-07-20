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
import { IsNotExistUsername } from './decorator/is-not-exist-username.decorator';

export class CreateUserDto extends OmitType(UserEntity, ['id', 'userRoles']) {
  @IsString()
  @IsNotExistUsername()
  readonly username: string;

  @IsString()
  readonly password: string;

  @IsString()
  readonly name: string;

  @IsNumber()
  readonly age: number;

  // * 하위 DTO 에서 Validation 을 진행하는 경우 @ValidatedNest 와 @Type 을 추가한다.
  @IsArray()
  @ArrayNotEmpty()
  @ValidateNested()
  @Type(() => CreateUserRoleDto)
  readonly userRoles: CreateUserRoleDto[];
}
