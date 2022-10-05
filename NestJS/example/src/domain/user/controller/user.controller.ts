import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { UserService } from '../service/user.service';
import { UserEntity } from '../entity/user.entity';
import { CreateUserDto } from './dto/createUser.dto';
import { UpdateUserDto } from './dto/updateUser.dto';

// DTO
type CreateDto = CreateUserDto;
type UpdateDto = UpdateUserDto;
const CreateDto = CreateUserDto;
const UpdateDto = UpdateUserDto;

// ENTITY
type Entity = UserEntity;
const Entity = UserEntity;

const validation = new ValidationPipe({ transform: true });

@Controller('users')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<Entity> {
    return this.userService.findOne(id);
  }

  @Get()
  async findAll(): Promise<Entity[]> {
    return this.userService.findAll();
  }

  @Post()
  @UsePipes(validation)
  async save(@Body() createUserDto: CreateDto): Promise<Entity> {
    return this.userService.save(createUserDto.toUserEntity());
  }

  @Put()
  @UsePipes(validation)
  async update(@Body() updateUserDto: UpdateDto): Promise<Entity> {
    return this.userService.save(updateUserDto.toUserEntity());
  }

  @Delete(':id')
  @UsePipes(validation)
  async delete(@Param('id') id: number): Promise<string> {
    await this.userService.delete(id);
    return 'done';
  }
}
