package co.edu.umanizales.tdas.controller.dto;

import lombok.Data;

@Data
public class PetByPositionDTO {
    private String name;
    private String id;
    private String race;
    private Byte age;
    private String color;
    private char gender;
    private String codeOwner;
    private String codelocation;
    private int position;
}
