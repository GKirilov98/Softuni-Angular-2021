package softuni.angular.views.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
public class NomenclatureOutView implements Serializable {
    private Long id;
    private String code;
    private String description;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
