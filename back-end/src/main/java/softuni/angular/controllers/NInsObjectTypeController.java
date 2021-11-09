package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.NInsObjectTypeService;
import softuni.angular.views.nomenclature.NomenclatureOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
@RestController
@RequestMapping("/NInsObjectType")
public class NInsObjectTypeController {
    private final NInsObjectTypeService nInsObjectTypeService;

    public NInsObjectTypeController(NInsObjectTypeService nInsObjectTypeService) {
        this.nInsObjectTypeService = nInsObjectTypeService;
    }

    /**
     *  Взимане на всички данни от базата
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws GlobalServiceException {
        List<NomenclatureOutView> results = this.nInsObjectTypeService.getAll();
        return ResponseEntity.ok(results);
    }
}
