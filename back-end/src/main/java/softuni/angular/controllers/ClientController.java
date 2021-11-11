package softuni.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.angular.exception.GlobalBadRequest;
import softuni.angular.exception.GlobalServiceException;
import softuni.angular.services.ClientService;
import softuni.angular.views.client.ClientCreateInView;
import softuni.angular.views.client.ClientDetailsOutView;
import softuni.angular.views.client.ClientTableOutView;

import javax.validation.Valid;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
@RestController
@RequestMapping("/Client")
public class ClientController {
    @Autowired
    private ClientService service;

    /**
     *  Връща всички записи от базата
     * @return -
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws GlobalServiceException {
        List<ClientTableOutView> list = service.getAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneById(@PathVariable("id") Long id) throws GlobalBadRequest, GlobalServiceException {
        service.deleteOneById(id);
        return ResponseEntity.ok().build();
    }


//    /**
//     * this method create record, and saves it in db
//     * @param inView -
//     * @return -
//     */
//    @PostMapping
//    public ResponseEntity<?> insertOne(
//            @Valid @RequestBody ClientCreateInView inView){
//        List<ClientDetailsOutView> list = service.insertOne(inView);
//        return ResponseEntity.ok().body(list);
//    }
}
