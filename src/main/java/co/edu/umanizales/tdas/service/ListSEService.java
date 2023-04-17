package co.edu.umanizales.tdas.service;

import co.edu.umanizales.tdas.model.Kid;
import co.edu.umanizales.tdas.model.ListSE;
import co.edu.umanizales.tdas.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {

    private ListSE kids;

    public ListSEService() {
        kids=new ListSE();
    }
    public void invert(){
        kids.invert();
    }
    public void getAverageAge(){
        kids.getAverageAge();
    }


}
