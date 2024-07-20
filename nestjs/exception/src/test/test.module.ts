import { Module } from '@nestjs/common';
import { CreateEntityDto } from '../dto/create-entity.dto';
import { TestController } from './test.controller';

@Module({
  controllers: [TestController<CreateEntityDto>],
})
export class TestControllerModule {}
