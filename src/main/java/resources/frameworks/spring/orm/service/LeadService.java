package resources.frameworks.spring.orm.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import resources.frameworks.spring.orm.dao.LeadDao;
import resources.frameworks.spring.orm.model.Lead;

@Component
public class LeadService {

    @Autowired
    private LeadDao leadDao;

    @Transactional
    public void add(Lead lead) {
        leadDao.persist(lead);
    }

    @Transactional
    public void addAll(Collection<Lead> leads) {
        for (Lead lead : leads) {
            leadDao.persist(lead);
        }
    }

    @Transactional(readOnly = true)
    public List<Lead> listAll() {
        return leadDao.findAll();

    }

}
