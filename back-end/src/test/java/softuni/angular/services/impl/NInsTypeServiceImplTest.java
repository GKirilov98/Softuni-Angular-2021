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
import softuni.angular.data.entities.NInsType;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.NInsTypeRepository;
import softuni.angular.services.NInsTypeService;
import softuni.angular.data.views.nomenclature.NomenclatureOutView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class NInsTypeServiceImplTest {
    private ModelMapper modelMapper;
    private TestInitEntitiesUtils testInitUtils;
    private NInsTypeService nInsTypeService;
    @Mock
    private NInsTypeRepository nInsTypeRepository;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        nInsTypeService = new NInsTypeServiceImpl( nInsTypeRepository,modelMapper);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }
    @Test
    void getAll_success() throws GlobalServiceException {
        List<NInsType> entities = this.testInitUtils.initTenNInsType();
        Mockito.when(this.nInsTypeRepository.findAll()).thenReturn(entities);
        List<NomenclatureOutView> expected = entities.stream().map(e -> this.modelMapper.map(e, NomenclatureOutView.class))
                .collect(Collectors.toList());
        List<NomenclatureOutView> actual = this.nInsTypeService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_error_GlobalServiceException() {
        List<NInsType> entities = new ArrayList<>();
        entities.add(null);
        Mockito.when(this.nInsTypeRepository.findAll()).thenReturn(entities);
        Assertions.assertThrows(GlobalServiceException.class, () -> this.nInsTypeService.getAll());
    }
}