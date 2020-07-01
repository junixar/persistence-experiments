package com.adcubum.persistence.testdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataGeneratorServiceTest {

   @Autowired
   private DataGeneratorService sut;

   @Test
   void test() {
      sut.generateContracts(100, 10, 30);
   }

}