// https://javascript.plainenglish.io/type-safe-date-strings-66b6dc58658a
import dayjs, { Dayjs } from 'dayjs';

type d = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0;
type oneToNine = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9;
type MM = `0${oneToNine}` | `1${0 | 1 | 2}`;
type DD = `${0}${oneToNine}` | `${1 | 2}${d}` | `3${0 | 1}`;
type YYYY = `19${d}${d}` | `20${d}${d}`;

export type DateYMDString = `${YYYY}-${MM}-${DD}`;

/**
 * * YYYY-MM-DD HH:mm:ss
 */
export type DateTimeString = string;

export type DateTimeYMDStartOfDayString = `${YYYY}-${MM}-${DD} 00:00:00`;
export type DateTimeYMDEndSOfDayString = `${YYYY}-${MM}-${DD} 23:59:59`;

export function toStartOfDayString(dayjs: Dayjs): DateTimeYMDStartOfDayString {
  return dayjs.format('YYYY-MM-DD 00:00:00') as DateTimeYMDStartOfDayString;
}

export function toEndOfDayString(dayjs: Dayjs): DateTimeYMDEndSOfDayString {
  return dayjs.format('YYYY-MM-DD 23:59:59') as DateTimeYMDEndSOfDayString;
}

export const getYMDDate = (baseDate: Date): Date =>
  new Date(dayjs(baseDate).format('YYYY-MM-DD'));
export const getFullDate = (baseDate: Date): Date =>
  new Date(dayjs(baseDate).format('YYYY-MM-DD HH:mm:ss'));

export const dayjsUtil = {
  toStartOfDayString,
  toEndOfDayString,
  getYMDDate,
  getFullDate,
};
