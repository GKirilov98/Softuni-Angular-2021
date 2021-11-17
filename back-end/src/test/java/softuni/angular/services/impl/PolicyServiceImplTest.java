package softuni.angular.services.impl;

import org.joda.time.DateTime;
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
import softuni.angular.data.entities.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.*;
import softuni.angular.services.PolicyService;
import softuni.angular.views.policy.PolicyCalculationOutView;
import softuni.angular.views.policy.PolicyDetailsView;
import softuni.angular.views.policy.PolicyInsertInView;
import softuni.angular.views.policy.PolicyTableOutView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.angular.services.impl.PolicyServiceImpl.N_CLIENT_TYPE_LEGAL;
import static softuni.angular.services.impl.PolicyServiceImpl.N_CLIENT_TYPE_PERSON;

@ExtendWith(MockitoExtension.class)
class PolicyServiceImplTest {
    private TestInitEntitiesUtils testInitUtils;
    private ModelMapper modelMapper;
    private PolicyService policyService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private InsProductRepository productRepository;
    @Mock
    private NInsObjectTypeRepository nInsObjectTypeRepository;
    @Mock
    private NClientTypeRepository nClientTypeRepository;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        policyService = new PolicyServiceImpl(modelMapper, clientRepository, policyRepository, productRepository, nInsObjectTypeRepository, nClientTypeRepository);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    void insertOne_success_new_person() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        Long insObjecTypeId = -1L;
        PolicyInsertInView inView = this.initPolicyInsertInViewNewPerson(insProductId, insObjecTypeId);
        Mockito.when(this.policyRepository.existsByIdentityNumber(inView.getIdentityNumber())).thenReturn(false);

        NClientType nClientType = this.testInitUtils.initTestClientTypePerson();
        Mockito.when(this.nClientTypeRepository.findByCode(N_CLIENT_TYPE_PERSON)).thenReturn(nClientType);

        Client client = this.modelMapper.map(inView, Client.class);
        Mockito.when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(inView.getInsProductId())).thenReturn(java.util.Optional.ofNullable(insProduct));

        NInsObjectType nInsObjectType = this.testInitUtils.initNInsObjectType(insObjecTypeId);
        Mockito.when(this.nInsObjectTypeRepository.findById(inView.getInsObjectTypeId())).thenReturn(java.util.Optional.ofNullable(nInsObjectType));
        Assertions.assertDoesNotThrow(() -> this.policyService.insertOne(inView));
    }

    @Test
    void insertOne_success_new_legal() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        Long insObjecTypeId = -1L;
        PolicyInsertInView inView = this.initPolicyInsertInViewNewLegal(insProductId, insObjecTypeId);
        Mockito.when(this.policyRepository.existsByIdentityNumber(inView.getIdentityNumber())).thenReturn(false);

        NClientType nClientType = this.testInitUtils.initTestClientTypePerson();
        Mockito.when(this.nClientTypeRepository.findByCode(N_CLIENT_TYPE_LEGAL)).thenReturn(nClientType);

        Client client = this.modelMapper.map(inView, Client.class);
        Mockito.when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(inView.getInsProductId())).thenReturn(java.util.Optional.ofNullable(insProduct));

        NInsObjectType nInsObjectType = this.testInitUtils.initNInsObjectType(insObjecTypeId);
        Mockito.when(this.nInsObjectTypeRepository.findById(inView.getInsObjectTypeId())).thenReturn(java.util.Optional.ofNullable(nInsObjectType));
        Assertions.assertDoesNotThrow(() -> this.policyService.insertOne(inView));
    }


    @Test
    void getCalculations_success() throws GlobalServiceException {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        BigDecimal sum = BigDecimal.TEN;
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(java.util.Optional.ofNullable(insProduct));
        List<PolicyCalculationOutView> expected = new ArrayList<>();
        PolicyCalculationOutView expectedElement = new PolicyCalculationOutView();
        expectedElement.setPremium(
                sum.multiply(Objects.requireNonNull(insProduct).getPremiumPercent()
                        .divide(BigDecimal.valueOf(100))));
        expectedElement.setTax(expectedElement.getPremium().multiply(BigDecimal.valueOf(0.02)));
        expectedElement.setCommission(sum.multiply(insProduct.getComissionPercent().divide(BigDecimal.valueOf(100))));
        expected.add(expectedElement);

        List<PolicyCalculationOutView> actual = this.policyService.getCalculations(insProductId, sum);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_success() throws GlobalServiceException {
        Long clientId = null;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        Mockito.when(this.policyRepository.findAllByOptionalClientIdCustom(clientId)).thenReturn(policies);
        List<PolicyTableOutView> expected = policies.stream()
                .map(e -> {
                    PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                    map.setProductName(e.getInsProduct().getName());
                    return map;
                })
                .collect(Collectors.toList());
        List<PolicyTableOutView> actual = this.policyService.getAll(clientId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_error_GlobalServiceException() {
        Long clientId = null;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        policies.forEach(e -> e.setInsProduct(null));
        Mockito.when(this.policyRepository.findAllByOptionalClientIdCustom(clientId)).thenReturn(policies);
        Assertions.assertThrows(GlobalServiceException.class, () -> this.policyService.getAll(clientId));
    }

    @Test
    void getOneById_success() throws GlobalServiceException {
        Long policyId = -1L;
        Long productId = -1L;
        Long clientId = -1L;
        Policy policy = this.testInitUtils.initTestPolicy(policyId, productId, clientId);
        Mockito.when(this.policyRepository.findById(policyId)).thenReturn(java.util.Optional.ofNullable(policy));
        List<PolicyDetailsView> expected = new ArrayList<>();

        PolicyDetailsView expectedElement = this.modelMapper.map(policy, PolicyDetailsView.class);
        expectedElement.setPolicyNote(policy.getNote());
        this.modelMapper.map(policy.getClient(), expectedElement);
        expectedElement.setId(policy.getId());
        expectedElement.setClientNote(policy.getClient().getNote());
        expectedElement.setClientTypeCode(policy.getClient().getClientType().getCode());
        expectedElement.setClientTypeDescription(policy.getClient().getClientType().getDescription());
        expectedElement.setProductName(policy.getInsProduct().getName() + " / " + policy.getInsProduct().getInsCompany().getName());
        expectedElement.setObjectTypeDescription(policy.getInsObjectType().getDescription());
        expected.add(expectedElement);

        List<PolicyDetailsView> actual = this.policyService.getOneById(policyId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOneById_error_GlobalServiceException()  {
        Long policyId = -1L;
        Long productId = -1L;
        Long clientId = -1L;
        Policy policy = this.testInitUtils.initTestPolicy(policyId, productId, clientId);
        policy.setInsObjectType(null);
        Mockito.when(this.policyRepository.findById(policyId)).thenReturn(java.util.Optional.of(policy));

        Assertions.assertThrows(GlobalServiceException.class, ()->this.policyService.getOneById(policyId));
    }

    @Test
    void deleteOne_success() {
        Long policyId = -1L;
        Long productId = -1L;
        Long clientId = -1L;
        Policy policy = this.testInitUtils.initTestPolicy(policyId, productId, clientId);
        Mockito.when(this.policyRepository.findById(policyId)).thenReturn(java.util.Optional.ofNullable(policy));
        Assertions.assertDoesNotThrow(() -> this.policyService.deleteOne(policyId));
    }

    @Test
    void deleteOne_error_GlobalBadRequest() {
        Long policyId = -1L;
        Mockito.when(this.policyRepository.findById(policyId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class,() -> this.policyService.deleteOne(policyId));
    }

    @Test
    void getAllByProductId_success() throws GlobalServiceException {
        Long productId = -1L;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(productId)).thenReturn(policies);
        List<PolicyTableOutView> expected = policies.stream()
                .map(e -> {
                    PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                    map.setProductName(e.getInsProduct().getName());
                    return map;
                })
                .collect(Collectors.toList());
        List<PolicyTableOutView> actual = this.policyService.getAllByProductId(productId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllByProductId_error_GlobalServiceException()  {
        Long productId = -1L;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        policies.forEach(e -> e.setInsProduct(null));
        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(productId)).thenReturn(policies);

        Assertions.assertThrows(GlobalServiceException.class,() -> this.policyService.getAllByProductId(productId));
    }

    @Test
    void getAllByClientId_success() throws GlobalServiceException {
        Long clientId = -1L;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        Mockito.when(this.policyRepository.findAllByOptionalClientIdCustom(clientId)).thenReturn(policies);
        List<PolicyTableOutView> expected = policies.stream()
                .map(e -> {
                    PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                    map.setProductName(e.getInsProduct().getName());
                    return map;
                })
                .collect(Collectors.toList());
        List<PolicyTableOutView> actual = this.policyService.getAllByClientId(clientId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllByClientId_error_GlobalServiceException()  {
        Long clientId = -1L;
        List<Policy> policies = this.testInitUtils.initTenPolicies();
        policies.forEach(e -> e.setInsProduct(null));
        Mockito.when(this.policyRepository.findAllByOptionalClientIdCustom(clientId)).thenReturn(policies);

        Assertions.assertThrows(GlobalServiceException.class,() -> this.policyService.getAllByClientId(clientId));
    }


    private PolicyInsertInView initPolicyInsertInViewNewLegal(Long insProductId, Long insObjecTypeId) {
        PolicyInsertInView inView = new PolicyInsertInView();
        inView.setLegal(true);
        inView.setNew(true);
        inView.setName("Тестови клиент Фирма");
        inView.setMiddleName("Тестови клиент бащинно име");
        inView.setLastName("Тестови клиент фамилия");
        inView.setEgn("999999999");
        inView.setBulstat("999999999");
        inView.setEmail("email@Email.com");
        inView.setPhoneNumber("8888888888");
        inView.setAddress("Address");
        inView.setNote("Записки");
        inView.setIdentityNumber("123123123");
        inView.setSum(BigDecimal.TEN);
        inView.setInsProductId(insProductId);
        inView.setObjectName("Име на обекта");
        inView.setInsObjectTypeId(insObjecTypeId);
        inView.setObjectDescription("Описание на обекта");
        inView.setPolicyNote("Записки на полица");
        inView.setBeginDate(DateTime.now());
        inView.setEndDate(DateTime.now().plusDays(10));
        return inView;
    }

    private PolicyInsertInView initPolicyInsertInViewNewPerson(Long insProductId, Long insObjecTypeId) {
        PolicyInsertInView inView = new PolicyInsertInView();
        inView.setLegal(false);
        inView.setNew(true);
        inView.setName("Тестови клиент");
        inView.setMiddleName("Тестови клиент бащинно име");
        inView.setLastName("Тестови клиент фамилия");
        inView.setEgn("999999999");
        inView.setBulstat("999999999");
        inView.setEmail("email@Email.com");
        inView.setPhoneNumber("8888888888");
        inView.setAddress("Address");
        inView.setNote("Записки");
        inView.setIdentityNumber("123123123");
        inView.setSum(BigDecimal.TEN);
        inView.setInsProductId(insProductId);
        inView.setObjectName("Име на обекта");
        inView.setInsObjectTypeId(insObjecTypeId);
        inView.setObjectDescription("Описание на обекта");
        inView.setPolicyNote("Записки на полица");
        inView.setBeginDate(DateTime.now());
        inView.setEndDate(DateTime.now().plusDays(10));
        return inView;
    }
}