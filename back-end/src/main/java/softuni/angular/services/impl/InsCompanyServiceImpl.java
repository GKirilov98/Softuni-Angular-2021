package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.Client;
import softuni.angular.data.entities.InsCompany;
import softuni.angular.data.entities.InsProduct;
import softuni.angular.data.entities.Policy;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.repositories.InsProductRepository;
import softuni.angular.repositories.PolicyRepository;
import softuni.angular.services.InsCompanyService;
import softuni.angular.data.views.insCompany.InsCompanyDetailsView;
import softuni.angular.data.views.insCompany.InsCompanyInView;
import softuni.angular.data.views.insCompany.InsCompanyTableView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
@Service
public class InsCompanyServiceImpl implements InsCompanyService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final InsCompanyRepository insCompanyRepository;
    private final PolicyRepository policyRepository;
    private final InsProductRepository productRepository;
    private final ClientRepository clientRepository;

    public InsCompanyServiceImpl(ModelMapper modelMapper, InsCompanyRepository insCompanyRepository, PolicyRepository policyRepository, InsProductRepository productRepository, ClientRepository clientRepository) {
        this.modelMapper = modelMapper;
        this.insCompanyRepository = insCompanyRepository;
        this.policyRepository = policyRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void insertOne(InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            if (this.insCompanyRepository.existsByBulstat(inView.getBulstat())) {
                throw new GlobalBadRequest("Вече има съществуваща фирма с този Булстат!",
                        new Throwable("Already exist company with this bulstat!"));
            }

            InsCompany map = this.modelMapper.map(inView, InsCompany.class);
            this.insCompanyRepository.save(map);
        } catch (GlobalBadRequest exc) {
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished insertOne service", logId));
        }
    }

    @Override
    public void updateOne(Long id, InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start updateOne service", logId));
            InsCompany insCompany = this.insCompanyRepository.findById(id).orElse(null);
            if (insCompany == null) {
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            insCompany.setAddress(inView.getAddress());
            insCompany.setEmail(inView.getEmail());
            insCompany.setName(inView.getName());
            insCompany.setPhone(inView.getPhone());
            this.insCompanyRepository.save(insCompany);
        } catch (GlobalBadRequest exc) {
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished updateOne service", logId));
        }
    }

    @Override
    public List<InsCompanyTableView> getAll(String name, String bulstat) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
            return this.insCompanyRepository.findAllByOptionalNameAndBulstatCustom(name, bulstat)
                    .stream()
                    .map(e -> this.modelMapper.map(e, InsCompanyTableView.class))
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }

    @Override
    public List<InsCompanyDetailsView> getOneById(Long id) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getOneById service", logId));
            InsCompany insCompany = this.insCompanyRepository.findById(id).orElse(null);
            InsCompanyDetailsView map = this.modelMapper.map(insCompany, InsCompanyDetailsView.class);
            List<InsCompanyDetailsView> result = new ArrayList<>();
            result.add(map);
            return result;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getOneById service", logId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOne service", logId));
            InsCompany insCompany = this.insCompanyRepository.findById(id).orElse(null);
            if (insCompany == null) {
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            List<Policy> policies = this.policyRepository.findAllByInsCompanyIdCustom(id);
            this.policyRepository.deleteAll(policies);

            List<InsProduct> products = this.productRepository.findAllByInsCompanyId(id);
            this.productRepository.deleteAll(products);

            this.insCompanyRepository.delete(insCompany);

            List<Client> clients = this.clientRepository.findAllByNoPliciesCustom();
            this.clientRepository.deleteAll(clients);

        } catch (GlobalBadRequest exc) {
            logger.error(String.format("%s: %s", logId, exc.getCustomMessage()), exc);
            throw exc;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteOne service", logId));
        }
    }
}
