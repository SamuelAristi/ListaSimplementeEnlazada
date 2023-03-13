package co.edu.umanizales.tdas.controller;

import co.edu.umanizales.tdas.controller.dto.ResponseDTO;
import co.edu.umanizales.tdas.model.Kid;
import co.edu.umanizales.tdas.model.ListSE;
import co.edu.umanizales.tdas.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private ListSE listSE;
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

    @GetMapping(path = "/totalage")
    public ResponseEntity<ResponseDTO> getTotalAges(){
        if (listSE.getTotalAges()==0){
            return  new ResponseEntity<>(new ResponseDTO(400,"no se encuentran niños registrados no es posible hacer el calculo",
                    null),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"el total de las edad de los niños es: "+ listSE.getTotalAges()
                ,null), HttpStatus.OK);
    }

    @GetMapping(path = "/avarage")
public ResponseEntity<ResponseDTO> getAvarage(@PathVariable List<Kid> kids){

        if(listSE.getTotalAges()==0){
            return  new ResponseEntity<>(new ResponseDTO(400,"no posible hacer el calculo no hay niños",null),
                    HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new ResponseDTO(200,listSE.getTotalAges() / (float) listSE.getKids().size(),null),
                    HttpStatus.OK);
        }
   }



}

