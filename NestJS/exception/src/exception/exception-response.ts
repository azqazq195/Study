import { HttpException, HttpStatus } from '@nestjs/common';

interface Response {
  readonly statusCode: number;
  readonly message: string;
}

interface FailureResponse extends Response {
  readonly error: string;
}

interface SuccessResponse extends Response {
  readonly data: object;
}

export const buildFailureResponse = (
  exception: HttpException,
): FailureResponse => ({
  statusCode: exception.getStatus(),
  message: exception.message,
  error: exception.name,
});

export const buildErrorResponse = (exception: Error): FailureResponse => ({
  statusCode: HttpStatus.BAD_REQUEST,
  message: exception.message,
  error: exception.name,
});

export const buildUnHandledResponse = (): FailureResponse => ({
  statusCode: HttpStatus.INTERNAL_SERVER_ERROR,
  message: '처리되지 않은 에러.',
  error: HttpStatus.INTERNAL_SERVER_ERROR.toString(),
});

export const buildSuccessResponse = (
  statusCode: HttpStatus,
  data: object,
): SuccessResponse => ({
  statusCode: statusCode,
  message: 'success',
  data: data,
});
