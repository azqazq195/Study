import { Module } from '@nestjs/common';
import { AuthService } from '../service/auth.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from '../../../../domain/user/user/entity/user.entity';
import { RoleEntity } from '../../../../domain/user/role/entity/role.entity';
import { CustomJwtModule } from '../../module/custom-jwt.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([UserEntity, RoleEntity]),
    CustomJwtModule,
  ],
  providers: [AuthService],
  exports: [AuthService],
})
export class AuthServiceModule {}
