package resources.classes;

import java.math.BigDecimal;
import java.util.Date;

public class AdminProduct
{

    private String displayname;
    private Date nextbill_date;
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
}
