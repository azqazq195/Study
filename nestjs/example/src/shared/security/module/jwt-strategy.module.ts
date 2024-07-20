import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { JwtStrategy } from '../strategy/jwt.strategy';
import { AuthServiceModule } from '../auth/module/auth-service.module';

@Module({
  imports: [AuthServiceModule, PassportModule],
  providers: [JwtStrategy],
})
export class JwtStrategyModule {}
