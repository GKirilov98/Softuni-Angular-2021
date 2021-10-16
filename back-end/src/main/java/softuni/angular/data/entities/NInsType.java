package softuni.angular.data.entities;

import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */

@Entity
@Table(name = "n_ins_types")
public class NInsType extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
