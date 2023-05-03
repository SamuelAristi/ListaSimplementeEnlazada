package co.edu.umanizales.tdas.controller;

import co.edu.umanizales.tdas.controller.dto.*;
import co.edu.umanizales.tdas.model.Location;
import co.edu.umanizales.tdas.model.Owner;
import co.edu.umanizales.tdas.model.Pet;
import co.edu.umanizales.tdas.service.ListDeService;
import co.edu.umanizales.tdas.service.LocationService;
import co.edu.umanizales.tdas.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class  ListDeController {
    @Autowired
    private ListDeService listDeService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private OwnerService ownerService;

    @PostMapping(path = "/addpet")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO) {
        // Verificar si la mascota ya existe
        boolean isPetRegistered = listDeService.getPets().checkPetById(petDTO.getId());
        if (isPetRegistered) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe una mascota con el mismo ID",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si el dueño ya existe
        Owner owner = ownerService.getOwnerById(petDTO.getCodeOwner());
        if (owner == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "El dueño no existe",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si la localidad ya existe
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La localidad no existe",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Si esta correcto
        listDeService.getPets().add(new Pet(
                petDTO.getName(),
                petDTO.getId(),
                petDTO.getRace(),
                petDTO.getAge(),
                petDTO.getColor(),
                petDTO.getGender(),
                owner,
                location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado la mascota",
                null), HttpStatus.OK);
    }


    @PostMapping(path = "/addtostar")
    public ResponseEntity<ResponseDTO>   addToStart (@RequestBody PetDTO petDTO) {
        // Verificar si la mascota ya existe
        boolean isPetRegistered = listDeService.getPets().checkPetById(petDTO.getId());
        if (isPetRegistered) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe una mascota con el mismo ID",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si el dueño ya existe
        Owner owner = ownerService.getOwnerById(petDTO.getCodeOwner());
        if (owner == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "El dueño no existe",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si la localidad ya existe
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La localidad no existe",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Si esta correcto
        listDeService.getPets().addToStart(new Pet(
                petDTO.getName(),
                petDTO.getId(),
                petDTO.getRace(),
                petDTO.getAge(),
                petDTO.getColor(),
                petDTO.getGender(),
                owner,
                location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado la mascota",
                null), HttpStatus.OK);
    }
    @PostMapping(path = "/addbyposition")
    public ResponseEntity<ResponseDTO> addByPosition(@RequestBody PetByPositionDTO petByPositionDTO){
        // Verificar si la mascota ya existe
        boolean isPetRegistered = listDeService.getPets().checkPetById(petByPositionDTO.getId());
        if (isPetRegistered) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe una mascota con el mismo ID",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si el dueño ya existe
        Owner owner = ownerService.getOwnerById(petByPositionDTO.getCodeOwner());
        if (owner == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "El dueño no existe",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Verificar si la localidad ya existe
        Location location = locationService.getLocationByCode(petByPositionDTO.getCodelocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La localidad no existe",
                    null), HttpStatus.BAD_REQUEST);
        }

        listDeService.getPets().addByPosition(new Pet(
                petByPositionDTO.getName(),
                petByPositionDTO.getId(),
                petByPositionDTO.getRace(),
                petByPositionDTO.getAge(),
                petByPositionDTO.getColor(),
                petByPositionDTO.getGender(),
                owner,
                location),petByPositionDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado la mascota",
                null), HttpStatus.OK);
    }



    @DeleteMapping(path = "/deletepets/{id}")
    public ResponseEntity<ResponseDTO> deletePetById(@PathVariable String id){
        listDeService.getPets().deletePetById(id);
        return new ResponseEntity<>(new ResponseDTO(200,"La mascota con ID"+ id+"ha sido eliminado",
                null),HttpStatus.OK);
    }
    @DeleteMapping(path = "/deletepetsbyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable byte age){
        listDeService.getPets().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200,"Las mascotas con edad:"+ age +"han sido eliminadas",
                null),HttpStatus.OK);
    }

    @GetMapping(path = "/getavarage")
    public ResponseEntity<ResponseDTO> getAverageAge(){
        double averageAge= listDeService.getPets().getAverageAge();
        return new ResponseEntity<>(new ResponseDTO(200,"el promedio de edades es:"+ averageAge,
                null),HttpStatus.OK);
    }
     @GetMapping(path = "/changeExtremes")
    public ResponseEntity<ResponseDTO> changeExtremes(){
        listDeService.getPets().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha invertido la lista",null),HttpStatus.OK);
     }

     @GetMapping(path = "/backposition/{id}/{position}")
    public ResponseEntity<ResponseDTO> backPosition(@PathVariable String id,@PathVariable int position){
        listDeService.getPets().backPosition(id,position,listDeService.getPets());
         return new ResponseEntity<>(new ResponseDTO(200, "Se ha movido la macota a una posicion anterior",
                 null), HttpStatus.OK);
     }

    @GetMapping(path = "/gainPosition/{id}/{position}")
    public ResponseEntity<ResponseDTO> gainPosition(@PathVariable String id,@PathVariable int position){
        listDeService.getPets().gainPosition(id,position,listDeService.getPets());
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha movido la macota a una posicion anterior",
                null), HttpStatus.OK);
    }

     @GetMapping(path = "/orderBoysToStart")
     public ResponseEntity<ResponseDTO> orderBoysToStart(){
         listDeService.getPets().orderBoysToStart();
         return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado las mascotas de sexo masculino al inicio",
                 null),HttpStatus.OK);
     }
     @GetMapping(path = "/intercalateByGender")
    public ResponseEntity<ResponseDTO> intercalateByGender(){
        listDeService.getPets().intercalateByGender();
         return new ResponseEntity<>(new ResponseDTO(200,"Se han intercalado los generos",
                 null),HttpStatus.OK);
     }

     @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO>  getPetsByLocation(){
        List<PetByLocationDTO> petByLocationDTOSList = new ArrayList<>();
        for(Location loc : locationService.getLocations()){
            int count = listDeService.getPets().getCountPetsByLocation(loc.getCode());
            if(count > 0){
                petByLocationDTOSList.add(new PetByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,petByLocationDTOSList,null),HttpStatus.OK);
     }
    @GetMapping(path = "/petsbydeptos")
    public ResponseEntity<ResponseDTO>  getPetsBydeptos(){
        List<PetByLocationDTO> petByLocationDTOSList1 = new ArrayList<>();
        for(Location loc : locationService.getLocations()){
            int count = listDeService.getPets().getCountPetByDeptoCode(loc.getCode());
            if(count > 0){
                petByLocationDTOSList1.add(new PetByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,petByLocationDTOSList1,null),HttpStatus.OK);
    }

    @GetMapping(path = "/reportspets/{age}")
    public ResponseEntity<ResponseDTO> getReportsPetsLocationGneders(@PathVariable byte age){
        ReportPetsLocationDTO report = new ReportPetsLocationDTO(locationService.getLocationsByCodeSize(8));
        listDeService.getPets().getReportPetsByLocationByGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200,report,null),HttpStatus.OK);
    }
    @GetMapping(path = "/reportagepets/{age}")
    public ResponseEntity<ResponseDTO> getReportAgePets(@PathVariable byte age){
        ReportAgeQuantityPetsDTO report = new ReportAgeQuantityPetsDTO(listDeService.getPets().getPets());
        listDeService.getPets().getReportPetsByAgeByGender(age, report);
        return new ResponseEntity<>(new ResponseDTO(200,report,null),HttpStatus.OK);

    }

}


