import { Module } from '@nestjs/common';
import { CoffeeController } from './controller/coffee.controller';
import { CoffeeService } from './service/coffee.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Coffee } from './entity/coffee.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Coffee])],
  controllers: [CoffeeController],
  providers: [CoffeeService],
})
export class CoffeeModule {}
