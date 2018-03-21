package org.androidtown.socketiochatting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by DASOM on 2018-03-21.
 */

public class MessageAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Message> messages;
    private int[] usernameColor;

    public MessageAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        usernameColor = context.getResources().getIntArray(R.array.username_colors);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType){
            case Message.TYPE_MESSAGE:
                layout = R.layout.item_message;
                break;
            case Message.TYPE_LOG:
                layout=R.layout.item_log;
                break;
            case Message.TYPE_ACTION:
                layout=R.layout.item_action;
                break;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v,usernameColor);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = messages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setUsername(message.getUsername());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }
}
