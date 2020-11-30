import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
/*
 * Transforms a zonedatetime-string to a date
*/
@Pipe({name: 'zoneddatetimestringToDate'})
export class ZonedDateTimeStringToDatePipe implements PipeTransform {
  transform(value: string): Date {
    return new Date(value.substring(0,9));
  }
}