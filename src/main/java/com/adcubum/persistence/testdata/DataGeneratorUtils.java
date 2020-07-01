package com.adcubum.persistence.testdata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.data.DateInterval;

public final class DataGeneratorUtils {

   private DataGeneratorUtils() {
   }

   private final static Random utilsRandom = new Random();

   public static String generatePayload(int minLength, int maxLength) {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = minLength + utilsRandom.nextInt(maxLength - minLength);
      Random random = new Random();

      return random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
   }

   public static List<DateInterval> generateConnectedDateIntervals(final LocalDate startDate, final int durationDays, int count) {
      List<DateInterval> result = new ArrayList<>();
      LocalDate currentStartDate = startDate;
      for (int i = 0; i < count; i++) {
         LocalDate currentEndDate = currentStartDate.plusDays(durationDays - 1);
         result.add(DateInterval.of(currentStartDate, currentEndDate));

         currentStartDate = currentEndDate.plusDays(1);
      }

      return result;
   }

}
