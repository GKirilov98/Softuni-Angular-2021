package softuni.angular.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import softuni.angular.data.entities.Client;
import softuni.angular.data.entities.NClientType;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.views.client.ClientDetailsOutView;
import softuni.angular.views.client.ClientTableOutView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    @WithMockUser(username = "username_test", password = "password_test", roles = {"ADMIN", "USER"})
    void getAll_success() throws Exception {
        List<Client> clients = this.testInitUtils.initTenClients();
        List<ClientTableOutView> expectedList = clients.stream()
                .map(e -> {
                    ClientTableOutView map = this.modelMapper.map(e, ClientTableOutView.class);
                    map.setObjectTypeDescription(e.getClientType().getDescription());
                    return map;
                })
                .collect(Collectors.toList());
        String expected = objectMapper.writeValueAsString(expectedList);
        Mockito.when(this.clientRepository.findAll()).thenReturn(clients);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/Client/"))
                .andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(username = "username_test", password = "password_test", roles = {"ADMIN", "USER"})
    void getOneById_success() throws Exception {
        Long clientId = -1L;
        NClientType nClientType = this.testInitUtils.initTestClientTypePerson();
        Client client = this.testInitUtils.initTestClient(clientId, nClientType);
        List<ClientDetailsOutView> expectedList = new ArrayList<>();
        ClientDetailsOutView map = this.modelMapper.map(client, ClientDetailsOutView.class);
        map.setObjectTypeDescription(client.getClientType().getDescription());
        expectedList.add(map);

        String expected = objectMapper.writeValueAsString(expectedList);
        Mockito.when(this.clientRepository.findById(clientId)).thenReturn(java.util.Optional.of(client));
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/Client/" + clientId))
                .andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }
}