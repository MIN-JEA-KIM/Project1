package com.example.trial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder() //Retrofit.Build를 통해 Retrofit 인스턴스 생성
                .baseUrl("http://c851-218-153-113-20.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create()) //json을 변환해줄 gson변환기 등록
                .build();

        RetrofitService service1 = retrofit.create(RetrofitService.class); //Retrofit 인스턴스로 인터페이스 객체 구현

        Call<PostResult> call = service1.getPosts("59"); //사용할 메서드 선언

        call.enqueue(new Callback<PostResult>() { //enqueue로 비동기 통신실행. 통신 완료 후 이번트 처리 위한 callback 리스너 등록
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) { //oneResponse통신 성공시 Callback(메인스레드에서 작업하는 부분(UI작업가능))

                TextView textView = (TextView)findViewById(R.id.text1);
                String resultText = "[NULL]";

                if(response.isSuccessful()){ //onResponse.isSuccessful()로 정상 2xx인지 확인
                    PostResult result = response.body();
                    Log.d("", "onResponse: 성공, 결과\n"+result.toString());

                    resultText = result.toString();
                    textView.setText(resultText);

                } else {
                    Log.d("","onRepsonse: 실패");//onResponse가 무조건 성공 응답이 아니기에 확인 필요(응답 코드 3xx, 4xx onResponse 호출.)
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {   //onFailure 통신 실패시 Callback(예외 발생, 인터넷 끊김 등 시스템적인 이유 실패)
                Log.d("","onFailure: "  + t.getMessage());
            }
        });




    }
}