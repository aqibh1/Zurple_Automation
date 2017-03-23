package resources.classes;

import java.math.BigDecimal;
import java.util.Date;

public class AdminProduct
{

    private String displayname;
    private Date nextbill_date;
    private String nextbill_date_string;
    private BigDecimal fee;

    public String getDisplayName()
    {
        return displayname;
    }

    public void setDisplayName(String displayname)
    {
        this.displayname = displayname;
    }

    public Date getNextBillDate()
    {
        return nextbill_date;
    }

    public void setNextBillDate(Date nextbill_date)
    {
        this.nextbill_date = nextbill_date;
    }

    public BigDecimal getFee()
    {
        return fee;
    }

    public void setFee(BigDecimal fee)
    {
        this.fee = fee;
    }

    public String getNextbillDateString()
    {
        return nextbill_date_string;
    }

    public void setNextbillDateString(String nextbill_date_string)
    {
        this.nextbill_date_string = nextbill_date_string;
    }
}
