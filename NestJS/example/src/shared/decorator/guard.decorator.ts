import { SetMetadata } from '@nestjs/common';
import { USER_ROLE } from '../../domain/user/entity/type/enum/user-role.enum';

export const ROLE_KEY = 'role';
export const Roles = (...roles: USER_ROLE[]) => SetMetadata(ROLE_KEY, roles);

export const IS_PUBLIC_KEY = 'isPublic';
export const Public = () => SetMetadata(IS_PUBLIC_KEY, true);
