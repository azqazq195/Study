import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.useGlobalPipes(
    new ValidationPipe({
      transform: true,
      whitelist: true,
      // DTO에 존재하지 않는 Property를 넘겼을 경우 에러 발생
      forbidNonWhitelisted: true,
      stopAtFirstError: true,
    }),
  );
  await app.listen(3000);
}

bootstrap();
