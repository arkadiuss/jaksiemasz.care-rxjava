package it.arkadiuss.jaksiemasz_rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    @BindView(R.id.recycler_people) RecyclerView peopleView;
    @BindView(R.id.main_toolbar) Toolbar searchBar;
    @BindView(R.id.search_field) EditText searchField;
    @BindView(R.id.seach_button) Button searchButton;
    private PeopleAdapterImpl peopleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(searchBar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        presenter=new MainPresenterImpl(new RepositoryImpl());
        presenter.attach(this);
        peopleAdapter=new PeopleAdapterImpl();
        peopleView.setAdapter(peopleAdapter);
        peopleView.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter.registerEventBus();
        presenter.loadPeople();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        peopleAdapter.unregisterEventBus();
    }

    @Override
    public void updatePeopleView() {
        peopleAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.seach_button)
    public void search(){
        presenter.onSearchButtonClick(searchField.getText().toString());
    }
}
