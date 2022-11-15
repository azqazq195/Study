import { UserEntity } from '../../user/entity/user.entity';
import { UserRoleEntity } from '../../user/entity/user-role.entity';

export interface RequestUser
  extends Pick<UserEntity, 'id' | 'userId' | 'name'> {
  roles: UserRole[];
}

type UserRole = Pick<UserRoleEntity, 'id' | 'name'>;
