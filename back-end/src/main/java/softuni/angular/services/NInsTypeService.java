package softuni.angular.services;

import softuni.angular.exception.GlobalServiceException;
import softuni.angular.data.views.nomenclature.NomenclatureOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface NInsTypeService {
    List<NomenclatureOutView> getAll() throws GlobalServiceException;
}
