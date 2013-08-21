package be.ordina.droidina.fsm.background;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import be.ordina.droidina.fsm.background.callback.LoginCallback;
import be.ordina.droidina.fsm.http.RestServiceConsumer;
import be.ordina.droidina.fsm.model.Agent;

/**
 * Created by ordinamobile on 31/07/13.
 */
public class LoginAsyncTask extends AsyncTask<Agent, String, Agent>{

    private static final String TAG = "LoginAsyncTask";

    private RestServiceConsumer consumer;
    private LoginCallback callback;

    public LoginAsyncTask(Context context){
        consumer = RestServiceConsumer.getInstance(context);
    }

    public void setLoginCallback(LoginCallback callback){
        this.callback = callback;
    }

    @Override
    protected Agent doInBackground(Agent... agents) {
        //Get the first Agent from the varargs as there will only be one agent
        Agent agent = agents[0];

        Agent loggedInAgent = consumer.postForObject("http://droidinabackend.appspot.com/agents/login", agent, Agent.class);

        //We retrieved an agent from the back-end... Joepieee!!!
        if(loggedInAgent!=null)
            return loggedInAgent;
        else
            return null;
    }

    @Override
    protected void onPostExecute(Agent agent) {
        if(agent!=null){
            Log.d(TAG, "Agent " + agent.getFirstName() + " retrieved");
        }else{
            Log.d(TAG, "Wrong credentials!");
        }
        callback.onLoginResult(agent);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
