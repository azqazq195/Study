import { Module } from '@nestjs/common';
import { UserRoleAdminGuard } from './user-role-admin.guard';

@Module({
  imports: [UserRoleAdminGuard],
})
export class UserRoleGuardModule {}
