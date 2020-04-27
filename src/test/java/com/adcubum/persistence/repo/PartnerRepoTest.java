package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import com.adcubum.persistence.entity.PartnerState;
import com.adcubum.persistence.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(propagation = Propagation.NEVER)
@Rollback(false)
public class PartnerRepoTest {

    @Autowired
    private PartnerService sut;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void test() throws Exception {
        Date keyDate = new SimpleDateFormat("dd-MM-yyyy").parse("03-03-1993");
        Iterable<Partner> partners = sut.findAll(keyDate);
        System.out.println(partners.iterator().next().states.size());
        assertThat(partners).isNotEmpty();
    }

    @Test
    public void testSave() throws Exception {
        Date keyDate = new SimpleDateFormat("dd-MM-yyyy").parse("03-03-1993");

        Partner partner = sut.findById("6ee2221b-1e38-4709-afe5-4f3f40ce4c99", keyDate).get();

        PartnerState partnerState = partner.states.iterator().next();
        partnerState.name = String.valueOf(System.currentTimeMillis());
        sut.save(partner);
    }

}
