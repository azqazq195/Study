import { Injectable } from '@nestjs/common';
import { AuthService } from '../auth/service/auth.service';
import { RequestUser } from '../auth/interface/request-user.interface';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy } from 'passport-local';
import { ContextIdFactory, ModuleRef } from '@nestjs/core';

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy) {
  constructor(private moduleRef: ModuleRef) {
    super({
      passReqToCallback: true,
    });
  }

  async validate(
    request: Request,
    username: string,
    password: string,
  ): Promise<RequestUser> {
    const contextId = ContextIdFactory.getByRequest(request);
    const authService = await this.moduleRef.resolve(AuthService, contextId, {
      strict: false,
    });
    return authService.validateUser(username, password);
  }
}
