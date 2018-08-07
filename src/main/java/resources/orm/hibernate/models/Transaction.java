package resources.orm.hibernate.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ns_transactions", uniqueConstraints = {})
public class Transaction
        implements java.io.Serializable {

    @Id
    @Column(name="transaction_id")
    private Integer transaction_id;

    @Column(name = "customer_id")
    private Integer customer_id;

    @Column(name = "item_id")
    private Integer item_id;

    @Column(name = "custbody_billing_schedule_created")
    private Integer custbody_billing_schedule_created;

    @Column(name = "recordtype")
    private String recordtype;

    @Column(name = "type_id")
    private String type_id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type_title")
    private String type_title;

    @Column(name = "statusref_id")
    private String statusref_id;

    @Column(name = "statusref_title")
    private String statusref_title;

    @Column(name = "item_title")
    private String item_title;

    @Column(name = "salesdescription")
    private String salesdescription;

    @Column(name = "accounttype")
    private String accounttype;

    @Column(name = "tran_date", unique = false, nullable = true)
    private Date tran_date;

    @Column(name = "saleseffective_date", unique = false, nullable = true)
    private Date saleseffective_date;

    public Integer getTransaction_id()
    {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    public Integer getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id)
    {
        this.customer_id = customer_id;
    }

    public Integer getItem_id()
    {
        return item_id;
    }

    public void setItem_id(Integer item_id)
    {
        this.item_id = item_id;
    }

    public Integer getCustbody_billing_schedule_created()
    {
        return custbody_billing_schedule_created;
    }

    public void setCustbody_billing_schedule_created(Integer custbody_billing_schedule_created)
    {
        this.custbody_billing_schedule_created = custbody_billing_schedule_created;
    }

    public String getRecordtype()
    {
        return recordtype;
    }

    public void setRecordtype(String recordtype)
    {
        this.recordtype = recordtype;
    }

    public String getType_id()
    {
        return type_id;
    }

    public void setType_id(String type_id)
    {
        this.type_id = type_id;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public String getType_title()
    {
        return type_title;
    }

    public void setType_title(String type_title)
    {
        this.type_title = type_title;
    }

    public String getStatusref_id()
    {
        return statusref_id;
    }

    public void setStatusref_id(String statusref_id)
    {
        this.statusref_id = statusref_id;
    }

    public String getStatusref_title()
    {
        return statusref_title;
    }

    public void setStatusref_title(String statusref_title)
    {
        this.statusref_title = statusref_title;
    }

    public String getItem_title()
    {
        return item_title;
    }

    public void setItem_title(String item_title)
    {
        this.item_title = item_title;
    }

    public String getSalesdescription()
    {
        return salesdescription;
    }

    public void setSalesdescription(String salesdescription)
    {
        this.salesdescription = salesdescription;
    }

    public String getAccounttype()
    {
        return accounttype;
    }

    public void setAccounttype(String accounttype)
    {
        this.accounttype = accounttype;
    }

    public Date getTran_date()
    {
        return tran_date;
    }

    public void setTran_date(Date tran_date)
    {
        this.tran_date = tran_date;
    }

    public Date getSaleseffective_date()
    {
        return saleseffective_date;
    }

    public void setSaleseffective_date(Date saleseffective_date)
    {
        this.saleseffective_date = saleseffective_date;
    }
}
