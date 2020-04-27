package com.adcubum.persistence.service;

import com.adcubum.persistence.entity.Partner;
import com.adcubum.persistence.repo.PartnerRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class PartnerService {

    private PartnerRepo partnerRepo;

    private EntityManager entityManager;

    @Autowired
    public PartnerService(PartnerRepo partnerRepo, EntityManager entityManager) {
        this.partnerRepo = partnerRepo;
        this.entityManager = entityManager;
    }

    public Iterable<Partner> findAll(Date keyDate) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("state").setParameter("keyDate", keyDate);
        return partnerRepo.findAll();
    }

    public Optional<Partner> findById(String id, Date keyDate) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("state").setParameter("keyDate", keyDate);
        Optional<Partner> partner = partnerRepo.findById(id);
        partner.get().states.size();
        return partner;
    }

    public Partner save(Partner partner) {
        return partnerRepo.save(partner);
    }

}
