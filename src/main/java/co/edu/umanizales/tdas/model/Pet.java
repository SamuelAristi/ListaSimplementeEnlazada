package co.edu.umanizales.tdas.model;

import lombok.Data;

@Data
public class Pet {
    private String name;
    private String animal;
    private String race;
    private String color;
    private Owner owner;
    private String Code;

}
