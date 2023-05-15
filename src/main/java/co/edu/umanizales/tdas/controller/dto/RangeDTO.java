package co.edu.umanizales.tdas.controller.dto;

import co.edu.umanizales.tdas.model.Ranges;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeDTO {
    private Ranges ranges;
    int quantity;
}