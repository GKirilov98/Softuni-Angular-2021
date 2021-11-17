package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.Client;
import softuni.angular.data.entities.Policy;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.repositories.PolicyRepository;
import softuni.angular.services.ClientService;
import softuni.angular.views.client.ClientDetailsOutView;
import softuni.angular.views.client.ClientTableOutView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
@Service
public class ClientServiceImpl implements ClientService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ClientRepository clientRepository;
    private final PolicyRepository policyRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, PolicyRepository policyRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.policyRepository = policyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ClientTableOutView> getAll() throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));

            return  this.clientRepository.findAll()
                    .stream()
                    .map(e -> {
                        ClientTableOutView map = this.modelMapper.map(e, ClientTableOutView.class);
                        map.setObjectTypeDescription(e.getClientType().getDescription());
                        return map;
                    })
                    .collect(Collectors.toList());

        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }

    @Override
    public void deleteOneById(Long id) throws GlobalBadRequest, GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOneById service", logId));
            Client client = this.clientRepository.findById(id).orElse(null);
            if (client == null){
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            List<Policy> policies = this.policyRepository.findAllByOptionalClientIdCustom(id);
            this.policyRepository.deleteAll(policies);
            this.clientRepository.delete(client);

        } catch (GlobalBadRequest exc) {
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteOneById service", logId));
        }
    }

    @Override
    public List<ClientDetailsOutView> getOneById(Long id) throws GlobalServiceException {

        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOneById service", logId));
            List<ClientDetailsOutView> result = new ArrayList<>();
            Client client = this.clientRepository.findById(id).orElse(null);
            if (client != null){
                ClientDetailsOutView map = this.modelMapper.map(client, ClientDetailsOutView.class);
                map.setObjectTypeDescription(client.getClientType().getDescription());
                result.add(map);
            }

            return result;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteOneById service", logId));
        }
    }
}

