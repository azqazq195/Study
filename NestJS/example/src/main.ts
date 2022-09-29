import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DataSource } from 'typeorm';
import { Photo } from './domain/photo-typeorm/photo.model';
import { BoardEntity } from './domain/board-crud/board.entity';

async function bootstrap() {
    const app = await NestFactory.create(AppModule);
    await app.listen(3000);
}
bootstrap();
