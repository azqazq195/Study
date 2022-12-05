import {
  ArgumentsHost,
  Catch,
  ExceptionFilter,
  HttpStatus,
} from '@nestjs/common';
import { Response } from 'express';
import { TypeORMError } from 'typeorm';

@Catch(TypeORMError)
export class TypeormErrorFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    // const statusCode = exception.getStatus();

    console.log(exception);

    return response.status(HttpStatus.BAD_REQUEST).json({
      message: exception.message,
      statusCode: HttpStatus.BAD_REQUEST,
      error: exception.name,
    });
  }
}
