package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.*;
import softuni.angular.services.PolicyService;
import softuni.angular.data.views.policy.PolicyCalculationOutView;
import softuni.angular.data.views.policy.PolicyDetailsView;
import softuni.angular.data.views.policy.PolicyInsertInView;
import softuni.angular.data.views.policy.PolicyTableOutView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Service
public class PolicyServiceImpl implements PolicyService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static final String N_CLIENT_TYPE_LEGAL = "LEGAL";
    public static final String N_CLIENT_TYPE_PERSON = "PERSON";
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;
    private final PolicyRepository policyRepository;
    private final InsProductRepository productRepository;
    private final NInsObjectTypeRepository nInsObjectTypeRepository;
    private final NClientTypeRepository nClientTypeRepository;

    public PolicyServiceImpl(ModelMapper modelMapper, ClientRepository clientRepository,
                             PolicyRepository policyRepository, InsProductRepository productRepository,
                             NInsObjectTypeRepository nInsObjectTypeRepository,
                             NClientTypeRepository nClientTypeRepository) {
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
        this.policyRepository = policyRepository;
        this.productRepository = productRepository;
        this.nInsObjectTypeRepository = nInsObjectTypeRepository;
        this.nClientTypeRepository = nClientTypeRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(PolicyInsertInView inView) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            this.validateClient(inView);
            if (inView.getBeginDate().isAfter(inView.getEndDate())) {
                throw new GlobalBadRequest("Датите са невалидни!",
                        new Throwable("Invalid dates!"));
            }

            boolean isExist = this.policyRepository.existsByIdentityNumber(inView.getIdentityNumber());
            if (isExist) {
                throw new GlobalBadRequest("Повтарящ се идентификационен номер!!",
                        new Throwable("Invalid identity number!"));
            }

            Client client;
            if (inView.getIsNew()) {
                Client entity = this.modelMapper.map(inView, Client.class);
                NClientType nClientType;
                if (inView.getIsLegal()) {
                    nClientType = this.nClientTypeRepository.findByCode(N_CLIENT_TYPE_LEGAL);
                    entity.setEgnBulstat(inView.getBulstat());
                    entity.setFullName(inView.getName());
                } else {
                    nClientType = this.nClientTypeRepository.findByCode(N_CLIENT_TYPE_PERSON);
                    entity.setEgnBulstat(inView.getEgn());
                    entity.setFullName(
                            String.format("%s %s %s", inView.getName(), inView.getMiddleName(), inView.getLastName())
                    );
                }

                entity.setClientType(nClientType);
                client = this.clientRepository.save(entity);
            } else {
                if (inView.getIsLegal()) {
                    client = this.clientRepository.findByEgnBulstat(inView.getBulstat());
                } else {
                    client = this.clientRepository.findByEgnBulstat(inView.getEgn());
                }
            }

            Policy entity = this.modelMapper.map(inView, Policy.class);
            entity.setNote(inView.getPolicyNote());
            entity.setCreationDate(DateTime.now());
            InsProduct insProduct = this.productRepository.findById(inView.getInsProductId())
                    .orElseThrow(() -> new GlobalBadRequest("Невалиден продукт!",
                            new Throwable("Invalid product!")));
            entity.setInsProduct(insProduct);
            NInsObjectType objectType = this.nInsObjectTypeRepository.findById(inView.getInsObjectTypeId())
                    .orElseThrow(() -> new GlobalBadRequest("Невалиден тип на обекта!",
                            new Throwable("Invalid object type!")));
            entity.setInsObjectType(objectType);
            entity.setClient(client);
            PolicyCalculationOutView calculation = this.getPolicyCalculations(insProduct, inView.getSum());
            entity.setPremium(calculation.getPremium());
            entity.setTax(calculation.getTax());
            entity.setCommission(calculation.getCommission());

            this.policyRepository.save(entity);
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
    public List<PolicyCalculationOutView> getCalculations(Long productId, BigDecimal sum) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getCalculations service", logId));
            List<PolicyCalculationOutView> result = new ArrayList<>();
            InsProduct product = this.productRepository.findById(productId).orElse(null);
            if (product != null) {
                PolicyCalculationOutView calculations = this.getPolicyCalculations(product, sum);
                result.add(calculations);
            }

            return result;
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getCalculations service", logId));
        }
    }

    @Override
    public List<PolicyTableOutView> getAll(Long clientId) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
           return this.policyRepository.findAllByOptionalClientIdCustom(clientId)
                    .stream()
                    .map(e -> {
                        PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                        map.setProductName(e.getInsProduct().getName() );
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
    public List<PolicyDetailsView> getOneById(Long id) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getOneById service", logId));
            List<PolicyDetailsView> result = new ArrayList<>();
            Policy policy = this.policyRepository.findById(id).orElse(null);
            if (policy == null){
                return result;
            }

            PolicyDetailsView map = this.modelMapper.map(policy, PolicyDetailsView.class);
            map.setPolicyNote(policy.getNote());

            this.modelMapper.map(policy.getClient(), map);
            map.setId(policy.getId());
            map.setClientNote(policy.getClient().getNote());

            map.setClientTypeCode(policy.getClient().getClientType().getCode());
            map.setClientTypeDescription(policy.getClient().getClientType().getDescription());
            map.setProductName(policy.getInsProduct().getName() + " / " + policy.getInsProduct().getInsCompany().getName());
            map.setObjectTypeDescription(policy.getInsObjectType().getDescription());

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
    public void deleteOne(Long id) throws GlobalBadRequest, GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOne service", logId));
            Policy policy = this.policyRepository.findById(id).orElse(null);
            if (policy == null){
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            this.policyRepository.delete(policy);
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

    @Override
    public List<PolicyTableOutView> getAllByProductId(Long id) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOne service", logId));
         return   this.policyRepository.findAllByInsProductIdCustom(id)
                   .stream()
                   .map(e -> {
                       PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                       map.setProductName(e.getInsProduct().getName() );
                       return map;
                   })
                   .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteOne service", logId));
        }

    }

    @Override
    public List<PolicyTableOutView> getAllByClientId(Long id) throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start deleteOne service", logId));
            return   this.policyRepository.findAllByOptionalClientIdCustom(id)
                    .stream()
                    .map(e -> {
                        PolicyTableOutView map = this.modelMapper.map(e, PolicyTableOutView.class);
                        map.setProductName(e.getInsProduct().getName() );
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished deleteOne service", logId));
        }
    }

    private PolicyCalculationOutView getPolicyCalculations(InsProduct insProduct, BigDecimal sum) {
        PolicyCalculationOutView view = new PolicyCalculationOutView();
        view.setPremium(
                sum.multiply(insProduct.getPremiumPercent().divide(BigDecimal.valueOf(100)))
        );

        view.setTax(view.getPremium().multiply(BigDecimal.valueOf(0.02)));
        view.setCommission(
                sum.multiply(insProduct.getComissionPercent().divide(BigDecimal.valueOf(100)))
        );

        return view;
    }

    private void validateClient(PolicyInsertInView inView) throws GlobalBadRequest {

        if (!inView.getIsLegal()) {
            boolean isExist = this.clientRepository.existsByEgnBulstat(inView.getEgn());
            //Частно лице
            if (inView.getIsNew()) {
                //нов клиент
                if (inView.getName() == null || inView.getName().trim().length() == 0) {
                    throw new GlobalBadRequest("Името е невалидно/задължително поле!",
                            new Throwable("Invalid Name!"));
                }

                if (inView.getMiddleName() == null || inView.getMiddleName().trim().length() == 0) {
                    throw new GlobalBadRequest("Бащино име е невалидно/задължително поле!",
                            new Throwable("Invalid Middle name!"));
                }

                if (inView.getLastName() == null || inView.getLastName().trim().length() == 0) {
                    throw new GlobalBadRequest("Фамилия е невалидно/задължително поле!",
                            new Throwable("Invalid Last name!"));
                }

                if (isExist) {
                    throw new GlobalBadRequest("Има съществуващо лице с тези данни!",
                            new Throwable("Invalid EGN!"));
                }

                if (inView.getEgn() == null || inView.getEgn().trim().length() == 0) {
                    throw new GlobalBadRequest("ЕГН е невалидно/задължително поле!",
                            new Throwable("Invalid EGN!"));
                }

                if (inView.getAddress() == null || inView.getAddress().trim().length() == 0) {
                    throw new GlobalBadRequest("Адрес е задължително поле!",
                            new Throwable("Invalid Address!"));
                }

                if (inView.getPhoneNumber() == null ||
                        inView.getPhoneNumber().trim().length() == 0 ||
                        !inView.getPhoneNumber().matches("[0-9]+")
                ) {
                    throw new GlobalBadRequest("Телефонния номер е невалиден/задължително поле!",
                            new Throwable("Invalid Last name!"));
                }

            } else {
                //стар клиент
                if (!isExist) {
                    throw new GlobalBadRequest("Няма намерено лице в базата данни!",
                            new Throwable("Invalid EGN!"));
                }
            }
        } else {
            //Юридическо лице
            boolean isExist = this.clientRepository.existsByEgnBulstat(inView.getBulstat());
            if (inView.getIsNew()) {
                //нов клиент
                if (inView.getName() == null || inView.getName().trim().length() == 0) {
                    throw new GlobalBadRequest("Името е невалидно/задължително поле!",
                            new Throwable("Invalid Name!"));
                }

                if (isExist) {
                    throw new GlobalBadRequest("Има съществуващо лице с тези данни!",
                            new Throwable("Invalid Bulstat!"));
                }

                if (inView.getBulstat() == null || inView.getBulstat().trim().length() == 0) {
                    throw new GlobalBadRequest("Булстат е невалидно/задължително поле!",
                            new Throwable("Invalid Bulstat!"));
                }

                if (inView.getAddress() == null || inView.getAddress().trim().length() == 0) {
                    throw new GlobalBadRequest("Адрес е задължително поле!",
                            new Throwable("Invalid Address!"));
                }

                if (inView.getPhoneNumber() == null ||
                        inView.getPhoneNumber().trim().length() == 0 ||
                        !inView.getPhoneNumber().matches("[0-9]+")
                ) {
                    throw new GlobalBadRequest("Телефонния номер е невалиден/задължително поле!",
                            new Throwable("Invalid Last name!"));
                }

            } else {
                //стар клиент
                if (!isExist) {
                    throw new GlobalBadRequest("Няма намерено лице в базата данни!",
                            new Throwable("Invalid Bulstat!"));
                }
            }
        }
    }
}
