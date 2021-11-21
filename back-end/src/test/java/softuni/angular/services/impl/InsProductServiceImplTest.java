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
import softuni.angular.data.entities.InsProduct;
import softuni.angular.data.entities.NInsType;
import softuni.angular.data.entities.Policy;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.*;
import softuni.angular.services.InsProductService;
import softuni.angular.data.views.insProduct.InsProductCompanyTableView;
import softuni.angular.data.views.insProduct.InsProductDetailsView;
import softuni.angular.data.views.insProduct.InsProductInView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class InsProductServiceImplTest {
    private InsProductService productService;
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    @Mock
    private InsProductRepository productRepository;
    @Mock
    private InsCompanyRepository insCompanyRepository;
    @Mock
    private NInsTypeRepository nInsTypeRepository;
    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        productService = new InsProductServiceImpl(productRepository, modelMapper, insCompanyRepository, nInsTypeRepository, policyRepository, clientRepository);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    void insertOne_success() {
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);

        InsCompany company = this.testInitUtils.initTestInsCompany(insCompanyId);
        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(java.util.Optional.ofNullable(company));
        NInsType nInsType = this.testInitUtils.initTestInsType(insTypeId);
        Mockito.when(this.nInsTypeRepository.findById(inView.getInsTypeId())).thenReturn(java.util.Optional.ofNullable(nInsType));
        Assertions.assertDoesNotThrow(() -> this.productService.insertOne(inView));
    }

    @Test
    void insertOne_error_GlobalBadRequest_company() {
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);

        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.insertOne(inView));
    }

    @Test
    void insertOne_error_GlobalBadRequest_type() {
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);

        InsCompany company = this.testInitUtils.initTestInsCompany(insCompanyId);
        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(java.util.Optional.ofNullable(company));
        Mockito.when(this.nInsTypeRepository.findById(inView.getInsTypeId())).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.insertOne(inView));
    }


    @Test
    void insertOne_error_GlobalServiceException() {
        Assertions.assertThrows(GlobalServiceException.class, () -> this.productService.insertOne(null));
    }


    @Test
    void getAllByCompanyId_success() throws GlobalServiceException {
        Long companyId = -1L;
        List<InsProduct> entities = this.testInitUtils.initTenInsProductWithCompanyId(companyId);
        Mockito.when(this.productRepository.findAllByInsCompanyId(companyId)).thenReturn(entities);

        List<InsProductCompanyTableView> expected = entities.stream()
                .map(e -> {
                    InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                    map.setInsTypeDescription(e.getInsType().getDescription());
                    return map;
                }).collect(Collectors.toList());
        List<InsProductCompanyTableView> actual = this.productService.getAllByCompanyId(companyId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllByCompanyId_error_GlobalServiceException() {
        Long companyId = -1L;
        Mockito.when(this.productRepository.findAllByInsCompanyId(companyId)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class, () -> this.productService.getAllByCompanyId(companyId));
    }

    @Test
    void getOneById_success() throws GlobalServiceException {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));

        List<InsProductDetailsView> expected = new ArrayList<>();
        InsProductDetailsView expectedElement = this.modelMapper.map(insProduct, InsProductDetailsView.class);
        expectedElement.setInsTypeId(Objects.requireNonNull(insProduct).getInsType().getId());
        expectedElement.setInsTypeDescription(insProduct.getInsType().getDescription());
        expectedElement.setInsCompanyId(insProduct.getInsCompany().getId());
        expectedElement.setInsCompanyName(insProduct.getInsCompany().getName());
        expected.add(expectedElement);

        List<InsProductDetailsView> actual = this.productService.getOneById(insProductId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOneById_error_GlobalServiceException() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        insProduct.setInsType(null);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.of(insProduct));

        Assertions.assertThrows(GlobalServiceException.class, () -> this.productService.getOneById(insProductId));
    }

    @Test
    void updateOne_success() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);

        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));

        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(insProductId)).thenReturn(new ArrayList<>());

        InsCompany company = this.testInitUtils.initTestInsCompany(insCompanyId);
        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(java.util.Optional.ofNullable(company));

        NInsType nInsType = this.testInitUtils.initTestInsType(insTypeId);
        Mockito.when(this.nInsTypeRepository.findById(inView.getInsTypeId())).thenReturn(java.util.Optional.ofNullable(nInsType));
        Assertions.assertDoesNotThrow(() -> this.productService.updateOne(insProductId,inView));
    }


    @Test
    void updateOne_error_GlobalBadRequest_product() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.updateOne(insProductId, inView));
    }

    @Test
    void updateOne_error_GlobalBadRequest_policy() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));
        List<Policy> policies = testInitUtils.initTenPolicies();
        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(insProductId)).thenReturn(policies);
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.updateOne(insProductId, inView));
    }


    @Test
    void updateOne_error_GlobalBadRequest_company() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));
        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(insProductId)).thenReturn(new ArrayList<>());
        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.updateOne(insProductId, inView));
    }


    @Test
    void updateOne_error_GlobalBadRequest_type() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProductInView inView = initInsProductInView(insTypeId, insCompanyId);
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));

        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(insProductId)).thenReturn(new ArrayList<>());

        InsCompany company = this.testInitUtils.initTestInsCompany(insCompanyId);
        Mockito.when(this.insCompanyRepository.findById(inView.getInsCompanyId())).thenReturn(java.util.Optional.ofNullable(company));


        Mockito.when(this.nInsTypeRepository.findById(inView.getInsTypeId())).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.updateOne(insProductId, inView));
    }

    @Test
    void updateOne_error_GlobalServiceException() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));

        Mockito.when(this.policyRepository.findAllByInsProductIdCustom(insProductId)).thenReturn(new ArrayList<>());
        Assertions.assertThrows(GlobalServiceException.class, () -> this.productService.updateOne(insProductId, null));
    }

    @Test
    void deleteOne_success() {
        Long insProductId = -1L;
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        InsProduct insProduct = this.testInitUtils.initTestInsProduct(insProductId, insTypeId, insCompanyId);
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.ofNullable(insProduct));
        Assertions.assertDoesNotThrow(() -> this.productService.deleteOne(insProductId));
    }

    @Test
    void deleteOne_error_GlobalBadRequest() {
        Long insProductId = -1L;
        Mockito.when(this.productRepository.findById(insProductId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class, () -> this.productService.deleteOne(insProductId));
    }

    @Test
    void getAllByTypeId_success() throws GlobalServiceException {
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        List<InsProduct> products = this.testInitUtils.initTenInsProductWithCompanyId(insCompanyId);
        Mockito.when(this.productRepository.findAllByInsTypeId(insTypeId)).thenReturn(products);
        List<InsProductCompanyTableView> expected = products.stream()
                .map(e -> {
                    InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                    map.setName(e.getName() + " / " + e.getInsCompany().getName());
                    return map;
                }).collect(Collectors.toList());
        List<InsProductCompanyTableView> actual = this.productService.getAllByTypeId(insTypeId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllByTypeId_error_GlobalServiceException() {
        Long insTypeId = -1L;
        Long insCompanyId = -1L;
        List<InsProduct> products = this.testInitUtils.initTenInsProductWithCompanyId(insCompanyId);
        products.forEach(e -> e.setInsCompany(null));
        Mockito.when(this.productRepository.findAllByInsTypeId(insTypeId)).thenReturn(products);
        Assertions.assertThrows( GlobalServiceException.class,() -> this.productService.getAllByTypeId(insTypeId));
    }

    @Test
    void getAll_success() throws GlobalServiceException {
        Long insCompanyId = -1L;
        List<InsProduct> products = this.testInitUtils.initTenInsProductWithCompanyId(insCompanyId);
        Mockito.when(this.productRepository.findAll()).thenReturn(products);
        List<InsProductCompanyTableView> expected = products.stream()
                .map(e -> {
                    InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                    map.setInsTypeDescription(e.getInsType().getDescription());
                    return map;
                })
                .collect(Collectors.toList());
        List<InsProductCompanyTableView> actual = this.productService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_error_GlobalServiceException()  {
        Long insCompanyId = -1L;
        List<InsProduct> products = this.testInitUtils.initTenInsProductWithCompanyId(insCompanyId);
        products.forEach(e -> e.setInsType(null));
        Mockito.when(this.productRepository.findAll()).thenReturn(products);
        Assertions.assertThrows( GlobalServiceException.class,() ->this.productService.getAll());
    }


    private InsProductInView initInsProductInView(Long insTypeId, Long insCompanyId) {
        InsProductInView inView = new InsProductInView();
        inView.setName("Tест продукт");
        inView.setDefered(false);
        inView.setComissionPercent(BigDecimal.TEN);
        inView.setPremiumPercent(BigDecimal.ONE);
        inView.setInsTypeId(insTypeId);
        inView.setInsCompanyId(insCompanyId);
        return inView;
    }
}