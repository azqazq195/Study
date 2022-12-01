import { Module } from '@nestjs/common';
import { UserController } from './user/controller/user.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserEntity } from './user/entity/user.entity';
import { UserService } from './user/service/user.service';
import { PermissionModule } from './permission/permission.module';
import { RoleModule } from './role/role.module';
import { UserRoleEntity } from './user/entity/user-role.entity';
import { UserRolePermissionEntity } from './user/entity/user-role-permission.entity';
import { UserRoleService } from './user/service/user-role.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      UserEntity,
      UserRoleEntity,
      UserRolePermissionEntity,
    ]),
    RoleModule,
    PermissionModule,
  ],
  providers: [UserService, UserRoleService],
  controllers: [UserController],
})
export class UserModule {}
