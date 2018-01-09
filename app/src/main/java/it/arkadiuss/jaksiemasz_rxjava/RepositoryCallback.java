package it.arkadiuss.jaksiemasz_rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadius on 09.01.18.
 */

interface RepositoryCallback {
    void onSuccess(List<Person> people);
    void onError(Throwable t);
}
