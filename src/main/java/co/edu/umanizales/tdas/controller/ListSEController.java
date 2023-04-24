package co.edu.umanizales.tdas.controller;

import co.edu.umanizales.tdas.controller.dto.*;
import co.edu.umanizales.tdas.model.Kid;
import co.edu.umanizales.tdas.model.Location;
import co.edu.umanizales.tdas.service.ListSEService;
import co.edu.umanizales.tdas.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class  ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    ResponseEntity<ResponseDTO> getKids(){
    return new ResponseEntity<>(new ResponseDTO(200,listSEService.getKids().getHead(),null),HttpStatus.OK);
    }

    @DeleteMapping(path = "/DeleteKid")
    public ResponseEntity<ResponseDTO> deleteKidByIdentification(@PathVariable String identification) {
        Kid kidToRemove = listSEService.getKids().getKidByIdentification(identification);
        if (kidToRemove != null) {
            listSEService.getKids().deleteByIdentification(identification);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "El niño ha sido eliminado exitosamente", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No se encontró ningún niño con la identificación proporcionada", null), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/averageage")
    public ResponseEntity<ResponseDTO>  getAverageAge() {
        double averageAge = listSEService.getKids().getAverageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200,"El promedio de edad de los niños es " + averageAge,
                null), HttpStatus.OK);
    }


    @GetMapping(path= "/changeExtremes")
    public ResponseEntity<ResponseDTO> changeExtremes(){
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"se han intercambiado los extremos",null),HttpStatus.OK);
    }
     @GetMapping(path="/invertir")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200,"se ha invertido la lista",null),HttpStatus.OK);
    }


    @PostMapping(path = "/niño")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        // Verificar si ya existe un niño con la misma identificación
        Kid existingKid = listSEService.getKids().getKidByIdentification(kidDTO.getIdentification());
        if (existingKid != null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe un niño con la misma identificación",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Si no existe, agregar el niño
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().add(new Kid(
                kidDTO.getIdentification(),
                kidDTO.getName(),
                kidDTO.getAge(),
                kidDTO.getGender(),
                location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el niño",
                null), HttpStatus.OK);
    }

        @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getkidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc : locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocation(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList,null),HttpStatus.OK);
    }
    @GetMapping(path = "/kidsbydepto")
    public ResponseEntity<ResponseDTO> getKidsByDeptoCode(){
        List<KidsByLocationDTO> kidsByLocationDTOList1= new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByDeptoCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList1,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "kidsbyage")
    public ResponseEntity<ResponseDTO> kidsbyage(@PathVariable byte age){
        List<KidsByGendetDTO> kidsByGenderDTOlist = new ArrayList<>();
        for(Location loc:locationService.getLocations()){
            if(loc.getCode().length()==8){
                String nameCity = loc.getName();
                List<GenderDTO> genderDTOList=new ArrayList<>();

                genderDTOList.add(new GenderDTO('m',listSEService.getKids().getCountKidsByCityByAgeBygender(loc.getCode(),
                        'm',age)));
                genderDTOList.add(new GenderDTO('f',listSEService.getKids().getCountKidsByCityByAgeBygender(loc.getCode(),
                        'f',age)));
                int  total= genderDTOList.get(0).getQuantity() + genderDTOList.get(1).getQuantity();

                kidsByGenderDTOlist.add(new KidsByGendetDTO(nameCity,genderDTOList,total));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByGenderDTOlist,null),HttpStatus.OK);
    }

}

