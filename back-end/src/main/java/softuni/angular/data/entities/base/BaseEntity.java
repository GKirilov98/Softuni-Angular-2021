package softuni.angular.data.entities.base;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "is_active", columnDefinition = "tinyint(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
       this.isActive = isActive;
    }
}
