package co.edu.umanizales.tdas.controller;

import co.edu.umanizales.tdas.controller.dto.ResponseDTO;
import co.edu.umanizales.tdas.model.Owner;
import co.edu.umanizales.tdas.service.LocationService;
import co.edu.umanizales.tdas.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/owner")
public class OwnerController {
    @Autowired
    private OwnerService owmerService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    ResponseEntity<ResponseDTO> getOwners() {
        return new ResponseEntity<>(new ResponseDTO(200, owmerService.getOwners(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/addowner")
    ResponseEntity<ResponseDTO> addOwners(@RequestBody Owner owner) {
        owmerService.addOwner(owner);
        return new ResponseEntity<>(new ResponseDTO(200, "El dueño fue agregado", null), HttpStatus.OK);
    }


}

