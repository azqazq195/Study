import {
  ValidationArguments,
  ValidatorConstraintInterface,
} from 'class-validator';

export abstract class CustomValidatorConstraint
  implements ValidatorConstraintInterface
{
  defaultMessage(args: ValidationArguments) {
    return `${args.property} 값이 올바르지 않습니다.`;
  }

  validate(
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    value: any,
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    validationArguments?: ValidationArguments,
  ): Promise<boolean> | boolean {
    return undefined;
  }
}
