package it.arkadiuss.jaksiemasz_rxjava;

import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkadius on 08.01.18.
 */

public class MainPresenterImpl implements MainContract.Presenter {
    @Nullable
    private MainContract.View view;
    private Repository repository;
    MainPresenterImpl(Repository repository){
        this.repository=repository;
    }

    @Override
    public void attach(MainContract.View view) {
        this.view=view;
    }

    @Override
    public void detach() {
        this.view=null;
    }

    @Override
    public void loadPeople() {
        repository.getPeople(new RepositoryCallback() {
            @Override
            public void onSuccess(List<Person> people) {
                EventBus.getDefault().post(new MessageEvent(people));
                view.updatePeopleView();
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void onSearchButtonClick(String searchPhrase) {
        loadSearchedContact(searchPhrase);
    }

    private void loadSearchedContact(String searchPhrase){
        repository.getPeople(searchPhrase,new RepositoryCallback() {
            @Override
            public void onSuccess(List<Person> people) {
                EventBus.getDefault().post(new MessageEvent(people));
                view.updatePeopleView();
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
