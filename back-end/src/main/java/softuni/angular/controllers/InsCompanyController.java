package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.InsCompanyService;
import softuni.angular.views.insCompany.InsCompanyInView;

import javax.validation.Valid;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
@RestController
@RequestMapping("/InsCompany")
public class InsCompanyController {
    private final InsCompanyService insCompanyService;

    public InsCompanyController(InsCompanyService insCompanyService) {
        this.insCompanyService = insCompanyService;
    }

    @PostMapping("/")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> insertOne(@Valid @RequestBody InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.insCompanyService.insertOne(inView);
        return ResponseEntity.status(201).build();
    }
}
