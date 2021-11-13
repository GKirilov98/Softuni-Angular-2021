package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.*;
import softuni.angular.services.InsProductService;
import softuni.angular.views.insProduct.InsProductCompanyTableView;
import softuni.angular.views.insProduct.InsProductDetailsView;
import softuni.angular.views.insProduct.InsProductInView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/30/2021
 */
@Service
public class InsProductServiceImpl implements InsProductService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final InsProductRepository insProductRepository;
    private final ModelMapper modelMapper;
    private final InsCompanyRepository insCompanyRepository;
    private final NInsTypeRepository nInsTypeRepository;
    private final PolicyRepository policyRepository;
    private final ClientRepository clientRepository;

    public InsProductServiceImpl(InsProductRepository insProductRepository,
                                 ModelMapper modelMapper,
                                 InsCompanyRepository insCompanyRepository,
                                 NInsTypeRepository nInsTypeRepository, PolicyRepository policyRepository, ClientRepository clientRepository) {
        this.insProductRepository = insProductRepository;
        this.modelMapper = modelMapper;
        this.insCompanyRepository = insCompanyRepository;
        this.nInsTypeRepository = nInsTypeRepository;
        this.policyRepository = policyRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void insertOne(InsProductInView inView) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            InsCompany company = this.insCompanyRepository
                    .findById(inView.getInsCompanyId())
                    .orElseThrow(() -> new GlobalBadRequest("Невалидна компания",
                            new Throwable("Invalid company!")));
            NInsType insType = this.nInsTypeRepository.findById(inView.getInsTypeId())
                    .orElseThrow(() -> new GlobalBadRequest("Невалиден тип на продукт",
                            new Throwable("Invalid insurance type!")));
            InsProduct entity = this.modelMapper.map(inView, InsProduct.class);
            entity.setInsCompany(company);
            entity.setInsType(insType);
            this.insProductRepository.save(entity);
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
    public List<InsProductCompanyTableView> getAllByCompanyId(Long companyId) throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAllByCompanyId service", logId));
            return this.insProductRepository.findAllByInsCompanyId(companyId)
                    .stream()
                    .map(e -> {
                        InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                        map.setInsTypeDescription(e.getInsType().getDescription());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAllByCompanyId service", logId));
        }
    }

    @Override
    public List<InsProductDetailsView> getOneById(Long id) throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getOneById service", logId));
            InsProduct insProduct = this.insProductRepository.findById(id).orElse(null);
            if (insProduct == null){
                return null;
            }

            InsProductDetailsView map  = this.modelMapper.map(insProduct, InsProductDetailsView.class);
            map.setInsTypeId(insProduct.getInsType().getId());
            map.setInsTypeDescription(insProduct.getInsType().getDescription());
            map.setInsCompanyId(insProduct.getInsCompany().getId());
            map.setInsCompanyName(insProduct.getInsCompany().getName());
            List<InsProductDetailsView> result = new ArrayList<>();
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
    public void updateOne(Long id, InsProductInView inView) throws GlobalBadRequest, GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start updateOne service", logId));
            InsProduct insProduct = this.insProductRepository.findById(id).orElse(null);
            if (insProduct == null){
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }
            int size = this.policyRepository.findAllByInsProductIdCustom(insProduct.getId()).size();
            if (size != 0){
                throw new GlobalBadRequest("Има активни полици свързани с този продукт!",
                        new Throwable("Could not be edit!"));
            }

            InsCompany company = this.insCompanyRepository.findById(inView.getInsCompanyId())
                    .orElseThrow(() -> new GlobalBadRequest("Подаденото id на компания е невалидно!",
                            new Throwable("Invalid company id!")));

            NInsType insType = this.nInsTypeRepository.findById(inView.getInsTypeId())
                    .orElseThrow(() -> new GlobalBadRequest("Подаденото id на тип е невалидно!",
                            new Throwable("Invalid type id!")));
            this.modelMapper.map(inView, insProduct);
            insProduct.setInsCompany(company);
            insProduct.setInsType(insType);
            this.insProductRepository.save(insProduct);
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
    public void deleteOne(Long id) throws GlobalBadRequest, GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start updateOne service", logId));
            InsProduct insProduct = this.insProductRepository.findById(id).orElse(null);
            if (insProduct == null){
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            List<Policy> policies = this.policyRepository.findAllByInsProductIdCustom(id);
            this.policyRepository.deleteAll(policies);

            this.insProductRepository.delete(insProduct);

            List<Client> clients = this.clientRepository.findAllByNoPliciesCustom();
            this.clientRepository.deleteAll(clients);

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
    public List<InsProductCompanyTableView> getAllByTypeId(Long typeId) throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAllByTypeId service", logId));
            return this.insProductRepository.findAllByInsTypeId(typeId)
                    .stream()
                    .map(e -> {
                        InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                        map.setName(e.getName() + " / " + e.getInsCompany().getName());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAllByTypeId service", logId));
        }
    }

    @Override
    public List<InsProductCompanyTableView> getAll() throws GlobalServiceException {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAllByCompanyId service", logId));
            return this.insProductRepository.findAll()
                    .stream()
                    .map(e -> {
                        InsProductCompanyTableView map = this.modelMapper.map(e, InsProductCompanyTableView.class);
                        map.setInsTypeDescription(e.getInsType().getDescription());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAllByCompanyId service", logId));
        }
    }

}
