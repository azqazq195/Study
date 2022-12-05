import {
  isNotEmpty,
  registerDecorator,
  ValidationArguments,
  ValidatorConstraint,
} from 'class-validator';
import { Role } from '../../../entity/enum/role.enum';
import { CreateUserRoleDto } from '../create-user-role.dto';
import { CustomValidatorConstraint } from '../../../../../../shared/validator/custom-valdator-constraint.class';

export function isRole(value: Role): value is Role {
  return Object.values(Role).includes(value);
}

@ValidatorConstraint()
class RoleValidator extends CustomValidatorConstraint {
  validate(value: any): boolean {
    return isNotEmpty(value) && isRole(value);
  }
}

export function IsRole() {
  return function (object: CreateUserRoleDto, propertyName: string) {
    registerDecorator({
      name: 'IsRole',
      target: object.constructor,
      propertyName: propertyName,
      validator: RoleValidator,
    });
  };
}
