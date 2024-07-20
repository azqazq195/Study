import { UserEntity } from '../../../../domain/user/user/entity/user.entity';

export type RequestUser = Pick<UserEntity, 'username'>;
