import { Module } from '@nestjs/common';
import { UserController } from './user/controller/user.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from './user/entity/user.entity';
import { UserService } from './user/service/user.service';
import { UserRoleEntity } from './user/entity/user-role.entity';
import { UserRolePermissionEntity } from './user/entity/user-role-permission.entity';
import { UserRoleService } from './user/service/user-role.service';
import { UsernameValidator } from './user/controller/dto/decorator/is-not-exist-username.decorator';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      UserEntity,
      UserRoleEntity,
      UserRolePermissionEntity,
    ]),
  ],
  providers: [UserService, UserRoleService, UsernameValidator],
  controllers: [UserController],
})
export class UserModule {}
