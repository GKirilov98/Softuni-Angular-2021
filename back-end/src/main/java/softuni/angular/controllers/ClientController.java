package softuni.angular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.ClientService;
import softuni.angular.data.views.client.ClientDetailsOutView;
import softuni.angular.data.views.client.ClientTableOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
@RestController
@RequestMapping("/Client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    /**
     * Връща всички записи от базата
     *
     * @return -
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws GlobalServiceException {
        List<ClientTableOutView> list = service.getAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Връща 1 запис от базата
     *
     * @return -
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) throws GlobalServiceException {
        List<ClientDetailsOutView> list = service.getOneById(id);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteOneById(@PathVariable("id") Long id) throws GlobalBadRequest, GlobalServiceException {
        service.deleteOneById(id);
        return ResponseEntity.ok().build();
    }
}
