package co.edu.umanizales.tdas.model;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Data
@Getter
public class ListSE {
    private Node head;
    private List<Kid> kids;



    /*
     Algoritmo de adicionar al final

     como Entreada recibimos a
        un niño
     si hay datos // pueden haber dos opciones por las que puedo tomar

     si
            llamar a un ayudante que se posicione en la cabeza y  compruebe si en el brazo
            hay algo, si existe ese algo, paso al siguiente
            hasta llegar al ultimo

           al llegar al ultimo meter al niño en un costal (nuevo costal)
            y le digo al ultimo que tome el nuevo costal y lo ubique en el ultimo lugar

      no

      metemos al niño en ese costal y ese costal es la cabeza

     */
    public void add(Kid kid){
        if (head != null){

            Node temp=head;
            while(temp.getNext() != null)
            {
                temp=temp.getNext();
            }
            //parado en el ultimo
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else{
            head= new Node(kid);
        }
    }
    /*
    Adicionar al inicio
    si hay datos
    puedo tener dos opciones
    si
    meto al niño en un costal (nuevo costal)
    le digo a nuevo costal que tome con su brazo a la cabeza
    cabea es igual a nuevo costal
    no
    meto al niño en un cosata y lo asigno a la cabezza
    */

    public void addToStart(Kid kid){
        if(head != null){
            Node newNode=new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else{
            head= new Node(kid);
        }
    }
    /*
 metodo de elinar elementos

 buscar el elemento que deseamos eliminar
 mirar en que lugar esta posicionado el elemento
 dependiento del lugar en que se encuentre el elemento cambiar al anterior o al siguiente
 borrar el elemento
    */
    public void deleteByIdentification(String identification){
        Node current=head;
        Node prev=null;

        while (current != null && current.getData().getIdentification() !=identification){
            prev=current;
            current=current.getNext();
        }
        if(current !=null){
            if(prev == null){
                head= current.getNext();
            }else{
                prev.setNext(current.getNext());
            }
        }
    }
    public void addByPosition(Kid kid , int position){
        Node newNode= new Node(kid);
        if(position==0){
            newNode.setNext(head);
            head = newNode;
        }else{
            Node current= head;
            for(int i=0 ; i<position-1;i++){
                current=current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }
    public byte getTotalAges(){
        byte sum=0;
        for(Kid kid: kids){
            sum = (byte) (sum+ kid.getAge());
        }
        return sum;
    }

    public void getTotalKidsByCity() {
        // el hashmap es una variable que se utiliza para almacenar valores que tengan referencias, como en este caso una ciudad
        //que va a ir obteniendo un valor
        HashMap<String, Integer> cityMap = new HashMap<>();
        for (Kid kid : kids) {
            String kidCity = kid.getCity();
            if (kidCity != null) {
                //el constainsKey se utiliza para verificar si la ciudad ya tiene un valor, si lo tiene se le suma uno
                if (cityMap.containsKey(kidCity)) {
                    cityMap.put(kidCity, cityMap.get(kidCity) + 1);
                    //en este caso si la ciudad es la primera vez que se encuentra se agrega y se iniciliza con un valor 1
                } else {
                    cityMap.put(kidCity, 1);
                }
            }
        }

        if (cityMap.containsKey(city)) {
            int count = cityMap.get(city);
            System.out.println("Total kids in " + city + ": " + count);
        } else {
            System.out.println("No kids found in " + city);
        }
    }
}
