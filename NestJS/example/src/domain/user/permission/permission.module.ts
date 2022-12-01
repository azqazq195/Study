import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PermissionEntity } from './entity/permission.entity';
import { PermissionService } from './service/permission.service';

@Module({
  imports: [TypeOrmModule.forFeature([PermissionEntity])],
  providers: [PermissionService],
  exports: [PermissionService],
})
export class PermissionModule {}
