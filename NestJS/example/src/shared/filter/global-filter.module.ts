import { Module } from '@nestjs/common';
import { APP_FILTER } from '@nestjs/core';
import { HttpExceptionFilter } from './http-exception.filter';
import { TypeormErrorFilter } from './typeorm-error.filter';
import { EntityNotFoundErrorFilter } from './entity-not-found-error.filter';

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
    {
      provide: APP_FILTER,
      useClass: TypeormErrorFilter,
    },
    {
      provide: APP_FILTER,
      useClass: EntityNotFoundErrorFilter,
    },
  ],
})
export class GlobalFilterModule {}
