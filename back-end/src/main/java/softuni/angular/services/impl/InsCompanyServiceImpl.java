package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.InsCompany;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.services.InsCompanyService;
import softuni.angular.views.insCompany.InsCompanyDetailsView;
import softuni.angular.views.insCompany.InsCompanyInView;
import softuni.angular.views.insCompany.InsCompanyTableView;

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
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final InsCompanyRepository insCompanyRepository;

    public InsCompanyServiceImpl(ModelMapper modelMapper, InsCompanyRepository insCompanyRepository) {
        this.modelMapper = modelMapper;
        this.insCompanyRepository = insCompanyRepository;
    }

    @Override
    public void insertOne(InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start insertOne service", logId));
            if (this.insCompanyRepository.existsByBulstatAndIsActive(inView.getBulstat(), true)) {
                throw new GlobalBadRequest("Вече има съществуваща фирма с този Булстат!",
                        new Throwable("Already exist company with this bulstat!"));
            }

            InsCompany map = this.modelMapper.map(inView, InsCompany.class);
            map.setIsActive(true);
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
            if (this.insCompanyRepository.existsByBulstatAndIsActive(inView.getBulstat(), true)) {
                throw new GlobalBadRequest("Вече има съществуваща фирма с този Булстат!",
                        new Throwable("Already exist company with this bulstat!"));
            }
            InsCompany insCompany = this.insCompanyRepository.findById(id).orElse(null);
            if (insCompany == null){
                throw new GlobalBadRequest("Подаденото id е невалидно!",
                        new Throwable("Invalid id!"));
            }

            if (!insCompany.getIsActive()){
                throw new GlobalBadRequest("Обекта вече е изтрит и не може да се редактира!",
                        new Throwable("The object is already deleted!!"));
            }
            this.modelMapper.map(inView, insCompany);

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
            return this.insCompanyRepository.findAllByIsActiveAndOptionalNameAndBulstatCustom(name, bulstat)
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
            InsCompany insCompany = this.insCompanyRepository.findByIdAndIsActive(id, true).orElse(null);
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


}
