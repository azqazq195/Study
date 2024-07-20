import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { TestController } from './controller/test.controller';
import { TestService } from './service/test.service';
import { AEntity } from './entity/a.entity';
import { BEntity } from './entity/b.entity';

@Module({
  imports: [TypeOrmModule.forFeature([AEntity, BEntity])],
  providers: [TestService],
  controllers: [TestController],
})
export class TestModule {}
