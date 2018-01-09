package it.arkadiuss.jaksiemasz_rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadius on 08.01.18.
 */

public class MessageEvent {
    private List<Person> people;

    public MessageEvent(List<Person> people){
        this.people=people;
    }

    List<Person> getPeople(){
        return people;
    }
}
