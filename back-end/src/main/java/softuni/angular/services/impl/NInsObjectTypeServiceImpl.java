package softuni.angular.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.repositories.NInsObjectTypeRepository;
import softuni.angular.services.NInsObjectTypeService;
import softuni.angular.views.nomenclature.NomenclatureOutView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/8/2021
 */
@Service
public class NInsObjectTypeServiceImpl implements NInsObjectTypeService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final NInsObjectTypeRepository nInsObjectTypeRepository;

    public NInsObjectTypeServiceImpl(ModelMapper modelMapper,
                                     NInsObjectTypeRepository nInsObjectTypeRepository) {
        this.modelMapper = modelMapper;
        this.nInsObjectTypeRepository = nInsObjectTypeRepository;
    }

    @Override
    public List<NomenclatureOutView> getAll() throws GlobalServiceException {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        try {
            logger.info(String.format("%s: Start getAll service", logId));
            return this.nInsObjectTypeRepository.findAll()
                    .stream()
                    .map(e -> this.modelMapper.map(e, NomenclatureOutView.class))
                    .collect(Collectors.toList());
        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()));
            throw new GlobalServiceException("Грешка при работа с базата данни!", exc);
        } finally {
            logger.info(String.format("%s: Finished getAll service", logId));
        }
    }
}
