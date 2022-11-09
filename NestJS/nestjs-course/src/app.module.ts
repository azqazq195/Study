import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeModule } from './domain/coffee/coffee.module';
import { GlobalValidationPipe } from './shared/pipe/global-validation.pipe';
import { DatabaseModule } from './shared/database/database.module';

@Module({
  imports: [GlobalValidationPipe, DatabaseModule, CoffeeModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
