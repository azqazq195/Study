import {
  ArgumentsHost,
  Catch,
  ExceptionFilter,
  HttpStatus,
} from '@nestjs/common';
import { Response } from 'express';
import { EntityNotFoundError } from 'typeorm';

@Catch(EntityNotFoundError)
export class EntityNotFoundErrorFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    // const statusCode = exception.getStatus();

    return response.status(HttpStatus.NOT_FOUND).json({
      message: exception.message,
      statusCode: HttpStatus.NOT_FOUND,
      error: exception.name,
    });
  }
}
