import { Module } from '@nestjs/common';
import { AuthService } from '../service/auth.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from '../../../../domain/user/entity/user.entity';
import { UserRoleEntity } from '../../../../domain/user/entity/user-role.entity';
import { CustomJwtModule } from '../../module/custom-jwt.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([UserEntity, UserRoleEntity]),
    CustomJwtModule,
  ],
  providers: [AuthService],
  exports: [AuthService],
})
export class AuthServiceModule {}
