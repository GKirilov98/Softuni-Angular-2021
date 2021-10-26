package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.data.entities.InsCompany;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.InsCompanyRepository;
import softuni.angular.services.InsCompanyService;
import softuni.angular.views.insCompany.InsCompanyInView;

import java.util.Set;
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
            InsCompany map = this.modelMapper.map(inView, InsCompany.class);
            map.setIsActive(true);
            this.insCompanyRepository.save(map);
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished insertOne service", logId));
        }
    }
}
