package co.edu.umanizales.tdas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private String name;
    private String id;
    private String race;
    private Byte age;
    private String color;
    private char gender;
    private Owner owner;
    private Location location;
    private Boolean status;


}
