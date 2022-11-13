import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '',
      database: 'test',
      autoLoadEntities: true,
      synchronize: true,

      logging: true,
      connectTimeout: 3000,
      // dropSchema: true,
    }),
  ],
})
export class DatabaseModule {}

// @Module({})
// export class DatabaseModule {
//   static register(options: DataSourceOptions): DynamicModule {
//     return {
//       module: DatabaseModule,
//       providers: [
//         {
//           provide: 'CONNECTION',
//           useValue: new DataSource(options).initialize(),
//         },
//       ],
//     };
//   }
// }
