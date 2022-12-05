import { SetMetadata } from '@nestjs/common';
import { Role } from '../../../domain/user/user/entity/enum/role.enum';

export const ROLE_KEY = 'role';
export const Roles = (...roles: Role[]) => SetMetadata(ROLE_KEY, roles);

export const OKKOT_KEY = 'okkot';
export const Okkot = () => SetMetadata(OKKOT_KEY, true);

export const IS_PUBLIC_KEY = 'isPublic';
export const Public = () => SetMetadata(IS_PUBLIC_KEY, true);

export const CURRENT_USER = 'isPublic';
export const CurrentUser = () =>
  SetMetadata(CURRENT_USER, { id: 1, role_id: 2 });
