import { Connection, createConnection } from 'typeorm';
import { UserEntity } from '../../domain/user/entity/user.entity';
import { ProductEntity } from '../../domain/product/entity/product.entity';
import { BrandEntity } from '../../domain/brand/entity/brand.entity';

export class TestHelper {
  private static _instance: TestHelper;

  public static get instance(): TestHelper {
    if (!this._instance) this._instance = new TestHelper();
    return this._instance;
  }

  private connection: Connection;

  public async setupTestDB() {
    this.connection = await createConnection({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '',
      database: 'test',
      entities: [UserEntity, ProductEntity, BrandEntity],
      synchronize: true,
      logging: true,
      dropSchema: true,
      connectTimeout: 3000,
    });
  }

  public async teardownTestDB() {
    await this.connection.close();
  }
}
