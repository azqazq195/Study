import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class BoardEntity {
    @PrimaryGeneratedColumn({
        type: 'int',
        name: 'id',
        comment: '아이디',
    })
    id: number;

    @Column({
        type: 'varchar',
        name: 'title',
        comment: '제목',
    })
    title: string;

    @Column({
        type: 'varchar',
        name: 'content',
        comment: '본문',
    })
    content: string;
}
