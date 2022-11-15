import { RequestUser } from '../../entity/request-user.interface';
import { USER_ROLE } from '../../../user/entity/type/enum/user-role.enum';
import { UserRoleAdminGuard } from '../user-role-admin.guard';

describe('user-role admin guard', () => {
  let adminGuard;

  beforeEach(() => {
    adminGuard = new UserRoleAdminGuard();
  });

  describe('isAdminRequestUser', () => {
    test('return true', () => {
      // * given
      const requestUser: RequestUser = {
        id: 1,
        roleIds: [USER_ROLE.오늘의꽃관리자, USER_ROLE.화훼업계종사자],
        name: '관리자',
      };

      // * when
      const result = adminGuard.isAdmin(requestUser);

      // * then
      expect(result).toBe(true);
    });

    test('return false', () => {
      // * given
      const requestUser: RequestUser = {
        id: 1,
        roleIds: [USER_ROLE.화훼업계종사자],
        name: '화훼업계종사자',
      };

      // * when
      const result = adminGuard.isAdmin(requestUser);

      // * then
      expect(result).toBe(false);
    });
  });
});
