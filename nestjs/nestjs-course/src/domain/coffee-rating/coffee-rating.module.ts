import { Module } from '@nestjs/common';
import { CoffeeRatingService } from './service/coffee-rating.service';
import { CoffeeModule } from '../coffee/coffee.module';

@Module({
  imports: [CoffeeModule],
  providers: [CoffeeRatingService],
})
export class CoffeeRatingModule {}
