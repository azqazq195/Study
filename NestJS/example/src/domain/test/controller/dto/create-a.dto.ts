import { CreateBDto } from './create-b.dto';
import { ArrayNotEmpty, IsArray, IsString } from 'class-validator';

export class CreateADto {
  @IsString()
  readonly name;

  @IsArray()
  @ArrayNotEmpty()
  readonly bs: CreateBDto[];
}
