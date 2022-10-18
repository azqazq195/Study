import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { DatabaseModule } from './database/database.module';
import { UserModule } from './domain/user/user.module';
import { ProductModule } from './domain/product/product.module';
import { BrandModule } from './domain/brand/brand.module';
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [
    UserModule,
    ProductModule,
    BrandModule,

    DatabaseModule,
    ConfigModule.forRoot({
      isGlobal: true,
      envFilePath: '.local.env',
    }),
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
