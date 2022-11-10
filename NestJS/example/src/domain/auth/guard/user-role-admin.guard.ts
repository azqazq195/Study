import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { USER_ROLE } from '../../user/entity/type/enum/user-role.enum';
import { RequestUser } from '../entity/request-user.interface';

@Injectable()
export class UserRoleAdminGuard implements CanActivate {
  canActivate(context: ExecutionContext): boolean {
    const request = context.switchToHttp().getRequest();
    return this.isAdmin(request.user);
  }

  public isAdmin(user: RequestUser): boolean {
    return [USER_ROLE.최고관리자, USER_ROLE.오늘의꽃관리자].some((value) =>
      user.roleIds.includes(value),
    );
  }
}
