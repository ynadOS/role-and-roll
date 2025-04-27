import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'orderBy'
})
export class OrderByPipe implements PipeTransform {
  transform(array: any[], field: string, reverse: boolean = false): any[] {
    if (!Array.isArray(array) || !field) return array;

    const sorted = [...array].sort((a, b) => {
      let valueA = a[field];
      let valueB = b[field];

      // Gestion des dates
      if (valueA instanceof Date && valueB instanceof Date) {
        return valueA.getTime() - valueB.getTime();
      }

      if (typeof valueA === 'string' && typeof valueB === 'string') {
        return valueA.localeCompare(valueB);
      }

      if (typeof valueA === 'number' && typeof valueB === 'number') {
        return valueA - valueB;
      }

      // Si c'est un champ de type date sous forme de string
      const dateA = new Date(valueA);
      const dateB = new Date(valueB);
      if (!isNaN(dateA.getTime()) && !isNaN(dateB.getTime())) {
        return dateA.getTime() - dateB.getTime();
      }

      // fallback: rien ne change
      return 0;
    });

    return reverse ? sorted.reverse() : sorted;
  }
}