import { Module } from '@nestjs/common';
import { AuthController } from '../controller/auth.controller';
import { AuthServiceModule } from './auth-service.module';

@Module({
  imports: [AuthServiceModule],
  controllers: [AuthController],
})
export class AuthModule {}
