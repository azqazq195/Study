import { Module } from '@nestjs/common';
import { AuthService } from '../service/auth.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from '../../../../domain/user/user/entity/user.entity';
import { CustomJwtModule } from '../../module/custom-jwt.module';

@Module({
  imports: [TypeOrmModule.forFeature([UserEntity]), CustomJwtModule],
  providers: [AuthService],
  exports: [AuthService],
})
export class AuthServiceModule {}
