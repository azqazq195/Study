import { JwtModule } from '@nestjs/jwt';
import { jwtConstant } from '../constant/constant';
import { Module } from '@nestjs/common';

@Module({
  imports: [
    JwtModule.register({
      secret: jwtConstant.secret,
      signOptions: { expiresIn: '3600s' },
    }),
  ],
  exports: [JwtModule],
})
export class CustomJwtModule {}
