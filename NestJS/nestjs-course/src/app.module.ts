import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CoffeeModule } from './domain/coffee/coffee.module';
import { GlobalValidationPipe } from './shared/pipe/global-validation.pipe';

@Module({
  imports: [CoffeeModule, GlobalValidationPipe],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
