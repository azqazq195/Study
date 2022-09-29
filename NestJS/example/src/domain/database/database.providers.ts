import { DataSource } from 'typeorm';
import { Photo } from '../photo-typeorm/photo.model';
import { BoardEntity } from '../board-crud/board.entity';

export const databaseProviders = [
    {
        provide: 'DATA_SOURCE',
        useFactory: async () => {
            const dataSource = new DataSource({
                type: 'mysql',
                host: 'localhost',
                port: 3306,
                username: 'root',
                password: '',
                database: 'test',
                synchronize: true,
                logging: true,
                entities: [BoardEntity],
                subscribers: [],
                migrations: [],
            });

            return dataSource.initialize();
        },
    },
];
