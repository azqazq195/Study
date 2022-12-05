import { Module } from '@nestjs/common';
import { GlobalDatabaseModule } from './shared/database/global-database.module';
import { UserModule } from './domain/user/user.module';
import { ProductModule } from './domain/product/product.module';
import { BrandModule } from './domain/brand/brand.module';
import { GlobalPipeModule } from './shared/pipe/global-pipe.module';
import { GlobalFilterModule } from './shared/filter/global-filter.module';
import { AuthModule } from './shared/security/auth/module/auth.module';
import { GlobalSecurityModule } from './shared/security/global-security.module';
import { TestModule } from './domain/test/test.module';

@Module({
  imports: [
    GlobalDatabaseModule,
    GlobalPipeModule,
    GlobalFilterModule,
    GlobalSecurityModule,

    UserModule,
    ProductModule,
    BrandModule,
    AuthModule,
    TestModule,
  ],
})
export class AppModule {}
