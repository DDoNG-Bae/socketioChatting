package org.androidtown.socketiochatting;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by DASOM on 2018-03-21.
 */

public class ChatApplication extends Application {

    private Socket mSocket;{
        try{
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket(){
        return mSocket;
    }
}
