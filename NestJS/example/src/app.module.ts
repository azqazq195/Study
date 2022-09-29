import { Module } from '@nestjs/common';
import { BoardModule } from './domain/board-crud/board.module';
import { DatabaseModule } from './domain/database/database.module';

@Module({
    imports: [DatabaseModule, BoardModule],
    providers: [],
})
export class AppModule {}
