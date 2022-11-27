import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { LocalStrategy } from '../strategy/local.strategy';
import { AuthServiceModule } from '../auth/module/auth-service.module';
import { AuthService } from '../auth/service/auth.service';
import { CustomJwtModule } from './custom-jwt.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from '../../../domain/user/entity/user.entity';
import { UserRoleEntity } from '../../../domain/user/entity/user-role.entity';

@Module({
  imports: [
    AuthServiceModule,
    PassportModule,

    // // AuthService
    // TypeOrmModule.forFeature([UserEntity, UserRoleEntity]),
    // CustomJwtModule,
  ],
  providers: [LocalStrategy], // AuthService,
})
export class LocalStrategyModule {}
