import { IsString } from 'class-validator';

export class CreateBDto {
  @IsString()
  readonly name;
}
