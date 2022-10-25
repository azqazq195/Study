import { Body, Controller, Module, Post } from '@nestjs/common';
import { CreateEntityDto } from '../dto/create-entity.dto';

export function buildControllerModule(): any {
  @Controller('/test')
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  class CustomController<T> {
    @Post()
    createOne<M>(@Body() dto: T): string {
      console.log(dto instanceof CreateEntityDto);
      console.log(dto);
      console.log(dto as CreateEntityDto);
      return 'good';
    }
  }

  @Module({
    controllers: [CustomController<CreateEntityDto>],
  })
  class CustomControllerModule {}

  return CustomControllerModule;
}
