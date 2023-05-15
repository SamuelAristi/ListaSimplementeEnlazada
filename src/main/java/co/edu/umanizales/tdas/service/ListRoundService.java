package co.edu.umanizales.tdas.service;

import co.edu.umanizales.tdas.model.ListRound;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListRoundService {
    private ListRound pets;
    public ListRoundService(){pets = new ListRound();}
}
