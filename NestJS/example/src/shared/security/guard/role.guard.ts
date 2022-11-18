import {
  CanActivate,
  ExecutionContext,
  ForbiddenException,
  Inject,
  Injectable,
} from '@nestjs/common';
import { Reflector } from '@nestjs/core';
import { Role } from '../entity/enum/role.enum';
import { ROLE_KEY } from '../decorator/guard.decorator';
import { UserEntity } from '../../../domain/user/entity/user.entity';

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

  private getRequiredRoles(context: ExecutionContext): Role[] {
    return this.reflector.getAllAndOverride<Role[]>(ROLE_KEY, [
      context.getHandler(),
      context.getClass(),
    ]);
  }

  private validateRoles(roles: Role[], user: UserEntity): void {
    if (
      !roles.some((value) => user.roles.some((roles) => roles.id === value))
    ) {
      throw new ForbiddenException('권한이 없습니다.');
    }
  }
}
