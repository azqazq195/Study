import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule } from '@nestjs/config';
import { UserEntity } from '../../domain/user/user/entity/user.entity';
import { ProductEntity } from '../../domain/product/entity/product.entity';
import { BrandEntity } from '../../domain/brand/entity/brand.entity';
import { UserRoleEntity } from '../../domain/user/user/entity/user-role.entity';
import { UserRolePermissionEntity } from '../../domain/user/user/entity/user-role-permission.entity';
import { AEntity } from '../../domain/test/entity/a.entity';
import { BEntity } from '../../domain/test/entity/b.entity';
import { DataSource } from 'typeorm';
import { addTransactionalDataSource } from 'typeorm-transactional';

@Module({
  imports: [
    ConfigModule.forRoot(),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: process.env.DATABASE_HOST,
      port: Number(process.env.DATABASE_PORT),
      username: process.env.DATABASE_USER,
      password: process.env.DATABASE_PASSWORD,
      database: process.env.DATABASE_NAME,
      entities: [
        AEntity,
        BEntity,

        UserEntity,
        UserRoleEntity,
        UserRolePermissionEntity,

        ProductEntity,
        BrandEntity,
      ],
      // domain 폴더에 위치하지 않으면 못읽나?
      // autoLoadEntities: true,
      synchronize: true,
      // logging: true,
      dropSchema: true,
      connectTimeout: 3000,
    }),
  ],
})
export class GlobalDatabaseModule {}
