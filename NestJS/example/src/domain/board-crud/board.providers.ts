import { DataSource } from 'typeorm';
import { BoardEntity } from './board.entity';

export const boardProviders = [
    {
        provide: 'BOARD_REPOSITORY',
        useFactory: (dataSource: DataSource) => dataSource.getRepository(BoardEntity),
        inject: ['DATA_SOURCE'],
    },
];
