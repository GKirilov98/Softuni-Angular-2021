package softuni.angular.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import softuni.angular.TestInitEntitiesUtils;
import softuni.angular.data.entities.InsCompany;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.repositories.InsProductRepository;
import softuni.angular.repositories.PolicyRepository;
import softuni.angular.services.InsCompanyService;
import softuni.angular.views.insCompany.InsCompanyDetailsView;
import softuni.angular.views.insCompany.InsCompanyInView;
import softuni.angular.views.insCompany.InsCompanyTableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class InsCompanyServiceImplTest {
    private  ModelMapper modelMapper ;
    private InsCompanyService insCompanyService;
    private TestInitEntitiesUtils testInitUtils;
    @Mock
    private  InsCompanyRepository insCompanyRepository;
    @Mock
    private  PolicyRepository policyRepository;
    @Mock
    private  InsProductRepository productRepository;
    @Mock
    private  ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        insCompanyService = new InsCompanyServiceImpl(modelMapper, insCompanyRepository, policyRepository, productRepository, clientRepository);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    void insertOne_success() {
        InsCompanyInView inView = this.initInsCompanyInView();
        Mockito.when(this.insCompanyRepository.existsByBulstat(inView.getBulstat())).thenReturn(false);
        Mockito.when(this.insCompanyRepository.save(Mockito.any(InsCompany.class))).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> {this.insCompanyService.insertOne(inView);});
    }

    @Test
    void insertOne_error_GlobalBadRequestException() {
        InsCompanyInView inView = this.initInsCompanyInView();
        Mockito.when(this.insCompanyRepository.existsByBulstat(inView.getBulstat())).thenReturn(true);
        Assertions.assertThrows(GlobalBadRequest.class,() -> {this.insCompanyService.insertOne(inView);});
    }

    @Test
    void insertOne_error_GlobalServiceException() {
        Assertions.assertThrows(GlobalServiceException.class,() -> {this.insCompanyService.insertOne(null);});
    }

    @Test
    void updateOne_success() {
        Long companyId = -1L;
        InsCompanyInView inView = this.initInsCompanyInView();
        InsCompany map = this.modelMapper.map(inView, InsCompany.class);
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(java.util.Optional.ofNullable(map));
        Assertions.assertDoesNotThrow(() -> {this.insCompanyService.updateOne(companyId, inView);});
    }

    @Test
    void updateOne_error_GlobalBadRequest() {
        Long companyId = -1L;
        InsCompanyInView inView = this.initInsCompanyInView();
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> {this.insCompanyService.updateOne(companyId, inView);});
    }

    @Test
    void updateOne_error_GlobalServiceException() {
        Long companyId = -1L;
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class, () -> {this.insCompanyService.updateOne(companyId, null);});
    }

    @Test
    void getAll_success() throws GlobalServiceException {
        String name = null;
        String bulstat = null;
        List<InsCompany> entities = this.testInitUtils.initTenInsCompany();
        Mockito.when(this.insCompanyRepository.findAllByOptionalNameAndBulstatCustom(name, bulstat)).thenReturn(entities);
        List<InsCompanyTableView> expected = entities.stream().map(e -> this.modelMapper.map(e, InsCompanyTableView.class)).collect(Collectors.toList());
        List<InsCompanyTableView> actual = this.insCompanyService.getAll(name, bulstat);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_error_GlobalServiceException()  {
        String name = null;
        String bulstat = null;
        Mockito.when(this.insCompanyRepository.findAllByOptionalNameAndBulstatCustom(name, bulstat)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class, () -> {this.insCompanyService.getAll(name, bulstat);});
    }

    @Test
    void getOneById_success() throws GlobalServiceException {
        Long companyId = -1L;
        InsCompany company = this.testInitUtils.initTestInsCompany(companyId);
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(Optional.ofNullable(company));
        List<InsCompanyDetailsView> expected = new ArrayList<>();
        InsCompanyDetailsView expectedElement = this.modelMapper.map(company, InsCompanyDetailsView.class);
        expected.add(expectedElement);
        List<InsCompanyDetailsView> actual = this.insCompanyService.getOneById(companyId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOneById_error_GlobalServiceException(){
        Long companyId = -1L;
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalServiceException.class, () -> {this.insCompanyService.getOneById(companyId);});
    }

    @Test
    void deleteOne_success() {
        Long companyId = -1L;
        InsCompany company = this.testInitUtils.initTestInsCompany(companyId);
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(Optional.ofNullable(company));
        Assertions.assertDoesNotThrow(() -> {this.insCompanyService.getOneById(companyId);});
    }

    @Test
    void deleteOne_error_GlobalBadRequest(){
        Long companyId = -1L;
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> {this.insCompanyService.deleteOne(companyId);});
    }

    @Test
    void deleteOne_error_GlobalServiceException(){
        Long companyId = -1L;
        Mockito.when(this.insCompanyRepository.findById(companyId)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class, () -> {this.insCompanyService.deleteOne(companyId);});
    }


    private InsCompanyInView initInsCompanyInView(){
        InsCompanyInView inView = new InsCompanyInView();
        inView.setName("Тест компания");
        inView.setPhone("123141");
        inView.setEmail("email@emai.com");
        inView.setBulstat("9991231312");
        inView.setAddress("Тестови адрес на компания");
        return inView;
    }
}