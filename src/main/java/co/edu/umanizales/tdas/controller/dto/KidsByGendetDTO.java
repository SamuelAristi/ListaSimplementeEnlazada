package co.edu.umanizales.tdas.controller.dto;

import co.edu.umanizales.tdas.model.Location;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class KidsByGendetDTO {
    private String city;
    private List<GenderDTO> genderDTOList;
    private int totalkids;
}
