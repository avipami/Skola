package Uppg2;

import java.util.ArrayList;

public class TelephoneBook {


    ArrayList<Person> KontaktLista = new ArrayList<>();

    public TelephoneBook(){setFakeDB();}

    public String getTelefoneNumberFrom(String s)
    {
        for(Person nr : KontaktLista)
        {
            if(nr.getName().equalsIgnoreCase(s)){
                return nr.getPhoneNumber();
            }

        }
        return null;
    }
    public void getKompisar(){
        if(KontaktLista.get(0)!=null){
            KontaktLista.forEach((K) ->{
                System.out.println(K.getName() + " : " + K.getAddress() + " : " + K.getPhoneNumber());
            });
        }
        System.out.println("Du har inga vänner HAH!");
    }


    public void setFakeDB(){
        Person Joko =new Person("Joko","Bredvid Mig 13144","666 666 666");
        Person Ankan= new Person("DanielKwak","Södertälje","929292929");
        Person Robban = new Person("RObert Wellingtong","Södermalm","123RingMig");
        this.KontaktLista.add(Joko);
        this.KontaktLista.add(Ankan);
        this.KontaktLista.add(Robban);
    }
}
