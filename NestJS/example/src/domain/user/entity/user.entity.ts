import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';
import { plainToClass } from 'class-transformer';

@Entity()
export class UserEntity {
  @PrimaryGeneratedColumn()
  readonly id: number;

  @Column()
  readonly firstName!: string;

  @Column()
  readonly lastName!: string;

  @Column()
  readonly age!: number;

  static from(args: Partial<UserEntity>) {
    return plainToClass(UserEntity, args);
  }
}
