package org.androidtown.socketiochatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameView;
    private String mUsername;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ChatApplication app = (ChatApplication)getApplication();
        mSocket=app.getSocket();

        mUsernameView = (EditText) findViewById(R.id.username_input);
        mUsernameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id==R.id.login||id== EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mSocket.on("login",onLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.off("login",onLogin);
    }

    private void attemptLogin(){
        mUsernameView.setError(null);

        String username = mUsernameView.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            mUsernameView.setError("");
            mUsernameView.requestFocus();
            return;
        }

        mUsername = username;
        mSocket.emit("add user",username);
    }

    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            int numUsers;
            try{
                numUsers=data.getInt("numUsers");
            }catch (JSONException e){
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("username",mUsername);
            intent.putExtra("numUsers",numUsers);
            setResult(RESULT_OK,intent);
            finish();
        }
    };
}
