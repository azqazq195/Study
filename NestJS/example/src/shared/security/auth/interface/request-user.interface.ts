import { UserEntity } from '../../../../domain/user/entity/user.entity';

export type RequestUser = Pick<UserEntity, 'userId'>;
