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

    public void setMessage(String message){
        if(messageView == null) return;
        messageView.setText(message);
    }

    public void setUsername(String username,int[] usernameColor){
        if(usernameView==null) return;
        usernameView.setText(username);
        usernameView.setTextColor(getUsernameColor(username,usernameColor));
    }

    private int getUsernameColor(String username , int[] usernameColor){
        int hash = 7;
        for(int i = 0,len = username.length(); i<len;i++){
            hash = username.codePointAt(i)+(hash<<5)-hash;
        }
        int index = Math.abs(hash % usernameColor.length);
        return usernameColor[index];
    }
}
