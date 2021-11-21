package softuni.angular.services;

import softuni.angular.exception.GlobalServiceException;
import softuni.angular.data.views.nomenclature.NomenclatureOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/8/2021
 */
public interface NInsObjectTypeService {
    List<NomenclatureOutView> getAll() throws GlobalServiceException;
}
