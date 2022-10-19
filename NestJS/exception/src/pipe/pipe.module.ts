import { Module } from '@nestjs/common';
import { APP_PIPE } from '@nestjs/core';
import { GlobalValidationPipe } from './global-validation.pipe';

@Module({
  providers: [
    {
      provide: APP_PIPE,
      useClass: GlobalValidationPipe,
    },
  ],
})
export class PipeModule {}
