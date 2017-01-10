package resources.orm;

public interface LeadBo
{

    void save(Lead lead);
    void update(Lead lead);
    void delete(Lead lead);
    Lead findById(Integer leadId);
}
