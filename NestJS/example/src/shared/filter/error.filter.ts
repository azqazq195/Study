import { ArgumentsHost, Catch, ExceptionFilter } from '@nestjs/common';
import { Response } from 'express';

@Catch(Error)
export class ErrorFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost): any {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const statusCode = exception.getStatus();

    return response.status(statusCode).json({
      message: this.parseExceptionMessage(exception),
      statusCode,
      error: exception.name,
    });
  }

  private parseExceptionMessage(exception: Error): string {
    console.log(exception);
    console.log('error');
    return 'error';
    // const response = exception.getResponse() as any;
    // if (Array.isArray(response.message)) {
    //   return response.message[0];
    // }
    // return response.message;
  }
}
