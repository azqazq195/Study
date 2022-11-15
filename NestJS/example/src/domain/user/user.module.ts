import { Module } from '@nestjs/common';
import { UserController } from './controller/user.controller';
import { UserEntity } from './entity/user.entity';
import { UserService } from './service/user.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserRoleEntity } from './entity/user-role.entity';
import { RoleService } from './service/role.service';

@Module({
  imports: [TypeOrmModule.forFeature([UserEntity, UserRoleEntity])],
  providers: [UserService, RoleService],
  controllers: [UserController],
  exports: [UserService],
})
export class UserModule {}
