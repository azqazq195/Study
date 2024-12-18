import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Event } from './entity/event.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Event])],
})
export class EventModule {}
