import { Controller, HttpCode, Post, Request, UseGuards } from '@nestjs/common';
import { AuthService } from '../service/auth.service';
import { LocalAuthGuard } from '../../guard/local-auth.guard';
import { Public } from '../../decorator/guard.decorator';

@Controller()
export class AuthController {
  constructor(private authService: AuthService) {
    console.log('>> auth controller constructor <<');
  }

  @Public()
  @UseGuards(LocalAuthGuard)
  @Post('auth/login')
  @HttpCode(200)
  async login(@Request() req) {
    console.log('auth controller login');
    return this.authService.login(req.user);
  }
}
