package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.InsCompanyService;
import softuni.angular.views.insCompany.InsCompanyDetailsView;
import softuni.angular.views.insCompany.InsCompanyInView;
import softuni.angular.views.insCompany.InsCompanyTableView;

import javax.validation.Valid;
import java.util.List;

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

    /**
     *  Вкарва в базата запис
     * @param inView - body на заявката
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> insertOne(@Valid @RequestBody InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.insCompanyService.insertOne(inView);
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateOne(
            @PathVariable("id") Long id,
            @Valid @RequestBody InsCompanyInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.insCompanyService.updateOne(id, inView);
        return ResponseEntity.status(201).build();
    }

    /**
     *  Изтриване в базата запис
     * @param id - id на обекта за изтриване
     * @return -
     * @throws GlobalServiceException -
     * @throws GlobalBadRequest -
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long id) throws GlobalServiceException, GlobalBadRequest {
        this.insCompanyService.deleteOne(id);
        return ResponseEntity.status(201).build();
    }

    /**
     * Връща всички записи с филтрация по желание
     * @param name -
     * @param bulstat -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "bulstat", required = false) String bulstat
    ) throws GlobalServiceException {
        List<InsCompanyTableView> result =  this.insCompanyService.getAll(name, bulstat);
        return ResponseEntity.ok(result);
    }

    /**
     * Връща запис по id
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(
            @PathVariable(value = "id") Long id
    ) throws GlobalServiceException {
        List<InsCompanyDetailsView> result =  this.insCompanyService.getOneById(id);
        return ResponseEntity.ok(result);
    }
}
