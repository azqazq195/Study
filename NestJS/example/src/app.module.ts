import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CatsController } from './domain/cats/cats.controller';
import { PhotoController } from './domain/photo/photo.controller';

@Module({
    imports: [],
    controllers: [AppController, CatsController, PhotoController],
    providers: [AppService],
})
export class AppModule {}
