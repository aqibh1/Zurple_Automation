package resources.frameworks.spring.orm.dao;

import resources.frameworks.spring.orm.model.Lead;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class LeadDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(Lead lead) {
        em.persist(lead);
    }

    public List<Lead> findAll() {
        return em.createQuery("SELECT p FROM leads p").getResultList();
    }

}
