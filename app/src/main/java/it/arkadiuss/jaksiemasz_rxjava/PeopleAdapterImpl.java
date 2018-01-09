package it.arkadiuss.jaksiemasz_rxjava;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadius on 08.01.18.
 */

public class PeopleAdapterImpl extends RecyclerView.Adapter<PeopleAdapterImpl.ViewHolder> implements PeopleAdapter {
    private List<Person> people;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.name_tv)
        TextView name;
        @BindView(R.id.phone_tv)
        TextView phone;
        @BindView(R.id.email_tv)
        TextView email;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_person,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.avatar.getContext())
                .load(people.get(position).getAvatar()).into(holder.avatar);
        holder.name.setText(people.get(position).getFullName());
        holder.email.setText(people.get(position).getEmail());
        holder.phone.setText(people.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    @Subscribe
    public void receiveEvent(MessageEvent event) {
        this.people=event.getPeople();
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

}
