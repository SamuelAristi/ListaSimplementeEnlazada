package co.edu.umanizales.tdas.controller.dto;


import co.edu.umanizales.tdas.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsByLocationDTO {
    private Location location;
    private int quantity;
}
