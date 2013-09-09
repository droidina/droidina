package be.ordina.droidina.speechengine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;

import be.ordina.droidina.R;

/**
 * Created by fbousson on 08/09/13.
 */
public class SpeechEngineFragment extends Fragment {

    private static final String TAG = SpeechEngineActivity.class.getSimpleName();

    private Button _speakButton;
    private Button _recordButton;

    private EditText _speachOutput;

    private TextToSpeech _textToSpeech;


    private String _lastText;


    public static Fragment newInstance(){ //Bundle args){
        SpeechEngineFragment fragment = new SpeechEngineFragment();
        //Currently no arguments need to be set
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.activity_speech_engine, container, false);


        _lastText = "You need to speak first";

        _speakButton = (Button) fragmentView.findViewById(R.id.speakButton);
        _speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // _textToSpeech.speak("this is just a simple test. hope it works well.", TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
                speak(_lastText);
            }
        });


        _textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (TextToSpeech.SUCCESS == i) {
                    _speakButton.setEnabled(true);
                }
            }
        });

        _recordButton = (Button) fragmentView.findViewById(R.id.recordButton);
        _recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent recordTalkIntent = new Intent();
                recordTalkIntent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                recordTalkIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //   recordTalkIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                startActivityForResult(recordTalkIntent, 0);

            }
        });

        _speachOutput = (EditText) fragmentView.findViewById(R.id.speachOutput);

        return fragmentView;
    }


    private void speak(String text) {
        _textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && Activity.RESULT_OK == resultCode && data.getExtras() != null) {
            List<String> results = (List<String>) data.getExtras().get(RecognizerIntent.EXTRA_RESULTS);
            _speachOutput.setText(results.toString());
            if (!results.isEmpty()) {
                _lastText = results.get(0) + " motherfucker";
            }

        } else {
            Log.e(TAG, "Something went horribly wrong");
        }

    }

}
