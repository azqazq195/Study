import { Module } from '@nestjs/common';
import { APP_FILTER } from '@nestjs/core';
import { HttpExceptionFilter } from './http-exception.filter';

// * global Filter 는 providers 역순으로 적용된다.
// ex) HttpExceptionFilter -> ErrorFilter
@Module({
  providers: [
    {
      provide: APP_FILTER,
      useClass: Error,
    },
    {
      provide: APP_FILTER,
      useClass: HttpExceptionFilter,
    },
  ],
})
export class GlobalFilterModule {}
