import {
  ArgumentMetadata,
  BadRequestException,
  Injectable,
  ValidationPipe,
} from '@nestjs/common';

@Injectable()
export class GlobalValidationPipe extends ValidationPipe {
  constructor() {
    super({ whitelist: true, transform: true, forbidNonWhitelisted: true });
  }

  public async transform(value, metadata: ArgumentMetadata) {
    try {
      return await super.transform(value, metadata);
    } catch (e) {
      if (e instanceof BadRequestException) {
        const response = e.getResponse();
        const messages = response['message'];
        const message =
          messages instanceof Array<string> ? messages[0] : messages;
        throw new BadRequestException(message);
      }
    }
  }
}
