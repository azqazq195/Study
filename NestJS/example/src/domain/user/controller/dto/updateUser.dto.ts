import { UserEntity } from '../../entity/user.entity';
import { IsNumber, IsString } from 'class-validator';

export class UpdateUserDto {
  @IsNumber()
  readonly id!: number;

  @IsString()
  readonly firstName!: string;

  @IsString()
  readonly lastName!: string;

  @IsNumber()
  readonly age!: number;

  toUserEntity(): UserEntity {
    return UserEntity.from(this);
  }
}
