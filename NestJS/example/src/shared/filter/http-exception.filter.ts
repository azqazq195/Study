import {
  ArgumentsHost,
  Catch,
  ExceptionFilter,
  HttpException,
} from '@nestjs/common';
import { Response } from 'express';

@Catch(HttpException)
export class HttpExceptionFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const statusCode = exception.getStatus();

    console.log(exception);

    return response.status(statusCode).json({
      message: this.parseExceptionMessage(exception),
      statusCode,
      error: exception.name,
    });
  }

  private parseExceptionMessage(exception: HttpException): string {
    const response = exception.getResponse() as any;

    return Array.isArray(response.message)
      ? response.message[0]
      : response.message;
  }
}
