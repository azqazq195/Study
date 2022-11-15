import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule } from '@nestjs/config';

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
      // entities: [UserEntity, ProductEntity, BrandEntity, UserRoleEntity],
      autoLoadEntities: true,
      synchronize: true,
      logging: true,
      // dropSchema: true,
      connectTimeout: 3000,
    }),
  ],
})
export class GlobalDatabaseModule {}
