package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.PolicyService;
import softuni.angular.views.policy.PolicyCalculationOutView;
import softuni.angular.views.policy.PolicyDetailsView;
import softuni.angular.views.policy.PolicyInsertInView;
import softuni.angular.views.policy.PolicyTableOutView;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/8/2021
 */
@RestController
@RequestMapping("/Policy")
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
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
    public ResponseEntity<?> insertOne(@Valid @RequestBody PolicyInsertInView inView) throws GlobalServiceException, GlobalBadRequest {
        this.policyService.insertOne(inView);
        return ResponseEntity.status(201).build();
    }

    /**
     *  Връща предварителните калкулации за полица
     * @param productId -
     * @param sum -
     * @return -
     */
    @GetMapping("/getCalculations")
    public ResponseEntity<?> getCalculations(
         @RequestParam("productId")    Long productId,
         @RequestParam("sum")    BigDecimal sum) throws GlobalServiceException {
        List<PolicyCalculationOutView> result = this.policyService.getCalculations(productId, sum);
        return ResponseEntity.ok(result);
    }

    /**
     * Връща всички записи с филтрация по желание
     * @param clientId -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "clientId", required = false) Long clientId
    ) throws GlobalServiceException {
        List<PolicyTableOutView> result =  this.policyService.getAll(clientId);
        return ResponseEntity.ok(result);
    }

    /**
     * getOneById Връща  запис по id
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable(value = "id") Long id) throws GlobalServiceException {
        List<PolicyDetailsView> result =  this.policyService.getOneById(id);
        return ResponseEntity.ok(result);
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
        this.policyService.deleteOne(id);
        return ResponseEntity.status(201).build();
    }

    /**
     * getAllByProductId
     *
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/getAllByProductId/{id}")
    public ResponseEntity<?> getAllByProductId(@PathVariable("id") Long id) throws GlobalServiceException {
        List<PolicyTableOutView> result = this.policyService.getAllByProductId(id);
        return ResponseEntity.ok(result);
    }

    /**
     * getAllByClientId
     *
     * @param id -
     * @return -
     * @throws GlobalServiceException -
     */
    @GetMapping("/getAllByClientId/{id}")
    public ResponseEntity<?> getAllByClientId(@PathVariable("id") Long id) throws GlobalServiceException {
        List<PolicyTableOutView> result = this.policyService.getAllByClientId(id);
        return ResponseEntity.ok(result);
    }
}
