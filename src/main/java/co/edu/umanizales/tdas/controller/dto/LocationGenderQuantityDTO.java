package co.edu.umanizales.tdas.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class LocationGenderQuantityDTO {
    private String city;
    private List<GenderQuantityDTO> genders;
    private int total;

    public LocationGenderQuantityDTO(String city){
        this.city = city;
        this.total = 0;
        this.genders =  new ArrayList<>();
        this.genders.add(new GenderQuantityDTO('M',0));
        this.genders.add(new GenderQuantityDTO('F',0));
    }
}
