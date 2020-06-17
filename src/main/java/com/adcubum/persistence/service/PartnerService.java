package com.adcubum.persistence.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.entity.Partner;
import com.adcubum.persistence.repo.PartnerRepo;

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
      EntityGraph<?> entityGraph = entityManager.getEntityGraph("partner");
      Map hints = new HashMap();
      hints.put("javax.persistence.fetchgraph", entityGraph);
      Session session = entityManager.unwrap(Session.class);
      Filter filter = session.enableFilter("state");
      filter.setParameter("keyDate", keyDate);
      entityManager.find(Partner.class, id, hints);
      Optional<Partner> partner = partnerRepo.findById(id);
      partner.get().states.size();
      return partner;
   }

   public Partner save(Partner partner) {
      return partnerRepo.save(partner);
   }

}
