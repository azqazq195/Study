import { Module } from '@nestjs/common';
import { DynamicModule } from '@nestjs/common/interfaces/modules/dynamic-module.interface';
import { ForwardReference } from '@nestjs/common/interfaces/modules/forward-reference.interface';
import { Type } from '@nestjs/common/interfaces/type.interface';

type NestModule =
  | Type<any>
  | DynamicModule
  | Promise<DynamicModule>
  | ForwardReference;

type MergedModule = any;

export function mergeNestModule(...modules: NestModule[]): MergedModule {
  @Module({
    imports: modules,
    exports: modules,
  })
  class MergeAndReexportModule {}

  return MergeAndReexportModule;
}
