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
    ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getKids().getHead(), null), HttpStatus.OK);
    }


    @GetMapping(path = "/averageage")
    public ResponseEntity<ResponseDTO> getAverageAge() {
        double averageAge = listSEService.getKids().getAverageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200, "El promedio de edad de los niños es " + averageAge,
                null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletekids/{identification}")
    public ResponseEntity<ResponseDTO> deleteKidByIdentification(@PathVariable String identification) {
        listSEService.getKids().deleteByIdentification(identification);
        return new ResponseEntity<>(new ResponseDTO(200, "El niño con identificación " + identification + " ha sido eliminado", null), HttpStatus.OK);
    }


    @DeleteMapping(path = "/deletekidsage/{age}")
    public ResponseEntity<ResponseDTO> deleteKidsByAge(@PathVariable int age) {
        listSEService.getKids().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado los niños con edad " + age, null), HttpStatus.OK);
    }


    @GetMapping(path = "/changeExtremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "se han intercambiado los extremos", null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200, "SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/orderboystostar")
    public ResponseEntity<ResponseDTO> orderBoyToStart() {
        listSEService.getKids().orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han agregado los niños al inicio", null), HttpStatus.OK);
    }

    @GetMapping(path = "/intercalategenders")
    public ResponseEntity<ResponseDTO> intercalateGenders() {
        listSEService.getKids().intercalateByGender();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han intercalado los géneros de los niños", null), HttpStatus.OK);
    }


    @PostMapping(path = "/addbyposition")
    public ResponseEntity<ResponseDTO> addByPosition(@RequestBody KidByPositionDTO kidByPositionDTO) {
        // Verificar si ya existe un niño con la misma identificación
        boolean isKidRegistered = listSEService.getKids().checkKidByIdentification(kidByPositionDTO.getIdentification());
        if (isKidRegistered) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe un niño con la misma identificación",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Si no existe, agregar el niño
        Location location = locationService.getLocationByCode(kidByPositionDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().addByPosition(new Kid(kidByPositionDTO.getIdentification(),
                        kidByPositionDTO.getName(), kidByPositionDTO.getAge(), kidByPositionDTO.getGender(), location),
                kidByPositionDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el niño",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtostar")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody KidByPositionDTO kidByPositionDTO) {
        // Verificar si ya existe un niño con la misma identificación
        boolean isKidRegistered = listSEService.getKids().checkKidByIdentification(kidByPositionDTO.getIdentification());
        if (isKidRegistered) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya existe un niño con la misma identificación",
                    null), HttpStatus.BAD_REQUEST);
        }
        // Si no existe, agregar el niño
        Location location = locationService.getLocationByCode(kidByPositionDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().addToStart(new Kid(kidByPositionDTO.getIdentification(),
                        kidByPositionDTO.getName(), kidByPositionDTO.getAge(), kidByPositionDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el niño",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/gainposition/{id}/{position}")
    public ResponseEntity<ResponseDTO> gainPosition(@PathVariable String id, @PathVariable int position) {
        listSEService.getKids().gainPosition(id, position, listSEService.getKids());
        return new ResponseEntity<>(new ResponseDTO(200, "El niño con identificación " + id + " se ha movido a la posición " + position, null), HttpStatus.OK);
    }
    @GetMapping(path = "/backposition/{id}/{position}")
    public ResponseEntity<ResponseDTO> backPosition(@PathVariable String id, @PathVariable int position) {
        listSEService.getKids().backPosition(id, position, listSEService.getKids());
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha movido el niño a una posición anterior", null), HttpStatus.OK);
    }


    @PostMapping(path = "/addkid")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        // Verificar si ya existe un niño con la misma identificación
        boolean isKidRegistered = listSEService.getKids().checkKidByIdentification(kidDTO.getIdentification());
        if (isKidRegistered) {
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
    public ResponseEntity<ResponseDTO> getkidsByLocation() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByLocation(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydepto")
    public ResponseEntity<ResponseDTO> getKidsByDeptoCode() {
        List<KidsByLocationDTO> kidsByLocationDTOList1 = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByDeptoCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList1.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList1,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/reportsKids/{age}")
    public ResponseEntity<ResponseDTO> getReportsKidsLocationGenders(@PathVariable byte age) {
        ReportKidsLocationDTO report = new ReportKidsLocationDTO(locationService.getLocationsByCodeSize(8));
        listSEService.getKids().getReportKidsByLocationByGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }

    @GetMapping(path = "reportsagekids/{age}")
    public ResponseEntity<ResponseDTO> getReportsKidsByAgeGender(@PathVariable byte age) {
        ReportAgeQuantityKidsDTO report = new ReportAgeQuantityKidsDTO(listSEService.getKids().getKids());
        listSEService.getKids().getReportKidsByAgeByGender(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }
}

