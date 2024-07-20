import {
  ArgumentMetadata,
  BadRequestException,
  ValidationPipe,
} from '@nestjs/common';

export class GlobalValidationPipe extends ValidationPipe {
  constructor() {
    /**
     * * whitelist: dto 에 없는 properties 를 입력받을 수 있다.
     * * transform: object to dto class
     * * forbidNonWhitelisted: dto 에 없는 properties 를 받는 경우 bad request exception 을 발생시킨다.
     */
    super({
      whitelist: true,
      transform: true,
      forbidNonWhitelisted: true,
    });
  }

  public async transform(value, metadata: ArgumentMetadata) {
    try {
      console.log(metadata);
      console.log(metadata.type);

      return await super.transform(value, metadata);
    } catch (e) {
      // ! try catch하지 말고 filter에서 처리하자
      // ! Exception을 두번 던짐
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
