import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { UserService } from '../service/user.service';
import { UserEntity } from '../entity/user.entity';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { Public } from '../../../../shared/security/decorator/guard.decorator';

@Controller('users')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<UserEntity> {
    return await this.userService.findOneById(id);
  }

  @Get()
  async findAll(): Promise<UserEntity[]> {
    return this.userService.findAll();
  }

  @Post()
  @Public()
  async create(@Body() createUserDto: CreateUserDto): Promise<UserEntity> {
    return this.userService.insert(createUserDto);
  }

  // @Patch(':id')
  // async patch(@Body() updateUserDto: UpdateUserDto): Promise<UserEntity> {
  //   return this.userService.save(updateUserDto as CreateUserDto);
  // }

  @Put(':id')
  @Public()
  async put(
    @Param('id') id: number,
    @Body() updateUserDto: UpdateUserDto,
  ): Promise<UserEntity> {
    return this.userService.update(id, updateUserDto);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<string> {
    await this.userService.delete(id);
    return 'done';
  }
}
