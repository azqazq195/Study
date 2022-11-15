import { Injectable } from '@nestjs/common';
import { Strategy } from 'passport-local';
import { PassportStrategy } from '@nestjs/passport';
import { AuthService } from '../service/auth.service';
import { RequestUser } from '../entity/request-user.interface';

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy) {
  constructor(private readonly authService: AuthService) {
    super();
  }

  async validate(userId: string, password: string): Promise<RequestUser> {
    return this.authService.validateUser(userId, password);
  }
}
