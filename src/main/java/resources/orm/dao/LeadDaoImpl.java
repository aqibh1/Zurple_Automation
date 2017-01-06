package resources.orm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import resources.orm.Lead;

public class LeadDaoImpl
        extends HibernateDaoSupport implements LeadDao
{

    public void save(Lead lead){
        getHibernateTemplate().save(lead);
    }

    public void update(Lead lead){
        getHibernateTemplate().update(lead);
    }

    public void delete(Lead lead){
        getHibernateTemplate().delete(lead);
    }

    public Lead findByCode(String leadCode){
        List list = getHibernateTemplate().find(
                "from leads where lead_id=?",leadCode
        );
        return (Lead)list.get(0);
    }

}
