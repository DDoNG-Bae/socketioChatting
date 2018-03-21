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
    private int[] usernameColor;
    public ViewHolder(View itemView,int[] usernameColor) {
        super(itemView);

        this.usernameColor = usernameColor;
        usernameView = (TextView)itemView.findViewById(R.id.username);
        messageView = (TextView)itemView.findViewById(R.id.message);
    }

    public void setMessage(String message){
        if(messageView == null) return;
        messageView.setText(message);
    }

    public void setUsername(String username){
        if(usernameView==null) return;
        usernameView.setText(username);
        usernameView.setTextColor(getUsernameColor(username));
    }

    private int getUsernameColor(String username){
        int hash = 7;
        for(int i = 0,len = username.length(); i<len;i++){
            hash = username.codePointAt(i)+(hash<<5)-hash;
        }
        int index = Math.abs(hash % usernameColor.length);
        return usernameColor[index];
    }
}
