package resources.classes;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction
{

    private Date billing_date;
    private String billing_date_string;
    private String product_name;
    private BigDecimal amount;
    private String status;
    private Date payment_date;
    private String payment_date_string;

    public Date getBillingDate()
    {
        return billing_date;
    }

    public void setBillingDate(Date billing_date)
    {
        this.billing_date = billing_date;
    }

    public String getProductName()
    {
        return product_name;
    }

    public void setProductName(String product_name)
    {
        this.product_name = product_name;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getPaymentDate()
    {
        return payment_date;
    }

    public void setPaymentDate(Date payment_date)
    {
        this.payment_date = payment_date;
    }

    public String getBillingDateString()
    {
        return billing_date_string;
    }

    public void setBillingDateString(String billing_date_string)
    {
        this.billing_date_string = billing_date_string;
    }

    public String getPaymentDateString()
    {
        return payment_date_string;
    }

    public void setPaymentDateString(String payment_date_string)
    {
        this.payment_date_string = payment_date_string;
    }
}
