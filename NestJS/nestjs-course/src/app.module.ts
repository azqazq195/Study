import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeModule } from './domain/coffee/coffee.module';
import { DatabaseModule } from './shared/database/database.module';
import { PipeModule } from './shared/pipe/pipe.module';

@Module({
  imports: [PipeModule, DatabaseModule, CoffeeModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
