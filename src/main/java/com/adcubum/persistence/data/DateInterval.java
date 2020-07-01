package com.adcubum.persistence.data;

import java.time.LocalDate;
import java.util.Objects;

public class DateInterval {

   public LocalDate startDate;

   public LocalDate endDate;

   public static DateInterval of(LocalDate startDate, LocalDate endDate) {
      DateInterval result = new DateInterval();
      result.startDate = startDate;
      result.endDate = endDate;
      return result;
   }

   @Override public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      DateInterval that = (DateInterval) o;
      return startDate.equals(that.startDate) &&
            endDate.equals(that.endDate);
   }

   @Override public int hashCode() {
      return Objects.hash(startDate, endDate);
   }

   @Override public String toString() {
      return "DateInterval{" +
            "startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
   }
}
