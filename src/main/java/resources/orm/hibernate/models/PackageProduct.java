package resources.orm.hibernate.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name = "ns_package_products", catalog = "zurple_platform", uniqueConstraints = {})
public class PackageProduct
        implements java.io.Serializable {

    @Id
    @Column(name="package_id")
    private Integer package_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product_id;

    @Column(name = "nextbill_date", unique = false, nullable = true)
    private Date nextbill_date;

    @Column(name = "fee", unique = false, nullable = false)
    private BigDecimal fee;

    public PackageProduct() {
    }

    public PackageProduct(
            Integer package_id,
            Product product_id,
            Date nextbill_date,
            BigDecimal fee
    ) {
        this.package_id = package_id;
        this.product_id = product_id;
        this.nextbill_date = nextbill_date;
        this.fee = fee;
    }

    public Integer getPackageId() {
        return this.package_id;
    }

    public void setPackageId(Integer package_id) {
        this.package_id = package_id;
    }

    public Product getProductId() {
        return this.product_id;
    }

    public Date getNextBillDate() {
        return this.nextbill_date;
    }

    public void setNextBillDate(Date nextbill_date) {
        this.nextbill_date = nextbill_date;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

}
