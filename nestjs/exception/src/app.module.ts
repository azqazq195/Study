import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { FilterModule } from './filter/filter.module';
import { PipeModule } from './pipe/pipe.module';
import { TestControllerModule } from './test/test.module';
import { CoffeesController } from './domain/coffees/coffees.controller';

@Module({
  imports: [FilterModule, PipeModule, TestControllerModule],
  controllers: [AppController, CoffeesController],
  providers: [AppService],
})
export class AppModule {}
