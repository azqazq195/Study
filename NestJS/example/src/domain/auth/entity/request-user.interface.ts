import { USER_ROLE } from '../../user/entity/type/enum/user-role.enum';

export interface RequestUser {
  id: number;
  roleIds: USER_ROLE[];
  name: string;
}
