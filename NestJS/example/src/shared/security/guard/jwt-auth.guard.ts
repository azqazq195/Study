import { AuthGuard } from '@nestjs/passport';
import {
  ExecutionContext,
  Inject,
  UnauthorizedException,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { Reflector } from '@nestjs/core';
import { IS_PUBLIC_KEY } from '../decorator/guard.decorator';

export class JwtAuthGuard extends AuthGuard('jwt') {
  constructor(@Inject(Reflector) private readonly reflector: Reflector) {
    super();
  }

  canActivate(
    context: ExecutionContext,
  ): boolean | Promise<boolean> | Observable<boolean> {
    console.log('jwt auth guard');
    if (this.isPublic(context)) {
      console.log('jwt auth guard is public');
      return true;
    }

    this.validateToken(context);

    return super.canActivate(context);
  }

  handleRequest(err, user) {
    if (err || !user) {
      throw err || new UnauthorizedException('유효하지 않은 토큰입니다.');
    }
    return user;
  }

  private isPublic(context: ExecutionContext): boolean {
    return this.reflector.getAllAndOverride<boolean>(IS_PUBLIC_KEY, [
      context.getHandler(),
      context.getClass(),
    ]);
  }

  private validateToken(context: ExecutionContext): void {
    const token = context.switchToHttp().getRequest().headers['authorization'];
    if (!token) {
      throw new UnauthorizedException('토큰 정보가 존재하지 않습니다.');
    }
  }
}
