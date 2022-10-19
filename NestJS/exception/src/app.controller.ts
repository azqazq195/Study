import { BadRequestException, Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { BadRequestMessage } from './exception/message/bad-request-message';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('/error')
  getError(): string {
    throw new Error('에러발생');
  }

  @Get('/exception')
  getException1(): string {
    throw new BadRequestException(BadRequestMessage.COMMON);
  }
}
