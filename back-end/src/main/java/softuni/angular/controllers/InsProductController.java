package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.InsProductService;
import softuni.angular.views.insCompany.InsCompanyInView;
import softuni.angular.views.insProduct.InsProductInView;

import javax.validation.Valid;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/30/2021
 */
@RestController
@RequestMapping("/InsProduct")
public class InsProductController {
    private final InsProductService insProductService;

    public InsProductController(InsProductService insProductService) {
        this.insProductService = insProductService;
    }

    /**
     *  Вкарва в базата запис
     * @param inView - body на заявката
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @PostMapping("/")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> insertOne(@Valid @RequestBody InsProductInView inView) throws GlobalServiceException, GlobalBadRequest {
//        this.insProductService.insertOne(inView);
        return ResponseEntity.status(201).build();
    }
}
