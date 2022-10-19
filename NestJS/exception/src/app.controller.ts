import {
  BadRequestException,
  Body,
  Controller,
  Get,
  Post,
} from '@nestjs/common';
import { AppService } from './app.service';
import { BadRequestMessage } from './filter/message/bad-request-message';
import { CreateEntityDto } from './dto/create-entity.dto';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Post()
  createEntity(@Body() createEntity: CreateEntityDto): string {
    console.log(createEntity);
    return 'good';
  }

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
