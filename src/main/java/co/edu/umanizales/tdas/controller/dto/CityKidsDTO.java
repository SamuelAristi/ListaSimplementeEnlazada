package co.edu.umanizales.tdas.controller.dto;
import co.edu.umanizales.tdas.model.Kid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityKidsDTO {

    private Kid kid;
    private int quantity;
}
