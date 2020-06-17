package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import com.adcubum.persistence.entity.PartnerState;
import com.adcubum.persistence.service.PartnerService;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PartnerRepoTest {

    @Autowired
    private PartnerService sut;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void test() throws Exception {
        Date keyDate = new SimpleDateFormat("dd-MM-yyyy").parse("03-03-1993");
        Iterable<Partner> partners = sut.findAll(keyDate);
        System.out.println(partners.iterator().next().states.size());
        assertThat(partners).isNotEmpty();
    }

    @Test
    public void testSave() throws Exception {
        Date keyDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1978");

        Partner partner = sut.findById("272a1a4c-7eae-4c13-88ae-118f3de6b9b8", keyDate).get();

        PartnerState partnerState = partner.states.iterator().next();

        JpaEntityInformation entityInformation = JpaEntityInformationSupport.getEntityInformation(PartnerState.class, entityManager);
        System.out.println(entityInformation.isNew(partnerState));
        PartnerState partnerState1 = new PartnerState();
        partnerState1.id = UUID.randomUUID().toString();
        System.out.println(entityInformation.isNew(partnerState1));

        partnerState.name = String.valueOf(System.currentTimeMillis());
        sut.save(partner);
    }

}
