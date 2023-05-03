package co.edu.umanizales.tdas.controller.dto;

import lombok.Data;

@Data
public class KidByPositionDTO {
    private String identification;
    private String name;
    private byte age;
    private char gender;
    private String codeLocation;
    private int position;
}
