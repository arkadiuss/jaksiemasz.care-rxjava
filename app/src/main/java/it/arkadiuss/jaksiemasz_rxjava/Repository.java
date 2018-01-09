package it.arkadiuss.jaksiemasz_rxjava;

import java.util.ArrayList;

/**
 * Created by arkadius on 08.01.18.
 */

interface Repository {
    void getPeople(RepositoryCallback callback);
    void getPeople(String searchPhrase, RepositoryCallback callback);
}
