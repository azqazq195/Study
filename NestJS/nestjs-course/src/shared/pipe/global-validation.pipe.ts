import { ValidationPipe } from '@nestjs/common';

export class GlobalValidationPipe extends ValidationPipe {
  constructor() {
    super({
      // DTO 객체로 변환
      transform: true,

      // DTO 에 존재하지 않는 Property 를 넘겼을 경우 에러 발생
      whitelist: true,
      forbidNonWhitelisted: true,

      stopAtFirstError: true,
    });
  }
}
