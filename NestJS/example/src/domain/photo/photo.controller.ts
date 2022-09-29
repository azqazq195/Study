import { Controller, Get, HttpCode, HttpStatus, Post, Res } from '@nestjs/common';
import { Photo } from './photo.model';
import { AppDataSource } from '../../main';
import { Response } from 'express';

@Controller('photos')
export class PhotoController {
    @Get()
    @HttpCode(HttpStatus.CREATED)
    async create(@Res() res: Response) {
        const photo = new Photo();
        photo.name = 'Me and Bears';
        photo.description = 'I am near polar bears';
        photo.filename = 'photo-with-bears.jpg';
        photo.views = 1;
        photo.isPublished = true;

        await AppDataSource.manager.save(photo);
        console.log('Photo has been saved. Photo id is', photo.id);
        res.json(photo);
    }
}
