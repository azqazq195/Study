import { Body, Controller, Post } from '@nestjs/common';
import { CreateEntityDto } from '../dto/create-entity.dto';

@Controller('/test')
export class TestController<T> {
  @Post()
  createOne(@Body() dto: T): string {
    console.log(dto instanceof CreateEntityDto);
    console.log(dto);
    console.log(dto as CreateEntityDto);
    return 'good';
  }
}
