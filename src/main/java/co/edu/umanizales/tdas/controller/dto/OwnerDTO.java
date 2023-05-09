package co.edu.umanizales.tdas.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerDTO {

    private String name;
    private String id;
    private String contact;
    private char gender;
    private String codeLocation;
}
