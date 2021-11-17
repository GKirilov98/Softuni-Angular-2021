package softuni.angular.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.angular.TestInitEntitiesUtils;
import softuni.angular.data.entities.Role;
import softuni.angular.data.entities.User;
import softuni.angular.repositories.UserRepository;
import softuni.angular.views.user.UserTableOutView;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    @Test
    @WithMockUser(username = "username_test", password = "password_test", roles = {"ADMIN", "USER"})
    void getAll_success() throws Exception {
        List<User> userList = this.testInitUtils.initTenUsers();
        List<UserTableOutView> collect = userList.stream()
                .map(e -> {
                    UserTableOutView map = this.modelMapper.map(e, UserTableOutView.class);
                    List<Role> roles = e.getRoles();
                    map.setRoleCode(roles.stream().map(Role::getCode).toArray(String[]::new));
                    map.setRoleDescription(roles.stream().map(Role::getDescription).collect(Collectors.joining(", ")));
                    return map;
                }).collect(Collectors.toList());
        String expected = objectMapper.writeValueAsString(collect);
        Mockito.when(this.userRepository.findAll()).thenReturn(userList);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/User/"))
                .andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addRemoveAdminRole() {
    }

    @Test
    void deleteUserByUserId() {
    }
}