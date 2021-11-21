package softuni.angular.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import softuni.angular.TestInitEntitiesUtils;
import softuni.angular.data.entities.Client;
import softuni.angular.data.entities.NClientType;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.repositories.PolicyRepository;
import softuni.angular.services.ClientService;
import softuni.angular.data.views.client.ClientDetailsOutView;
import softuni.angular.data.views.client.ClientTableOutView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    private  ModelMapper modelMapper ;
    private ClientService clientService;
    private TestInitEntitiesUtils testInitUtils;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PolicyRepository policyRepository;


    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        clientService = new ClientServiceImpl(clientRepository, policyRepository, modelMapper);
        testInitUtils = new TestInitEntitiesUtils();
        testInitUtils.setAuthenticate();
    }

    @Test
    @DisplayName("GetAll Success")
    void getAll_success() throws GlobalServiceException {
        List<Client> entities =  this.testInitUtils.initTenClients();
        List<ClientTableOutView> expected = entities
                .stream().map(e -> {
                    ClientTableOutView map = this.modelMapper.map(e, ClientTableOutView.class);
                    map.setObjectTypeDescription(e.getClientType().getDescription());
                    return map;
                }).collect(Collectors.toList());
        Mockito.when(clientRepository.findAll()).thenReturn(entities);

        List<ClientTableOutView> actual = this.clientService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("GetAll Error")
    void getAll_error() {
        List<Client> entities = new ArrayList<>();
        entities.add(this.testInitUtils.initTestClient(-123L, null));
        Mockito.when(clientRepository.findAll()).thenReturn(entities);

        Assertions.assertThrows(GlobalServiceException.class, () -> {
            this.clientService.getAll();
        });
    }

    @Test
    void deleteOneById_success() {
        Long clientId = -1L;
        Client client = this.testInitUtils.initTestClient(clientId, null);
        Mockito.when(clientRepository.findById(clientId)).thenReturn(java.util.Optional.of(client));
        Mockito.when(this.policyRepository.findAllByOptionalClientIdCustom(clientId))
                .thenReturn(null);
        Assertions.assertDoesNotThrow(() -> {this.clientService.deleteOneById(clientId);});
    }

    @Test
    void deleteOneById_error_GlobalServiceException() {
        Long clientId = -1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class,() -> {this.clientService.deleteOneById(clientId);});
    }

    @Test
    void deleteOneById_error_GlobalBadRequestException() {
        Long clientId = -1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        Assertions.assertThrows(GlobalBadRequest.class,() -> {this.clientService.deleteOneById(clientId);});
    }

    @Test
    void getOneById_Success() throws GlobalServiceException {
        Long clientId = -1L;
        NClientType nClientType = this.testInitUtils.initTestClientTypePerson();
        Client client = this.testInitUtils.initTestClient(clientId, nClientType);
        Mockito.when(clientRepository.findById(clientId)).thenReturn(java.util.Optional.of(client));

        List<ClientDetailsOutView> expected = new ArrayList<>();
        ClientDetailsOutView map = this.modelMapper.map(client, ClientDetailsOutView.class);
        map.setObjectTypeDescription(client.getClientType().getDescription());
        expected.add(map);
        List<ClientDetailsOutView> actual = this.clientService.getOneById(clientId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOneById_Error() {
        Long clientId = -1L;
        Mockito.when(clientRepository.findById(clientId)).thenReturn(null);
        Assertions.assertThrows(GlobalServiceException.class, () -> {
            this.clientService.getOneById(clientId);
        });
    }



}