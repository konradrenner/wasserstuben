import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
/*
 * Transforms a zonedatetime-string to a date
*/
@Pipe({name: 'zoneddatetimestringToDate'})
export class ZonedDateTimeStringToDatePipe implements PipeTransform {
  transform(value: string): Date {
    let stripped = value.substring(0,19);
    return new Date(stripped);
  }
}