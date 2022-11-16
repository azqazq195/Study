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
import { CreateUserDto } from './dto/createUser.dto';
import { UpdateUserDto } from './dto/updateUser.dto';
import { Roles } from '../../../shared/security/decorator/guard.decorator';
import { USER_ROLE } from '../entity/type/enum/user-role.enum';

@Controller('users')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Roles(USER_ROLE.오늘의꽃관리자)
  @Get(':id')
  async findOne(@Param('id') id: number): Promise<UserEntity> {
    return await this.userService.findOne(id);
  }

  @Get()
  @Roles(USER_ROLE.화훼업계종사자)
  async findAll(): Promise<UserEntity[]> {
    return this.userService.findAll();
  }

  @Post()
  async create(@Body() createUserDto: CreateUserDto): Promise<UserEntity> {
    return this.userService.create(createUserDto);
  }

  // @Patch(':id')
  // async patch(@Body() updateUserDto: UpdateUserDto): Promise<UserEntity> {
  //   return this.userService.save(updateUserDto as CreateUserDto);
  // }

  @Put(':id')
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
