import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeController } from './domain/coffee/controller/coffee.controller';
import { CoffeeService } from './domain/coffee/service/coffee.service';

@Module({
  imports: [],
  controllers: [AppController, CoffeeController],
  providers: [AppService, CoffeeService],
})
export class AppModule {}
