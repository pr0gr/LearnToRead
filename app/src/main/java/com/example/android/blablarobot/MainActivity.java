package com.example.android.blablarobot;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements TextToSpeech.OnInitListener {

//    private Button uiButton;
    private TextToSpeech mTTS;
//    public EditText uiEditText;
    public float speechRate = 0.8f;
    public Button uiDecSpeechRate;
    public Button uiIncSpeechRate;
    public TextView uiSpeechRate;
    public ImageView uiImageGirlDroid;
    public ImageButton uiNextButton;
    public TextView uiWord;

    public String[] Words = {"МАМА", "Евгения", "Максим", "Рома", "ПАПА", "ОКНО", "САПОГ", "нора", "нога", "рука", "стол", "стул", "ветер", "дом", "гамак", "палка", "метро", "пирог", "порог","бис", "рис", "бита", "Рита", "зябрь", "рябь", "лента", "рента", "лепка", "репка", "Лёва", "рёва", "печка", "речка", "писк", "риск", "Федька", "редька", "кепка", "репка", "кровать", "кошка", "крошка", "топка", "тропка", "каска", "краска", "дама", "драма", "гад", "игра", "пора", "дыра", "жара", "куры", "комары", "горы", "топоры", "Алла", "Эля", "Элла", "Эмма", "Эрик", "Оля", "Эдик", "Уля", "Алик", "лук", "лот", "лайка", "лапка", "лопата", "лавка", "лампа", "пила", "сила", "салат", "халат", "дела", "клубок", "платок", "плыть", "класс", "клумба", "клоун", "град", "мычать", "рычать", "пугать", "ругать", "макеты", "ракеты", "губка", "рубка", "мак", "рак", "нога", "рога", "высь", "рысь", "мука", "рука", "газ", "раз"};
    public int numberOfWords;
    public String currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        uiEditText = (EditText) findViewById(R.id.editText1);
        uiDecSpeechRate = (Button) findViewById(R.id.decreaseSpeechRate);
        uiIncSpeechRate = (Button) findViewById(R.id.increaseSpeechRate);
        uiSpeechRate = (TextView) findViewById(R.id.speechRate);
        uiImageGirlDroid = (ImageView) findViewById(R.id.imageGirldroid);
        uiNextButton = (ImageButton) findViewById(R.id.nextButton);
        uiWord = (TextView) findViewById(R.id.word);
//        uiButton = (Button) findViewById(R.id.button1);

        numberOfWords = Words.length;
        currentWord = Words[(int)(Math.floor(Math.random() * numberOfWords))];
        uiWord.setText(currentWord);

        mTTS = new TextToSpeech(this, this);

        mTTS.setSpeechRate(speechRate);

        uiSpeechRate.setText(Float.toString(speechRate));

        uiNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWord = Words[(int)(Math.floor(Math.random() * numberOfWords))];
                uiWord.setText(currentWord);
            }
        });

        uiImageGirlDroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTTS.speak(currentWord, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        uiDecSpeechRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speechRate > 0.1) {
                    speechRate = speechRate - 0.1f;
                    mTTS.setSpeechRate(speechRate);
                    uiSpeechRate.setText(Float.toString(speechRate));
                }
            }
        });

        uiIncSpeechRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechRate = speechRate + 0.1f;
                mTTS.setSpeechRate(speechRate);
                uiSpeechRate.setText(Float.toString(speechRate));
            }
        });

//        uiButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String whatToSay = uiEditText.getText().toString();
//                mTTS.speak(whatToSay, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });

    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            Locale locale = new Locale("ru");

            int result = mTTS.setLanguage(locale);
            //int result = mTTS.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается");
            } else {
//                uiButton.setEnabled(true);
            }

        } else {
            Log.e("TTS", "Не удалось инициализировать движок говорилки");
        }

    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown mTTS!
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

}
