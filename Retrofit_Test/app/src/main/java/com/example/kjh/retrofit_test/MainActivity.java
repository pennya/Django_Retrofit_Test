package com.example.kjh.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //public final String TAG = "KJH";
    private NetworkService networkService;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        bt4 = (Button)findViewById(R.id.bt4);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET
                Call<List<Version>> versionCall = networkService.get_version();
                versionCall.enqueue(new Callback<List<Version>>() {
                    @Override
                    public void onResponse(Call<List<Version>> call, Response<List<Version>> response) {
                        if(response.isSuccessful()) {
                            List<Version> versionList = response.body();

                            String version_txt = "";
                            for(Version version : versionList){
                                version_txt += version.getVersion() + "\n";
                            }

                            tv1.setText(version_txt);
                        } else {
                            int StatusCode = response.code();
                            Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Version>> call, Throwable t) {
                        Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
                    }
                });
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //POST
                Version version = new Version();
                version.setVersion("1.0.0.1");
                Call<Version> postCall = networkService.post_version(version);
                postCall.enqueue(new Callback<Version>() {
                    @Override
                    public void onResponse(Call<Version> call, Response<Version> response) {
                        if( response.isSuccessful()) {
                            tv2.setText("등록");
                        } else {
                            int StatusCode = response.code();
                            Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Version> call, Throwable t) {
                        Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
                    }
                });

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PUT
                Version version = new Version();
                version.setVersion("1.0.0.0.1");
                Call<Version> patchCall = networkService.patch_version(1, version);
                patchCall.enqueue(new Callback<Version>() {
                    @Override
                    public void onResponse(Call<Version> call, Response<Version> response) {
                        if( response.isSuccessful()) {
                            tv3.setText("업데이트");
                        } else {
                            int StatusCode = response.code();
                            Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<Version> call, Throwable t) {
                        Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
                    }
                });
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DELETE
                Call<Version> deleteCall = networkService.delete_version(1);
                deleteCall.enqueue(new Callback<Version>() {
                    @Override
                    public void onResponse(Call<Version> call, Response<Version> response) {
                        if( response.isSuccessful()) {
                            tv4.setText("삭제");
                        } else {
                            int StatusCode = response.code();
                            Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<Version> call, Throwable t) {
                        Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
                    }
                });
            }
        });

        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService("25779917.ngrok.io", 8000);
        networkService = ApplicationController.getInstance().getNetworkService();
    }
}
