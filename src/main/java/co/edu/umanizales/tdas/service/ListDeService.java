package co.edu.umanizales.tdas.service;
import co.edu.umanizales.tdas.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDeService {

    private ListDE pets;

    public ListDeService() {
        pets=new ListDE();
    }
}