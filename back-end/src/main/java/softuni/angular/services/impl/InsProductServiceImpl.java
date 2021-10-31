package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.InsCompany;
import softuni.angular.data.entities.InsProduct;
import softuni.angular.data.entities.NInsType;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.repositories.InsProductRepository;
import softuni.angular.repositories.NInsTypeRepository;
import softuni.angular.services.InsProductService;
import softuni.angular.views.insProduct.InsProductCompanyTableView;
import softuni.angular.views.insProduct.InsProductInView;

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

    public InsProductServiceImpl(InsProductRepository insProductRepository,
                                 ModelMapper modelMapper,
                                 InsCompanyRepository insCompanyRepository,
                                 NInsTypeRepository nInsTypeRepository) {
        this.insProductRepository = insProductRepository;
        this.modelMapper = modelMapper;
        this.insCompanyRepository = insCompanyRepository;
        this.nInsTypeRepository = nInsTypeRepository;
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
}
