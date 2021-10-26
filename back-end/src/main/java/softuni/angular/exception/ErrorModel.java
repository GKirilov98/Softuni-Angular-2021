package softuni.angular.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import softuni.angular.utils.joda.JodaTimeSerializer;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
public class ErrorModel {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("message")
    private String message;
    @JsonProperty("systemMessage")
    private String systemMessage;
    @JsonProperty("path")
    private String path;
    @JsonProperty("dateTime")
    @JsonSerialize(using = JodaTimeSerializer.class)
    private DateTime dateTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
