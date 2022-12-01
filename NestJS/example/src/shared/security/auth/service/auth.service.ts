import {
  Inject,
  Injectable,
  Scope,
  UnauthorizedException,
} from '@nestjs/common';
import { RequestUser } from '../interface/request-user.interface';
import { JwtService } from '@nestjs/jwt';
import { Token } from '../interface/token.interface';
import { UserEntity } from '../../../../domain/user/user/entity/user.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

@Injectable()
export class AuthService {
  constructor(
    @InjectRepository(UserEntity)
    private readonly userRepository: Repository<UserEntity>,
    private readonly jwtService: JwtService,
  ) {}

  // async getRequestedUser(): Promise<UserEntity> {
  //   return this.request.user;
  // }

  async validateUser(username: string, password: string): Promise<RequestUser> {
    const user = await this.findOneByUserName(username);

    if (!user) {
      throw new UnauthorizedException('존재하지 않는 사용자입니다.');
    }

    if (user.password !== password) {
      throw new UnauthorizedException('비밀번호가 다릅니다.');
    }

    return {
      username: user.username,
    } as RequestUser;
  }

  async login(user: RequestUser) {
    return {
      access_token: this.jwtService.sign(user),
    } as Token;
  }

  async findOneByUserName(username: string): Promise<UserEntity> {
    return this.userRepository.findOne(
      { username: username },
      { relations: ['roles'] },
    );
  }
}
