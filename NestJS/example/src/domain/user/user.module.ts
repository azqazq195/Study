import { Module } from '@nestjs/common';
import { UserController } from './controller/user.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from './entity/user.entity';
import { UserRoleEntity } from './entity/user-role.entity';
import { UserService } from './service/user.service';
import { UserRoleService } from './service/user-role.service';
import { AuthServiceModule } from '../../shared/security/auth/module/auth-service.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([UserEntity, UserRoleEntity]),
    AuthServiceModule,
  ],
  providers: [UserService, UserRoleService],
  controllers: [UserController],
})
export class UserModule {}
