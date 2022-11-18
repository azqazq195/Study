import { DATABASE_ENGINE } from '../../config/configuration';
import { Entity } from 'typeorm';

export const DefaultEntity = (name: string) =>
  Entity({ name: name, engine: DATABASE_ENGINE, synchronize: true });
