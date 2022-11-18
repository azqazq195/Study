import { Module } from '@nestjs/common';
import { JwtStrategyModule } from './module/jwt-strategy.module';
import { LocalStrategyModule } from './module/local-strategy.module';
import { APP_GUARD } from '@nestjs/core';
import { JwtAuthGuard } from './guard/jwt-auth.guard';
import { RoleGuard } from './guard/role.guard';
import { AuthServiceModule } from './auth/module/auth-service.module';

@Module({
  imports: [JwtStrategyModule, LocalStrategyModule],
  providers: [
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
})
export class GlobalSecurityModule {}
