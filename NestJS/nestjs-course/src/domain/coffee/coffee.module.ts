import { Injectable, Module } from '@nestjs/common';
import { CoffeeController } from './controller/coffee.controller';
import { CoffeeService } from './service/coffee.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Coffee } from './entity/coffee.entity';
import { Flavor } from './entity/flavor.entity';
import { COFFEE_BRANDS } from './constants/coffee.constants';
import { DataSource } from 'typeorm';

@Injectable()
export class CoffeeBrandsFactory {
  create() {
    return ['buddy brew', 'nescafe'];
  }
}

@Module({
  imports: [TypeOrmModule.forFeature([Coffee, Flavor])],
  controllers: [CoffeeController],
  providers: [
    CoffeeService,
    {
      provide: COFFEE_BRANDS,
      useFactory: async (dataSource: DataSource): Promise<string[]> => {
        // const coffee = await dataSource.query(`SELECT *
        //                                        FROM COFFEE`);
        return Promise.resolve(['buddy brew', 'nescafe']);
      },
      inject: [DataSource],
    },
  ],
  exports: [CoffeeService],
})
export class CoffeeModule {}
