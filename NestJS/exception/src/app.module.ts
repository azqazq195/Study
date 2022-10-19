import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { FilterModule } from './filter/filter.module';
import { PipeModule } from './pipe/pipe.module';

@Module({
  imports: [FilterModule, PipeModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
