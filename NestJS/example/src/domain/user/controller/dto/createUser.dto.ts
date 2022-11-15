import { IsArray, IsNumber, IsString } from 'class-validator';

export class CreateUserDto {
  @IsString()
  readonly userId: string;

  @IsString()
  readonly password: string;

  @IsString()
  readonly name: string;

  @IsNumber()
  readonly age: number;

  @IsArray()
  readonly roleIds: number[];
}
