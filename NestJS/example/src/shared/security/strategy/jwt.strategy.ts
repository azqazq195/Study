import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from 'passport-jwt';
import { jwtConstant } from '../constant/constant';
import { RequestUser } from '../auth/interface/request-user.interface';
import { UserEntity } from '../../../domain/user/entity/user.entity';
import { AuthService } from '../auth/service/auth.service';

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
  constructor(private authService: AuthService) {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      ignoreExpiration: false,
      secretOrKey: jwtConstant.secret,
    });
    console.log('>> jwt strategy constructor <<');
  }

  async validate(user: RequestUser): Promise<UserEntity> {
    console.log('jwt strategy validate');
    return this.authService.findOneByUserId(user.userId);
  }
}
