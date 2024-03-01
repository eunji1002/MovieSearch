package com.example.moviesearch;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //권한 허용 == 1
    final int PERMISSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //어플리케이션 시작 할 때 권한 체크
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO}, PERMISSION);
        }

        SearchView searchView;
        searchView = findViewById(R.id.search_view);
        ExtendedFloatingActionButton efab = findViewById(R.id.Voice_Button);

        searchView.setSubmitButtonEnabled(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
               

                intent.putExtra("대사", query);
                startActivity(intent);
                return true;
            }

            //텍스트가 바뀔때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
               

                return true;
            }


        });


//      보이스 버튼 누르면 보이스 xml로 넘어가는 코드
        efab.setOnClickListener(new View.OnClickListener() {


            String str;
            @Override
            public void onClick(View v) {
                Intent intent;
                SpeechRecognizer mRe;

                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

                mRe = SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
                mRe.setRecognitionListener(listener);
                mRe.startListening(intent);
            }
            private RecognitionListener listener = new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    Toast.makeText(getApplicationContext(), "음성을 듣고 있습니다.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {
                    String message;

                    switch (error) {
                        case SpeechRecognizer.ERROR_AUDIO:
                            message = "오디오 에러";
                            break;
                        case SpeechRecognizer.ERROR_CLIENT:
                            message = "클라이언트 에러";
                            break;
                        case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                            message = "퍼미션 없음";
                            break;
                        case SpeechRecognizer.ERROR_NETWORK:
                            message = "네트워크 에러";
                            break;
                        case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                            message = "네트웍 타임아웃";
                            break;
                        case SpeechRecognizer.ERROR_NO_MATCH:
                            message = "찾을 수 없음";
                            break;
                        case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                            message = "RECOGNIZER가 바쁨";
                            break;
                        case SpeechRecognizer.ERROR_SERVER:
                            message = "서버가 이상함";
                            break;
                        case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                            message = "말하는 시간초과";
                            break;
                        default:
                            message = "알 수 없는 오류임";
                            break;
                    }

                    Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResults(Bundle results) {
                    // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
                    ArrayList<String> matches =
                            results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                    for (int i = 0; i < matches.size(); i++) {
                        str = matches.get(i);
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("대사", str);
                        startActivity(intent);
                    }
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            };
        });
    }
}
