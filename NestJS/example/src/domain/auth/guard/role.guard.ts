import { Injectable, CanActivate, ExecutionContext } from '@nestjs/common';
import { Reflector } from '@nestjs/core';
import { RequestUser } from '../entity/request-user.interface';
import { USER_ROLE } from '../../user/entity/type/enum/user-role.enum';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private reflector: Reflector) {}

  canActivate(context: ExecutionContext): boolean {
    const roles = this.reflector.get<USER_ROLE[]>(
      'roles',
      context.getHandler(),
    );
    if (!roles) {
      return true;
    }
    const request = context.switchToHttp().getRequest();
    const user = request.user;
    return this.matchRoles(roles, user);
  }

  public matchRoles(roles: USER_ROLE[], user: RequestUser): boolean {
    return roles.some((value) => user.roleIds.includes(value));
  }
}
