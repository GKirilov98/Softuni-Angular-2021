package softuni.angular.security.model;
import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:35 PM
 */
public class AuthenticateResponseModel {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
//    private String email;
    private List<String> roles;

    public AuthenticateResponseModel(String accessToken, Integer id, String username,
//                                     String email,
                                     List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
//        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
