package co.edu.umanizales.tdas.model;


import co.edu.umanizales.tdas.controller.dto.ReportAgeQuantityPetsDTO;
import co.edu.umanizales.tdas.controller.dto.ReportPetsLocationDTO;
import lombok.Data;

import java.util.List;

@Data
public class ListDE {
    private List<Pet> pets;
    private List<Owner> owners;

    private NodeDE head;
    private int size;

    public void add(Pet pet){
        if (head != null){

            NodeDE temp=head;
            while(temp.getNext() != null)
            {
                temp=temp.getNext();
            }
            //parado en el ultimo
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.getNext().setPrev(temp);
        }
        else{
            head= new NodeDE(pet);
        }
        size ++;
    }

    public boolean checkPetById(String id){
        if(this.head != null){
            NodeDE temp= this.head;

            while (temp != null){
                if(temp.getData().getId().equals(id)){
                    return true;
                }
                temp=temp.getNext();
            }
        }
        return false;
    }
    public void addToStart(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (head != null) {
            head.setPrev(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

        public void deletePetById(String id) {
            if(head==null){
                return;
            }
            if(head.getData().getId().equals(id)){
                head = head.getNext();
                if(head != null){
                    head.setPrev(null);
                }
                return;
            }
            NodeDE current = head.getNext();
            while ((current != null)){
                if(current.getData().getId().equals(id)){
                    current.getPrev().setNext(current.getNext());
                    if(current.getNext()!=null){
                        current.getNext().setPrev(current.getPrev());
                    }
                    return;
                }
                current=current.getNext();
            }
        }
    public void deleteByAge(int age) {
        if (head == null) {
            return;
        }

        // Eliminar nodos en la cabeza de la lista con la edad dada
        while (head != null && head.getData().getAge() == age) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        }

        NodeDE current = head;
        while (current != null) {
            if (current.getData().getAge() == age) {
                NodeDE prev = current.getPrev();
                NodeDE next = current.getNext();
                if (prev != null) {
                    prev.setNext(next);
                }
                if (next != null) {
                    next.setPrev(prev);
                }
            }
            current = current.getNext();
        }
    }
    public void addByPosition(Pet pet, int position) {
        NodeDE newNode = new NodeDE(pet);
        if (position <= 1) {
            addToStart(pet);
        } else {
            NodeDE current = head;
            for (int i = 1; i < position - 1 && current != null; i++) {
                current = current.getNext();
            }
            if (current != null) {
                newNode.setNext(current.getNext());
                newNode.setPrev(current);
                if (current.getNext() != null) {
                    current.getNext().setPrev(newNode);
                }
                current.setNext(newNode);
            }
        }
    }
    public double getAverageAge() {
        int totalAge = 0;
        int numPets = 0;
        NodeDE temp = head;

        while (temp != null) {
            Pet pet = temp.getData();
            totalAge += pet.getAge();
            numPets++;
            temp = temp.getNext();
        }

        if (numPets > 0) {
            return (double) totalAge / numPets;
        } else {
            return 0.0;
        }
    }
    public void changeExtremes() {
        if (head != null && head.getNext() != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            // temp está en el último nodo
            Pet copy = head.getData();
            head.setData(temp.getData());
            temp.setData(copy);

            NodeDE tempPrev = temp.getPrev();
            tempPrev.setNext(null);
            temp.setPrev(null);

            NodeDE tempNext = head.getNext();
            head.setNext(null);
            head.setPrev(temp);
            temp.setNext(head);
            tempNext.setPrev(head);
            head.setNext(tempNext);
            head = temp;
        }
    }
    public void gainPosition(String id, int position, ListDE listDE) {
        if (head != null) {
            NodeDE temp = head;
            int count = 1;

            while (temp != null && !temp.getData().getId().equals(id)) {
                temp = temp.getNext();
                count++;
            }
            int newPosition = position - count;
            Pet listCopy = temp.getData();
            listDE.deletePetById(temp.getData().getId());
            listDE.addByPosition(listCopy, newPosition);
        }
    }
    public void backPosition(String id, int position, ListDE listDE) {
        if (head != null) {
            NodeDE temp = head;
            int count = 1;

            while (temp != null && !temp.getData().getId().equals(id)) {
                temp = temp.getNext();
                count++;
            }
            int newPosition = position + count;
            Pet listCopy = temp.getData();
            listDE.deletePetById(temp.getData().getId());
            listDE.addByPosition(listCopy, newPosition);
        }
    }

        public void orderBoysToStart() {
            if (this.head != null) {
                ListDE listCp = new ListDE();
                NodeDE temp = this.head;
                while (temp != null) {
                    if (temp.getData().getGender() == 'M') {
                        listCp.addToStart(temp.getData());
                    } else {
                        listCp.add(temp.getData());
                    }

                    temp = temp.getNext();
                }
                this.head = listCp.getHead();
                this.head.setPrev(null);
            }
        }
    public void intercalateByGender() {
        ListDE boysList = new ListDE();
        ListDE girlsList = new ListDE();

        NodeDE current = head;
        while (current != null) {
            if (current.getData().getGender() == 'M') {
                boysList.add(current.getData());
            } else {
                girlsList.add(current.getData());
            }
            current = current.getNext();
        }

        ListDE combinedList = new ListDE();
        NodeDE boysCurrent = boysList.getHead();
        NodeDE girlsCurrent = girlsList.getHead();
        while (boysCurrent != null && girlsCurrent != null) {
            combinedList.add(boysCurrent.getData());
            combinedList.add(girlsCurrent.getData());
            boysCurrent = boysCurrent.getNext();
            girlsCurrent = girlsCurrent.getNext();
        }

        while (boysCurrent != null) {
            combinedList.add(boysCurrent.getData());
            boysCurrent = boysCurrent.getNext();
        }

        while (girlsCurrent != null) {
            combinedList.add(girlsCurrent.getData());
            girlsCurrent = girlsCurrent.getNext();
        }

        head = combinedList.getHead();
    }
    public int getCountPetsByLocation(String code) {
        int count = 0;
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetByDeptoCode(String code){
        int count =0;
        if( this.head!=null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().substring(0,5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }
    public void getReportPetsByLocationByGendersByAge(byte age, ReportPetsLocationDTO report) {
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getAge() > age) {
                    report.updateQuantity(temp.getData().getLocation().getName(), temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }
    public void getReportPetsByAgeByGender(byte age, ReportAgeQuantityPetsDTO report) {
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getAge() == age ) {
                    report.updateQuantity(temp.getData().getAge(), temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }



}



















