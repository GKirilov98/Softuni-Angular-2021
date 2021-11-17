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
import softuni.angular.data.entities.InsCompany;
import softuni.angular.data.entities.NClientType;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.views.client.ClientDetailsOutView;
import softuni.angular.views.insCompany.InsCompanyDetailsView;
import softuni.angular.views.insCompany.InsCompanyTableView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InsCompanyControllerTest {
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private InsCompanyRepository insCompanyRepository;

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
        List<InsCompany> companies = this.testInitUtils.initTenInsCompany();
        List<InsCompanyTableView> expectedList = companies
                .stream()
                .map(e -> this.modelMapper.map(e, InsCompanyTableView.class))
                .collect(Collectors.toList());
        String expected = objectMapper.writeValueAsString(expectedList);
        Mockito.when(this.insCompanyRepository.findAllByOptionalNameAndBulstatCustom(null, null))
                .thenReturn(companies);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/InsCompany/"))
                .andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(username = "username_test", password = "password_test", roles = {"ADMIN", "USER"})
    void getOneById_success() throws Exception {
        Long companyId = -1L;
        InsCompany company = this.testInitUtils.initTestInsCompany(companyId);
        List<InsCompanyDetailsView> expectedList = new ArrayList<>();
        InsCompanyDetailsView map = this.modelMapper.map(company, InsCompanyDetailsView.class);
        expectedList.add(map);

        String expected = objectMapper.writeValueAsString(expectedList);
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(java.util.Optional.of(company));
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/InsCompany/" + companyId))
                .andExpect(status().isOk())
                .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }
}