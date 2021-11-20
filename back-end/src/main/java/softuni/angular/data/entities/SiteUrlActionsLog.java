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
 * On: 11/18/2021
 */
@Entity
@Table(name = "site_url_actions")
public class SiteUrlActionsLog extends BaseEntity {
    private String method;
    private String url;
    private String username;
    private String  requestToken;
    private DateTime requestOn;

    @Column
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String  getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String  requestToken) {
        this.requestToken = requestToken;
    }

    @Column(name = "request_on", columnDefinition = "TIMESTAMP", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(DateTime requestOn) {
        this.requestOn = requestOn;
    }
}
