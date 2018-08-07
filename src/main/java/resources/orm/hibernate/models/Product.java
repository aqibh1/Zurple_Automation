package resources.orm.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ns_products", uniqueConstraints = {
        @UniqueConstraint(columnNames = "product_id")})
public class Product
        implements java.io.Serializable {

    private Integer product_id;
    private String recordtype;
    private String displayname;
    private String code_name;

    public Product() {
    }

    public Product(
            Integer product_id,
            String recordtype,
            String displayname,
            String code_name
    ) {
        this.product_id = product_id;
        this.recordtype = recordtype;
        this.displayname = displayname;
        this.code_name = code_name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    public Integer getId() {
        return this.product_id;
    }

    public void setId(Integer product_id) {
        this.product_id = product_id;
    }

    @Column(name = "recordtype", unique = false, nullable = false, length = 50)
    public String getRecordType() {
        return this.recordtype;
    }

    public void setRecordType(String recordtype) {
        this.recordtype = recordtype;
    }

    @Column(name = "displayname", unique = false, nullable = false, length = 150)
    public String getDisplayName() {
        return this.displayname;
    }

    public void setDisplayName(String displayname) {
        this.displayname = displayname;
    }

    @Column(name = "code_name", unique = true, nullable = false, length = 50)
    public String getCodeName() {
        return this.code_name;
    }

    public void setCodeName(String code_name) {
        this.code_name = code_name;
    }

}
