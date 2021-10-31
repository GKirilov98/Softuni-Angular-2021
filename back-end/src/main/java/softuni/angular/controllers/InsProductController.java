package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.InsProductService;
import softuni.angular.views.insCompany.InsCompanyInView;
import softuni.angular.views.insProduct.InsProductCompanyTableView;
import softuni.angular.views.insProduct.InsProductDetailsView;
import softuni.angular.views.insProduct.InsProductInView;

import javax.validation.Valid;
import java.util.List;

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
     * Вкарва в базата запис
     *
     * @param inView - body на заявката
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest       -
     */
    @PostMapping("/")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> insertOne(@Valid @RequestBody InsProductInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.insProductService.insertOne(inView);
        return ResponseEntity.status(201).build();
    }

    /**
     *  Редактира в базата запис
     * @param id - id на обекта за редакция
     * @param inView - body на заявката
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateOne(
            @PathVariable("id") Long id,
            @Valid @RequestBody InsProductInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.insProductService.updateOne(id, inView);
        return ResponseEntity.status(201).build();
    }

    /**
     *
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @DeleteMapping("/{id}")
    //    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteOne(
            @PathVariable("id") Long id) throws GlobalServiceException, GlobalBadRequest {
        this.insProductService.deleteOne(id);
        return ResponseEntity.status(200).build();
    }

    /**
     * getAllByCompanyId
     *
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/getAllByCompanyId/{id}")
    public ResponseEntity<?> getAllByCompanyId(@PathVariable("id") Long id) throws GlobalServiceException {
        List<InsProductCompanyTableView> result = this.insProductService.getAllByCompanyId(id);
        return ResponseEntity.ok(result);
    }

    /**
     * getOneById
     * @param id -
     * @return -
     * @throws GlobalServiceException-
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) throws GlobalServiceException {
        List<InsProductDetailsView> result = this.insProductService.getOneById(id);
        return ResponseEntity.ok(result);
    }


}
