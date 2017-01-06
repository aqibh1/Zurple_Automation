package resources.orm;

//import com.mkyong.stock.dao.StockDao;

import resources.orm.dao.LeadDao;

public class LeadBoImpl
        implements LeadBo
{

    LeadDao leadDao;

    public void setLeadDao(LeadDao leadDao) {
        this.leadDao = leadDao;
    }

    public void save(Lead lead){
        leadDao.save(lead);
    }

    public void update(Lead lead){
        leadDao.update(lead);
    }

    public void delete(Lead lead){
        leadDao.delete(lead);
    }

    public Lead findByCode(String leadCode){
        return leadDao.findByCode(leadCode);
    }
}
