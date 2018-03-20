package org.androidtown.socketiochatting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DASOM on 2018-03-21.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView usernameView;
    private TextView messageView;

    public ViewHolder(View itemView) {
        super(itemView);

        usernameView = (TextView)itemView.findViewById(R.id.username);
        messageView = (TextView)itemView.findViewById(R.id.message);
    }

    public TextView getUsernameView() {
        return usernameView;
    }

    public void setUsernameView(TextView usernameView) {
        this.usernameView = usernameView;
    }

    public TextView getMessageView() {
        return messageView;
    }

    public void setMessageView(TextView messageView) {
        this.messageView = messageView;
    }
}
