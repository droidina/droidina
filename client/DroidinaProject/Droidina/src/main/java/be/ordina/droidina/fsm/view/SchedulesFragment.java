package be.ordina.droidina.fsm.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.ordina.droidina.R;
import be.ordina.droidina.fsm.background.LoginAsyncTask;
import be.ordina.droidina.fsm.background.callback.LoginCallback;
import be.ordina.droidina.fsm.model.Agent;

/**
 * Created by ordinamobile on 28/06/13.
 */
public class SchedulesFragment extends Fragment implements LoginCallback {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private LoginAsyncTask loginAsyncTask;

    public static Fragment newInstance(){
        return new SchedulesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Here you usually define if you wish to retain the fragment
        setRetainInstance(true);
        //setHasOptionsMenu(true);
        instantiateLoginAsyncTask();
        //Also, AsyncTasks can be created here
    }

    private void instantiateLoginAsyncTask(){
        loginAsyncTask = new LoginAsyncTask(getActivity().getApplicationContext());
        loginAsyncTask.setLoginCallback(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the fragment layout here
        //get a reference to your UI components and link listeneres to them etc...
        View view = inflater.inflate(R.layout.fragment_schedules, container, false);

        usernameEditText = (EditText)view.findViewById(R.id.username_edit_text);
        passwordEditText = (EditText)view.findViewById(R.id.password_edit_text);

        loginButton = (Button)view.findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Agent agent = new Agent();
                agent.setUserName(username);
                agent.setPassword(password);

                if(loginAsyncTask!=null && !loginAsyncTask.isCancelled()){
                    //Re-instantiate the asynctask each time we call it. Task can only be run once!
                    instantiateLoginAsyncTask();
                    loginAsyncTask.execute(agent);
                }
            }
        });

        return view;
    }

    @Override
    public void onLoginResult(Agent agent) {
        //Agent succesfully retrieved
        if(agent!=null){
            Toast.makeText(getActivity(), "Logged on succesfully!", Toast.LENGTH_LONG).show();
        }
        loginAsyncTask.cancel(true);
        loginAsyncTask=null;
    }
}
