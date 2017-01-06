package resources.orm.dao;

import resources.orm.Lead;

public interface LeadDao
{

    void save(Lead lead);
    void update(Lead lead);
    void delete(Lead lead);
    Lead findByCode(String leadCode);

}
