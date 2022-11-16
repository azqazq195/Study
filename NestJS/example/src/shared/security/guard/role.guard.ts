import {
  CanActivate,
  ExecutionContext,
  ForbiddenException,
  Inject,
  Injectable,
} from '@nestjs/common';
import { Reflector } from '@nestjs/core';
import { USER_ROLE } from '../../../domain/user/entity/type/enum/user-role.enum';
import { RequestUser } from '../auth/interface/request-user.interface';
import { ROLE_KEY } from '../../decorator/guard.decorator';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(@Inject(Reflector) private reflector: Reflector) {}

  canActivate(context: ExecutionContext): boolean {
    const requiredRoles = this.reflector.getAllAndOverride<USER_ROLE[]>(
      ROLE_KEY,
      [context.getHandler(), context.getClass()],
    );
    if (!requiredRoles) {
      return true;
    }
    const request = context.switchToHttp().getRequest();
    const result = this.matchRoles(requiredRoles, request.user);

    if (!result) {
      throw new ForbiddenException('권한이 없습니다.');
    }

    return true;
  }

  public matchRoles(roles: USER_ROLE[], user: RequestUser): boolean {
    return roles.some((value) =>
      user.roles.some((roles) => roles.id === value),
    );
  }
}
