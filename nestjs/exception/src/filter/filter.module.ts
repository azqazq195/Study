import { Module } from '@nestjs/common';
import { APP_FILTER } from '@nestjs/core';
import { UnhandledExceptionFilter } from './unhandled-exception.filter';
import { ErrorFilter } from './error.filter';
import { HttpExceptionFilter } from './http-exception.filter';

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
