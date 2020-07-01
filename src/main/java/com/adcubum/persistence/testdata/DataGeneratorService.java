package com.adcubum.persistence.testdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.data.DateInterval;

@Transactional
@Service
public class DataGeneratorService {

   public static final int DURATION_DAYS = 30;
   @Autowired
   private JdbcTemplate jdbcTemplate;

   private final Random serviceRandom = new Random();

   public void generateContracts(int count, int averageCountOfSubparts, int averageCountOfStates) {
      final LocalDate minimalStartDate = LocalDate.of(1970, 1, 1);
      for (int i = 0; i < count; i++) {
         LocalDate startDate = minimalStartDate.plusMonths(serviceRandom.nextInt(600));
         generateContract(averageCountOfSubparts, averageCountOfStates, startDate);
      }
   }

   private void generateContract(int averageCountOfSubparts, int averageCountOfStates, LocalDate startDate) {
      int countOfStates = randomInt(averageCountOfStates);

      UUID boid = UUID.randomUUID();
      String payload = DataGeneratorUtils.generatePayload(10, 15);
      List<DateInterval> stateIntervals = DataGeneratorUtils.generateConnectedDateIntervals(startDate, DURATION_DAYS, countOfStates);

      int i = 0;
      for (DateInterval dateInterval : stateIntervals) {
         jdbcTemplate.update("INSERT INTO contract(id, boid, state_begin, state_end, payload) VALUES (?,?,?,?,?)",
               UUID.randomUUID(), boid, dateInterval.startDate, dateInterval.endDate, payload + "_" + (i++));
      }

      int countOfContractParts = randomInt(averageCountOfSubparts);
      IntStream.range(0, countOfContractParts).forEach(part -> generateContractPart(boid, averageCountOfSubparts, averageCountOfStates, startDate));
   }

   private void generateContractPart(UUID contractBoid, int averageCountOfSubparts, int averageCountOfStates, LocalDate startDate) {
      int countOfStates = randomInt(averageCountOfStates);

      UUID boid = UUID.randomUUID();
      String payload = DataGeneratorUtils.generatePayload(10, 15);
      List<DateInterval> stateIntervals = DataGeneratorUtils.generateConnectedDateIntervals(startDate, 30, countOfStates);

      int i = 0;
      for (DateInterval dateInterval : stateIntervals) {
         jdbcTemplate.update("INSERT INTO contract_part(id, boid, contract_boid, state_begin, state_end, payload) VALUES (?,?,?,?,?,?)",
               UUID.randomUUID(), boid, contractBoid, dateInterval.startDate, dateInterval.endDate, payload + "_" + (i++));
      }

      int countOfInsuredObjects = randomInt(averageCountOfSubparts);
      IntStream.range(0, countOfInsuredObjects).forEach(insuredObject -> generateInsuredObject(contractBoid, boid, averageCountOfSubparts, averageCountOfStates, startDate));
   }

   private void generateInsuredObject(UUID contractBoid, UUID contractPartBoid, int averageCountOfSubparts, int averageCountOfStates, LocalDate startDate) {
      int countOfStates = randomInt(averageCountOfStates);

      UUID boid = UUID.randomUUID();
      String payload = DataGeneratorUtils.generatePayload(10, 15);
      List<DateInterval> stateIntervals = DataGeneratorUtils.generateConnectedDateIntervals(startDate, 30, countOfStates);

      int i = 0;
      for (DateInterval dateInterval : stateIntervals) {
         jdbcTemplate.update("INSERT INTO insured_object(id, boid, contract_boid, contract_part_boid, state_begin, state_end, payload) VALUES (?,?,?,?,?,?,?)",
               UUID.randomUUID(), boid, contractBoid, contractPartBoid, dateInterval.startDate, dateInterval.endDate, payload + "_" + (i++));
      }
   }

   private int randomInt(int average) {
      return serviceRandom.nextInt(average * 2) + 1;
   }

}
