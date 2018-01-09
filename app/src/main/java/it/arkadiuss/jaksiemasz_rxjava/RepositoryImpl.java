package it.arkadiuss.jaksiemasz_rxjava;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by arkadius on 08.01.18.
 */

public class RepositoryImpl implements Repository {
    private Person[] people ={
            new Person("1","Owen","Shaw","owen@shaw.eu",
                    "dasd", "https://randomuser.me/api/portraits/men/52.jpg"),
            new Person("2","NoOwen","NoShaw","noowen@shaw.eu",
                    "dasd", "https://randomuser.me/api/portraits/men/52.jpg"),
            new Person("3","UnOwen","UnShaw","owen@shaw.eu",
                    "dasd", "https://randomuser.me/api/portraits/men/52.jpg"),
    };

    @Override
    public void getPeople(RepositoryCallback callback) {
        callback.onSuccess(Arrays.asList(people));
    }

    @Override
    public void getPeople(String searchPhrase,RepositoryCallback callback) {
        callback.onSuccess(Arrays.asList(people));
    }
}
