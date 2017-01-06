package resources.orm;

//import com.mkyong.stock.dao.StockDao;

public class LeadBoImpl
        implements LeadBo
{

    //StockDao stockDao;

    /*public void setStockDao(StockDao stockDao) {
        //this.stockDao = stockDao;
    } */

    public void save(Lead lead){
        //stockDao.save(stock);
    }

    public void update(Lead lead){
        //stockDao.update(stock);
    }

    public void delete(Lead lead){
        //stockDao.delete(stock);
    }

    public Lead findByCode(String stockCode){
        return new Lead();
        //return stockDao.findByStockCode(stockCode);
    }
}
