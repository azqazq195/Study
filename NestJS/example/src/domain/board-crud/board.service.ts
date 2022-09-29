import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';

import { BoardEntity } from './board.entity';
import { Repository } from 'typeorm';

@Injectable()
export class BoardService {
    constructor(
        @InjectRepository(BoardEntity)
        private readonly repo: Repository<BoardEntity>,
    ) {}
    async greeting(id: number): Promise<any> {
        return 'hi';
    }
}
