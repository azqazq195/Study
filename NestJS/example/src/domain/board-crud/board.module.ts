import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { BoardEntity } from './board.entity';
import { BoardService } from './board.service';
import { BoardController } from './board.controller';
import { DatabaseModule } from '../database/database.module';
import { boardProviders } from './board.providers';

@Module({
    imports: [DatabaseModule],
    providers: [...boardProviders, BoardService],
    controllers: [BoardController],
})
export class BoardModule {}
