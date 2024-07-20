import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeModule } from './domain/coffee/coffee.module';
import { GlobalDatabaseModule } from './common/database/global-database.module';
import { GlobalPipeModule } from './common/pipe/global-pipe.module';
import { EventModule } from './domain/event/event.module';
import { CoffeeRatingModule } from './domain/coffee-rating/coffee-rating.module';
import { GlobalConfigModule } from './common/config/global-config.module';

@Module({
  imports: [
    GlobalConfigModule,
    GlobalPipeModule,
    GlobalDatabaseModule,

    CoffeeModule,
    EventModule,
    CoffeeRatingModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
