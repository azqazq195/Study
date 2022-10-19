import { Module } from '@nestjs/common';
import { APP_FILTER } from '@nestjs/core';
import { UnhandledExceptionFilter } from './filters/unhandled-exception.filter';
import { ErrorFilter } from './filters/error.filter';
import { HttpExceptionFilter } from './filters/http-exception.filter';

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
export class FilterModule {}
