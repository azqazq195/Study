import { ArgumentsHost, Catch, ExceptionFilter } from '@nestjs/common';
import { Response } from 'express';
import { buildUnHandledResponse } from '../exception/exception-response';

@Catch()
export class UnhandledExceptionFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    response
      .status(exception.INTERNAL_SERVER_ERROR)
      .json(buildUnHandledResponse());
  }
}
