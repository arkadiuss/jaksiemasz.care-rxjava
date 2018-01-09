package it.arkadiuss.jaksiemasz_rxjava;

/**
 * Created by arkadius on 08.01.18.
 */

interface MainContract {
    interface View{
        void updatePeopleView();
    }

    interface Presenter{
        void attach(View view);
        void detach();
        void loadPeople();
    }
}
