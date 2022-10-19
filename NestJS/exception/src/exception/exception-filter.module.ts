import { Module } from '@nestjs/common';
import { APP_FILTER } from '@nestjs/core';
import { UnhandledExceptionFilter } from './filter/unhandled-exception.filter';
import { ErrorFilter } from './filter/error.filter';
import { HttpExceptionFilter } from './filter/http-exception.filter';

@Module({
  providers: [
    {
      provide: APP_FILTER,
      useClass: UnhandledExceptionFilter,
    },
    {
      provide: APP_FILTER,
      useClass: ErrorFilter,
    },
    {
      provide: APP_FILTER,
      useClass: HttpExceptionFilter,
    },
  ],
})
export class ExceptionFilterModule {}
