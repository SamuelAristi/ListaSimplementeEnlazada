package co.edu.umanizales.tdas.controller.dto;

import co.edu.umanizales.tdas.model.Kid;
import co.edu.umanizales.tdas.model.Location;

import java.util.ArrayList;
import java.util.List;

public class ReportAgeQuantityKidsDTO {

    private List<AgeGenderQuantityDTO> ageGenderQuantityDTOS;

    public ReportAgeQuantityKidsDTO(List<Kid> kids){
        ageGenderQuantityDTOS = new ArrayList<>();
        for(Kid kid: kids){
            ageGenderQuantityDTOS.add(new AgeGenderQuantityDTO(kid.getAge()));
        }
    }

    public void updateQuantity(int age,char gender){
        for(AgeGenderQuantityDTO ageGender : ageGenderQuantityDTOS){
            if(ageGender.getAge()==age){
                for(GenderQuantityDTO genderDto: ageGender.getGenders()){
                    genderDto.setQuantity(genderDto.getQuantity()+1);
                    ageGender.setTotal(ageGender.getTotal()+1);
                    return;
                }
            }
        }
    }



}
