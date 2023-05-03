package co.edu.umanizales.tdas.model;

import co.edu.umanizales.tdas.controller.dto.ReportAgeQuantityKidsDTO;
import co.edu.umanizales.tdas.controller.dto.ReportKidsLocationDTO;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
public class ListSE {
    private Node head;

    private int size;
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
        size ++;
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
        size++;
    }
    public void deleteByIdentification(String identification) {
        if (head == null) {
            return;
        }

        if (head.getData().getIdentification().equals(identification)) {
            head = head.getNext();
            return;
        }

        Node current = head.getNext();
        Node previous = head;

        while (current != null) {
            if (current.getData().getIdentification().equals(identification)) {
                previous.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }
    public void deleteByAge(int age) {
        if (head == null) {
            return;
        }

        while (head != null && head.getData().getAge() == age) {
            head = head.getNext();
        }

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.getData().getAge() == age) {
                previous.setNext(current.getNext());
            } else {
                previous = current;
            }
            current = current.getNext();
        }
    }


    public boolean checkKidByIdentification(String identification) {
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getIdentification().equals(identification)) {
                    return true;
                }
                temp = temp.getNext();
            }
        }
        return false;
    }

    //metodo para añadir por posicion
    public void addByPosition(Kid kid , int position){
        Node newNode= new Node(kid);
        if(position<=1){
           addToStart(kid);
        }else{
            Node current= head;
            for(int i=1 ; i<position-1;i++){
                current=current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }


    public double getAverageAge() {
        int totalAge = 0;
        int numKids = 0;
        Node temp = this.head;

        while (temp != null) {
            Kid kid = temp.getData();
            totalAge += kid.getAge();
            numKids++;
            temp = temp.getNext();
        }

        if (numKids > 0) {
            return (double) totalAge / numKids;
        } else {
            return 0.0;
        }
    }


    //metodo para intercambiar extremos
    public void changeExtremes(){
        if(this.head !=null && this.head.getNext() != null){
            Node temp = this.head;
            while(temp.getNext()!= null){
                temp = temp.getNext();
            }
            //temp esta en el ultimo
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }
    public void invert(){
        if(this.head != null){
            ListSE listCp= new ListSE();
            Node temp = this.head;
            while(temp!= null){
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
        }
    }
    public void gainPosition(String id, int position, ListSE listSE){
        if (head != null){
            Node temp = this.head;
            int count = 1;

            while (temp != null && ! temp.getData().getIdentification().equals(id)){
                temp = temp.getNext();
                count ++;
            }
            int newPosition = position-count;
            Kid listCopy = temp.getData();
            listSE.deleteByIdentification(temp.getData().getIdentification());
            listSE.addByPosition(listCopy , newPosition);
        }
    }

    public void backPosition(String id, int position, ListSE listSE){
        if (head != null){
            Node temp = this.head;
            int count = 1;

            while (temp != null && ! temp.getData().getIdentification().equals(id)){
                temp = temp.getNext();
                count ++;
            }
            int newPosition = position+count-1;
            Kid listCopy = temp.getData();
            listSE.deleteByIdentification(temp.getData().getIdentification());
            listSE.addByPosition(listCopy , newPosition);
        }
    }
    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
    public void intercalateByGender() {
        ListSE boysList = new ListSE();
        ListSE girlsList = new ListSE();

        Node current = head;
        while(current != null) {
            if(current.getData().getGender() == 'M') {
                boysList.add(current.getData());
            } else {
                girlsList.add(current.getData());
            }
            current = current.getNext();
        }

        ListSE combinedList = new ListSE();
        Node boysCurrent = boysList.getHead();
        Node girlsCurrent = girlsList.getHead();
        while(boysCurrent != null && girlsCurrent != null) {
            combinedList.add(boysCurrent.getData());
            combinedList.add(girlsCurrent.getData());
            boysCurrent = boysCurrent.getNext();
            girlsCurrent = girlsCurrent.getNext();
        }

        while(boysCurrent != null) {
            combinedList.add(boysCurrent.getData());
            boysCurrent = boysCurrent.getNext();
        }

        while(girlsCurrent != null) {
            combinedList.add(girlsCurrent.getData());
            girlsCurrent = girlsCurrent.getNext();
        }

        head = combinedList.getHead();
    }



    public int getCountKidsByLocation(String code){
        int count =0;
        if(this.head !=null){
            Node temp= this.head;
            while (temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }
    public int getCountKidsByDeptoCode(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().substring(0,5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void getReportKidsByLocationByGendersByAge(byte age, ReportKidsLocationDTO report){
        if(head != null){
            Node temp=this.head;
            while(temp != null){
                 if(temp.getData().getAge()>age){
                     report.updateQuantity(temp.getData().getLocation().getName(), temp.getData().getGender());
                 }
                 temp = temp.getNext();
            }
        }
    }
public void getReportKidsByAgeByGender(byte age, ReportAgeQuantityKidsDTO report){
        if(head!= null){
            Node temp= this.head;
            while (temp != null){
                if(temp.getData().getAge()==age){
                    report.updateQuantity(temp.getData().getAge(),temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
}

}
