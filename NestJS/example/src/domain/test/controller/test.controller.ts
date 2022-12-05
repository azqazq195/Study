import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { TestService } from '../service/test.service';
import { AEntity } from '../entity/a.entity';
import { Public } from '../../../shared/security/decorator/guard.decorator';
import { CreateADto } from './dto/create-a.dto';
import { UpdateADto } from './dto/update-a.dto';
import { DeleteResult } from 'typeorm';

@Public()
@Controller('test')
export class TestController {
  constructor(private readonly testService: TestService) {}

  @Get()
  async findAll(): Promise<AEntity[]> {
    return this.testService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<AEntity> {
    return await this.testService.findOneById(id);
  }

  @Post()
  async create(@Body() createADto: CreateADto): Promise<AEntity> {
    return this.testService.insert(createADto);
  }

  @Put(':id')
  async update(
    @Param('id') id: number,
    @Body() updateADto: UpdateADto,
  ): Promise<AEntity> {
    return this.testService.update(id, updateADto);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<DeleteResult> {
    return this.testService.delete(id);
  }
}
