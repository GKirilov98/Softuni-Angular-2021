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
import softuni.angular.data.entities.NInsObjectType;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.NInsObjectTypeRepository;
import softuni.angular.services.NInsObjectTypeService;
import softuni.angular.views.nomenclature.NomenclatureOutView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class NInsObjectTypeServiceImplTest {
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    private NInsObjectTypeService insObjectTypeService;
    @Mock
    private NInsObjectTypeRepository nInsObjectTypeRepository;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        insObjectTypeService = new NInsObjectTypeServiceImpl(modelMapper, nInsObjectTypeRepository);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    void getAll_success() throws GlobalServiceException {
        List<NInsObjectType> entities = this.testInitUtils.initTenNInsObjectType();
        Mockito.when(this.nInsObjectTypeRepository.findAll()).thenReturn(entities);
        List<NomenclatureOutView> expected = entities.stream().map(e -> this.modelMapper.map(e, NomenclatureOutView.class))
                .collect(Collectors.toList());
        List<NomenclatureOutView> actual = this.insObjectTypeService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_error_GlobalServiceException() {
        List<NInsObjectType> entities = new ArrayList<>();
        entities.add(null);
        Mockito.when(this.nInsObjectTypeRepository.findAll()).thenReturn(entities);
        Assertions.assertThrows(GlobalServiceException.class, () -> this.insObjectTypeService.getAll());
    }
}