package com.adcubum.persistence.testdata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.adcubum.persistence.data.DateInterval;

class DataGeneratorUtilsTest {

   @Test
   void generatePayloadShouldGenerateCorrectLength() {
      //given
      int minLength = 25;
      int maxLength = 27;

      //when
      String payload = DataGeneratorUtils.generatePayload(minLength, maxLength);

      //then
      assertThat(payload).hasSizeBetween(minLength, maxLength);
   }

   @Test
   void generateConnectedDateIntervalsShouldGenerateIntervalsWithCorrectBoundaries() {
      //given
      LocalDate minDate = LocalDate.of(2019, 01, 01);
      int count = 3;

      //when
      List<DateInterval> dateIntervals = DataGeneratorUtils.generateConnectedDateIntervals(minDate, 1, count);

      //then
      DateInterval dateInterval1 = DateInterval.of(LocalDate.of(2019, 01, 01), LocalDate.of(2019, 01, 01));
      DateInterval dateInterval2 = DateInterval.of(LocalDate.of(2019, 01, 02), LocalDate.of(2019, 01, 02));
      DateInterval dateInterval3 = DateInterval.of(LocalDate.of(2019, 01, 03), LocalDate.of(2019, 01, 03));

      assertThat(dateIntervals).containsExactly(dateInterval1, dateInterval2, dateInterval3);
   }


}