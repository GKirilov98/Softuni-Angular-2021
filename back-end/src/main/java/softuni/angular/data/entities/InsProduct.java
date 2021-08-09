package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Entity(name ="ins_products")
public class InsProduct extends BaseEntity {
    @Basic
    @Column(name = "n_ins_type_id")
    private Integer insTypeId;

    @Basic
    @Column(name = "ins_company_id")
    private Integer insCompanyId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "defered")
    private Boolean defered;

    @Basic
    @Column(name = "prem_perc")
    private BigDecimal premiumPercent;

    @Basic
    @Column(name = "comiss_perc")
    private BigDecimal comissionPercent;

    public Integer getInsTypeId() {
        return insTypeId;
    }

    public void setInsTypeId(Integer insTypeId) {
        this.insTypeId = insTypeId;
    }

    public Integer getInsCompanyId() {
        return insCompanyId;
    }

    public void setInsCompanyId(Integer insCompanyId) {
        this.insCompanyId = insCompanyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefered() {
        return defered;
    }

    public void setDefered(Boolean defered) {
        this.defered = defered;
    }

    public BigDecimal getPremiumPercent() {
        return premiumPercent;
    }

    public void setPremiumPercent(BigDecimal premiumPercent) {
        this.premiumPercent = premiumPercent;
    }

    public BigDecimal getComissionPercent() {
        return comissionPercent;
    }

    public void setComissionPercent(BigDecimal comissionPercent) {
        this.comissionPercent = comissionPercent;
    }
}
