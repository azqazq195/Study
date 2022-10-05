import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from '../domain/user/entity/user.entity';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '',
      database: 'test',
      // entities: [`${__dirname}/domain/user/entity/*.js`],
      entities: [UserEntity],
      synchronize: true,
      logging: true,
      dropSchema: true,
      connectTimeout: 3000,
    }),
  ],
})
export class DatabaseModule {}
