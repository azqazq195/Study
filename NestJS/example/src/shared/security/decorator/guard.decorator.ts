import { SetMetadata } from '@nestjs/common';
import { Role } from '../entity/enum/role.enum';

export const ROLE_KEY = 'role';
export const Roles = (...roles: Role[]) => SetMetadata(ROLE_KEY, roles);

export const IS_PUBLIC_KEY = 'isPublic';
export const Public = () => SetMetadata(IS_PUBLIC_KEY, true);
