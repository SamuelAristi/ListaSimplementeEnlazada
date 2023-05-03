package co.edu.umanizales.tdas.controller.dto;

import co.edu.umanizales.tdas.model.Location;

import java.util.ArrayList;
import java.util.List;

public class ReportPetsLocationDTO {

   private List<LocationGenderPetQuantityDTO> locationsGenderPetQuantityDTOS;

   public ReportPetsLocationDTO(List<Location> cities){
      locationsGenderPetQuantityDTOS = new ArrayList<>();
      for(Location locations: cities){
         locationsGenderPetQuantityDTOS.add(new LocationGenderPetQuantityDTO(locations.getName()));
      }
   }
   //Metodo para actualizar
   public void updateQuantity(String city,char gender){
      for(  LocationGenderPetQuantityDTO loc : locationsGenderPetQuantityDTOS){
         if(loc.getCity().equals(city)){
            for(GenderPetQuantityDTO genderDto: loc.getGenders()){
               if(genderDto.getGender()==gender){
                  genderDto.setQuantity(genderDto.getQuantity()+1);

                  loc.setTotal(loc.getTotal()+1);
                  return;
               }
            }
         }
      }
   }


}
