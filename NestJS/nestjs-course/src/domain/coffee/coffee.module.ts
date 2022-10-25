import { Module } from '@nestjs/common';
import { CoffeeController } from './controller/coffee.controller';
import { CoffeeService } from './service/coffee.service';

@Module({
  controllers: [CoffeeController],
  providers: [CoffeeService],
})
export class CoffeeModule {}
