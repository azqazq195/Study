import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { LocalStrategy } from '../strategy/local.strategy';
import { AuthServiceModule } from '../auth/module/auth-service.module';

@Module({
  imports: [
    AuthServiceModule,
    PassportModule,

    // // AuthService
    // TypeOrmModule.forFeature([UserEntity, RoleEntity]),
    // CustomJwtModule,
  ],
  providers: [LocalStrategy], // AuthService,
})
export class LocalStrategyModule {}
