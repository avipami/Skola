package com.neopixels.Vecka3.Uppg2;

import java.util.ArrayList;

public class Database {
    ArrayList<Person> phoneBook = new ArrayList<>();

    public Database(){}
    public Database (Person person)
    {
        this.phoneBook.add(person);
    }

    public void printPeopleInPhoneBook()
    {
        if(phoneBook!=null)
        {
            phoneBook.forEach((person -> {
                System.out.println(person.getName() + " " + person.getPhoneNumber() + " " + person.getAddress());
            }));
        }

    }

    public ArrayList<Person> returnPhoneBookList()
    {
        return this.phoneBook;
    }

}
