import { SetMetadata } from '@nestjs/common';
import { USER_ROLE } from '../../user/entity/type/enum/user-role.enum';

export const Roles = (...roles: USER_ROLE[]) => SetMetadata('roles', roles);
