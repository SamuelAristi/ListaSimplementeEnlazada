package co.edu.umanizales.tdas.service;

import co.edu.umanizales.tdas.model.Owner;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class OwnerService {

    private List<Owner> owners;

    public OwnerService(){
         owners = new ArrayList<>();
    }

    public void addOwner(Owner owner) {
        owners.add(owner);
    }



    public Owner getOwnerById(String id){
        ;
        for(Owner own: owners){
            if(own.getId().equals(id)){
                return own;
            }
        }
        return null;
    }


}
