import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeModule } from './domain/coffee/coffee.module';
import { DatabaseModule } from './common/database/database.module';
import { PipeModule } from './common/pipe/pipe.module';
import { EventModule } from './domain/event/event.module';
import { CoffeeRatingModule } from './domain/coffee-rating/coffee-rating.module';

@Module({
  imports: [
    PipeModule,
    DatabaseModule,
    CoffeeModule,
    EventModule,
    CoffeeRatingModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
