import { Injectable } from '@nestjs/common';
import { AuthService } from '../auth/service/auth.service';
import { RequestUser } from '../auth/interface/request-user.interface';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy } from 'passport-local';
import { ContextIdFactory, ModuleRef } from '@nestjs/core';

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy) {
  constructor(
    private moduleRef: ModuleRef, // private readonly authService: AuthService,
  ) {
    console.log('>> local strategy constructor <<');
    super({
      passReqToCallback: true,
    });
  }

  async validate(
    request: Request,
    userId: string,
    password: string,
  ): Promise<RequestUser> {
    console.log('local strategy validate');
    const contextId = ContextIdFactory.getByRequest(request);
    const authService = await this.moduleRef.resolve(AuthService, contextId);
    return authService.validateUser(userId, password);
  }
}
