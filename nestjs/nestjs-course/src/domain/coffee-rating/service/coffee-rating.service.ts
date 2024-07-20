import { Injectable } from '@nestjs/common';
import { CoffeeService } from '../../coffee/service/coffee.service';

@Injectable()
export class CoffeeRatingService {
  constructor(private readonly coffeeService: CoffeeService) {}
}
