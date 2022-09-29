import { Column, Entity, PrimaryColumn, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Photo {
    @PrimaryGeneratedColumn({
        type: 'int',
        name: 'id',
        comment: '아이디',
    })
    id: number;

    @Column({
        type: 'varchar',
        name: 'name',
        comment: '사진 이름',
    })
    name: string;

    @Column({
        type: 'varchar',
        name: 'description',
        comment: '사진 설명',
    })
    description: string;

    @Column({
        type: 'varchar',
        name: 'filename',
        comment: '파일 이름',
    })
    filename: string;

    @Column({
        type: 'int',
        name: 'views',
        comment: '조회수',
    })
    views: number;

    @Column({
        type: 'tinyint',
        name: 'is_published',
        comment: '배포 여부',
    })
    isPublished: boolean;
}
