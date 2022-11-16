import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';

import { jwtConstant } from './constant/constant';
import { JwtStrategy } from './strategy/jwt.strategy';
import { APP_GUARD } from '@nestjs/core';
import { JwtAuthGuard } from './guard/jwt-auth.guard';
import { PassportModule } from '@nestjs/passport';
import { RoleGuard } from './guard/role.guard';
import { UserModule } from '../../domain/user/user.module';
import { AuthService } from './auth/service/auth.service';
import { LocalStrategy } from './strategy/local.strategy';
import { AuthController } from './auth/controller/auth.controller';

@Module({
  imports: [
    UserModule,
    PassportModule,
    JwtModule.register({
      secret: jwtConstant.secret,
      signOptions: { expiresIn: '3600s' },
    }),
  ],
  providers: [
    JwtStrategy,
    LocalStrategy,
    AuthService,
    // jwt token 을 먼저 검사하고, role 을 검사한다.
    // exception filter 와 적용 순서가 다른점은 lifecycle 상 filter 는 빠져나가는 구조 인것 같다.
    {
      provide: APP_GUARD,
      useClass: JwtAuthGuard,
    },
    {
      provide: APP_GUARD,
      useClass: RoleGuard,
    },
  ],
  controllers: [AuthController],
})
export class GlobalSecurityModule {}
