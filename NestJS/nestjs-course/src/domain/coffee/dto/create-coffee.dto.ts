import { IsNotEmpty, IsString } from 'class-validator';

export class CreateCoffeeDto {
  @IsString()
  readonly name: string;

  @IsString()
  readonly brand: string;

  @IsNotEmpty()
  @IsString({ each: true })
  readonly flavors: string[];
}
