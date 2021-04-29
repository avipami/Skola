package Uppg1;

import java.util.ArrayList;

public class DataAccessObjectClass {


    ArrayList<Person> KompisArrayList = new ArrayList<>(); // en lista av typen person

    public DataAccessObjectClass(){setFakeDB();}

    public void getKompisar()
    {
        if(KompisArrayList.get(0)!=null){
            KompisArrayList.forEach((K) ->{
                System.out.println(K.getName() + " : " + K.getAddress() + " : " + K.getPhoneNumber());
            });
        }
        System.out.println("Du har inga vänner HAH!");
    }


    public void setFakeDB(){
        Person Joko =new Person("Joko","Bredvid Mig 13144","666 666 666");
        Person Ankan= new Person("DanielKwak","Södertälje","929292929");
        Person Robban = new Person("RObert Wellingtong","Södermalm","123RingMig");
        this.KompisArrayList.add(Joko);
        this.KompisArrayList.add(Ankan);
        this.KompisArrayList.add(Robban);
    }
}
