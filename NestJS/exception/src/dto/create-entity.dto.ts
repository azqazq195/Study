import { IsInt, IsString } from 'class-validator';

export class CreateEntityDto {
  @IsString()
  readonly name: string;
  @IsInt()
  readonly age: number;
  @IsString()
  readonly phone: string;
}
