import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { GlobalDatabaseModule } from './database/global-database.module';
import { UserModule } from './domain/user/user.module';
import { ProductModule } from './domain/product/product.module';
import { BrandModule } from './domain/brand/brand.module';
import { GlobalConfigModule } from './config/global-config.module';

@Module({
  imports: [GlobalDatabaseModule, UserModule, ProductModule, BrandModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
