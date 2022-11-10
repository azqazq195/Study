import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { USER_ROLE } from '../../user/entity/type/enum/user-role.enum';
import { RequestUser } from '../entity/request-user.interface';

@Injectable()
export class UserRoleCustomerGuard implements CanActivate {
  constructor() {}
  canActivate(context: ExecutionContext): boolean {
    const request = context.switchToHttp().getRequest();
    return this.isCustomer(request.user);
  }

  public isCustomer(user: RequestUser): boolean {
    return [USER_ROLE.화훼업계종사자, USER_ROLE.구매자, USER_ROLE.판매자].some(
      (value) => user.roleIds.includes(value),
    );
  }
}
