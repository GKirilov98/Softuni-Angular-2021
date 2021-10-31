package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.NInsTypeService;
import softuni.angular.views.nomenclature.NomenclatureOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/31/2021
 */
@RestController
@RequestMapping("/NInsType")
public class NInsTypeController {
    private final NInsTypeService nInsTypeService;

    public NInsTypeController(NInsTypeService nInsTypeService) {
        this.nInsTypeService = nInsTypeService;
    }

    /**
     *  Взимане на всички данни от базата
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws GlobalServiceException {
        List<NomenclatureOutView> results = this.nInsTypeService.getAll();
        return ResponseEntity.ok(results);
    }
}
