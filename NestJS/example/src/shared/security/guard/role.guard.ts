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
import { ROLE_KEY } from '../decorator/guard.decorator';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(@Inject(Reflector) private reflector: Reflector) {}

  canActivate(context: ExecutionContext): boolean {
    const requiredRoles = this.getRequiredRoles(context);
    if (!requiredRoles) {
      return true;
    }

    this.validateRoles(requiredRoles, context.switchToHttp().getRequest().user);

    return true;
  }

  private getRequiredRoles(context: ExecutionContext): USER_ROLE[] {
    return this.reflector.getAllAndOverride<USER_ROLE[]>(ROLE_KEY, [
      context.getHandler(),
      context.getClass(),
    ]);
  }

  private validateRoles(roles: USER_ROLE[], user: RequestUser): void {
    if (
      !roles.some((value) => user.roles.some((roles) => roles.id === value))
    ) {
      throw new ForbiddenException('권한이 없습니다.');
    }
  }
}
