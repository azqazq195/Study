import {
  ArgumentsHost,
  Catch,
  ExceptionFilter,
  HttpStatus,
} from '@nestjs/common';
import { Response } from 'express';
import { buildErrorResponse } from '../exception/exception-response';

// ! 에러 발생은 HttpException 을 활용한다.
// ! 기존 Error 처리를 위한 filters
@Catch(Error)
export class ErrorFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    response.status(HttpStatus.BAD_REQUEST).json(buildErrorResponse(exception));
  }
}
