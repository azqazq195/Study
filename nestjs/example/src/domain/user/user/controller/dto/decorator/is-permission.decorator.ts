import {
  isNotEmpty,
  registerDecorator,
  ValidatorConstraint,
} from 'class-validator';
import { Permission } from '../../../entity/enum/permission.enum';
import { CustomValidatorConstraint } from '../../../../../../shared/validator/custom-valdator-constraint.class';
import { CreateUserRolePermissionDto } from '../create-user-role-permission.dto';

export function isPermission(value: Permission): value is Permission {
  return Object.values(Permission).includes(value);
}

@ValidatorConstraint()
class PermissionValidator extends CustomValidatorConstraint {
  validate(value: any): boolean {
    return isNotEmpty(value) && isPermission(value);
  }
}

export function IsPermission() {
  return function (object: CreateUserRolePermissionDto, propertyName: string) {
    registerDecorator({
      name: 'IsPermission',
      target: object.constructor,
      propertyName: propertyName,
      validator: PermissionValidator,
    });
  };
}
