package softuni.angular.data.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import softuni.angular.data.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Entity
@Table(name = "res_error_log")
public class ResErrorLog extends BaseEntity {
    private String method;
    private String url;
    private Integer status;
    private String username;
    private DateTime responseOn;
    private String error;

    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "response_on", columnDefinition = "TIMESTAMP", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getResponseOn() {
        return responseOn;
    }

    public void setResponseOn(DateTime responseOn) {
        this.responseOn = responseOn;
    }

    @Column(name = "error", columnDefinition = "TEXT")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
