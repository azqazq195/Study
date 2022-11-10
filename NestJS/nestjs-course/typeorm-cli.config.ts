import { DataSource } from 'typeorm';
import { Coffee } from './src/domain/coffee/entity/coffee.entity';
import { Flavor } from './src/domain/coffee/entity/flavor.entity';

export default new DataSource({
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: '',
  database: 'test',
  entities: [Coffee, Flavor],
  migrations: [],
});
