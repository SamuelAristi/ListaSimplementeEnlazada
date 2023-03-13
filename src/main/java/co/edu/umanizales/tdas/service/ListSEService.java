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

    public ListSEService(){
        kids=new ListSE();
        kids = new ListSE();
        kids.add(new Kid("234","samuel",(byte)4,"manizales"));
        kids.add(new Kid("434","daniel",(byte)5,"manizales"));
        kids.add(new Kid("534","felipe",(byte)8,"medellin"));
        kids.addToStart(new Kid("897","Michael",(byte)6,"cali"));
    }
    public Node getKids(){
        return kids.getHead();
    }



}
