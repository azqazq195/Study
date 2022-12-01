import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule } from '@nestjs/config';
import { UserEntity } from '../../domain/user/user/entity/user.entity';
import { ProductEntity } from '../../domain/product/entity/product.entity';
import { BrandEntity } from '../../domain/brand/entity/brand.entity';
import { RoleEntity } from '../../domain/user/role/entity/role.entity';
import { PermissionEntity } from '../../domain/user/permission/entity/permission.entity';
import { UserRoleEntity } from '../../domain/user/user/entity/user-role.entity';
import { UserRolePermissionEntity } from '../../domain/user/user/entity/user-role-permission.entity';

@Module({
  imports: [
    ConfigModule.forRoot({}),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: process.env.DATABASE_HOST,
      port: Number(process.env.DATABASE_PORT),
      username: process.env.DATABASE_USER,
      password: process.env.DATABASE_PASSWORD,
      database: process.env.DATABASE_NAME,
      entities: [
        UserEntity,
        UserRoleEntity,
        UserRolePermissionEntity,
        RoleEntity,
        PermissionEntity,
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
