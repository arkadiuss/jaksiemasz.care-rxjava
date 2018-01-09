package it.arkadiuss.jaksiemasz_rxjava;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arkadius on 08.01.18.
 */

public class RepositoryImpl implements Repository {
    private static final String URL="http://80.211.240.79/";
    private Retrofit retrofit;
    public RepositoryImpl(){
        this.retrofit= new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    interface APIClient{
        @GET("people")
        Single<List<Person>> getPeople();
        @GET("people")
        Single<List<Person>> getPeople(@Query("phrase") String phrase);
    }

    @Override
    public void getPeople(final RepositoryCallback callback) {
        APIClient api = retrofit.create(APIClient.class);
        api.getPeople()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Exception {
                        callback.onSuccess(people);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(throwable);
                    }
                })
                .subscribe();
    }

    @Override
    public void getPeople(String searchPhrase, final RepositoryCallback callback) {
        APIClient api = retrofit.create(APIClient.class);
        api.getPeople(searchPhrase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Exception {
                        callback.onSuccess(people);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(throwable);
                    }
                })
                .subscribe();
    }
}
