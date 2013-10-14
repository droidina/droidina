package be.ordina.droidina.speechengine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;

import be.ordina.droidina.R;

public class SpeechEngineActivity extends Activity {

    private static final String TAG = SpeechEngineActivity.class.getSimpleName();


    public static final String EXTRA_SMS_TEXT = "EXTRA_SMS_TEXT";
    public static final String EXTRA_SMS_SENDER_NUMBER = "EXTRA_SMS_SENDER_NUMBER";

    private Button _speakButton;
    private Button _recordButton;

    private EditText _speachOutput;

    private TextToSpeech _textToSpeech;


    private String _lastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_engine);

        _lastText = "You need to speak first";

        _speakButton = (Button) findViewById(R.id.speakButton);
        _speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // _textToSpeech.speak("this is just a simple test. hope it works well.", TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
                speak(_lastText);
            }
        });


        _textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (TextToSpeech.SUCCESS == i) {
                    _speakButton.setEnabled(true);
                }
            }
        });

        _recordButton = (Button) findViewById(R.id.recordButton);
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

        _speachOutput = (EditText) findViewById(R.id.speachOutput);


    }

    private void speak(String text) {
        _textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
