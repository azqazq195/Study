import { Injectable, UnauthorizedException } from '@nestjs/common';
import { UserService } from '../../../../domain/user/service/user.service';
import { RequestUser } from '../interface/request-user.interface';
import { JwtService } from '@nestjs/jwt';
import { Token } from '../interface/token.interface';

@Injectable()
export class AuthService {
  constructor(
    private userService: UserService,
    private jwtService: JwtService,
  ) {}

  async validateUser(userId: string, password: string): Promise<RequestUser> {
    const user = await this.userService.findOneByUserId(userId);

    if (!user) {
      throw new UnauthorizedException('존재하지 않는 사용자입니다.');
    }

    if (user.password !== password) {
      throw new UnauthorizedException('비밀번호가 다릅니다.');
    }

    return {
      id: user.id,
      userId: user.userId,
      name: user.name,
      roles: user.roles.map((role) => ({ id: role.id, name: role.name })),
    } as RequestUser;
  }

  async login(user: RequestUser) {
    return {
      access_token: this.jwtService.sign(user),
    } as Token;
  }
}
