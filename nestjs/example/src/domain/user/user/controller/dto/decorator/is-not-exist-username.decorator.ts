import {
  isNotEmpty,
  registerDecorator,
  ValidationArguments,
  ValidatorConstraint,
} from 'class-validator';
import { UserService } from '../../../service/user.service';
import { CreateUserDto } from '../create-user.dto';
import { CustomValidatorConstraint } from '../../../../../../shared/validator/custom-valdator-constraint.class';

@ValidatorConstraint({ async: true })
export class UsernameValidator extends CustomValidatorConstraint {
  constructor(private readonly userService: UserService) {
    super();
  }

  async validate(value: any): Promise<boolean> {
    return isNotEmpty(value) && (await this.isNotExistUsername(value));
  }

  async isNotExistUsername(value: string): Promise<boolean> {
    return !(await this.userService.isExist(value));
  }

  defaultMessage(args: ValidationArguments): string {
    return `이미 존재하는 ${args.property}입니다.`;
  }
}

export function IsNotExistUsername() {
  return function (object: CreateUserDto, propertyName: string) {
    registerDecorator({
      name: 'IsNotExistUsername',
      target: object.constructor,
      propertyName: propertyName,
      validator: UsernameValidator,
    });
  };
}
