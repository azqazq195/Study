import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { RoleEntity } from './entity/role.entity';
import { RoleService } from './service/role.service';
import { PermissionModule } from '../permission/permission.module';

@Module({
  imports: [TypeOrmModule.forFeature([RoleEntity]), PermissionModule],
  providers: [RoleService],
  exports: [RoleService],
})
export class RoleModule {}
