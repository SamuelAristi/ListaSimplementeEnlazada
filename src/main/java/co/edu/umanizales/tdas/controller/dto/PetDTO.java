package co.edu.umanizales.tdas.controller.dto;

import co.edu.umanizales.tdas.model.Location;
import co.edu.umanizales.tdas.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetDTO {

    private String name;
    private String id;
    private String race;
    private Byte age;
    private String color;
    private char gender;
    private String codeOwner;
    private String codelocation;


}
