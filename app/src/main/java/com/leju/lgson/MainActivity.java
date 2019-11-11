package com.leju.lgson;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.zxl.lgson.LGson;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mResult,mResult1;
    private String json = "{\n" +
            "    \"name\": \"zs\",\n" +
            "    \"age\": 13,\n" +
            "    \"classes\": [\n" +
            "        \"chinese\",\n" +
            "        \"english\",\n" +
            "        \"french\"\n" +
            "    ]\n" +
            "}";

    private String json1 = "{\n" +
            "    \"name\": \"zs\",\n" +
            "    \"age\": 13,\n" +
            "    \"classes\": null\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = findViewById(R.id.result);
        mResult1 = findViewById(R.id.result2);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = LGson.parseDataByGson(json,Student.class);
                mResult.setText("Student:"+student);

                Student student1 = LGson.parseDataByGson(json1,Student.class);
                mResult1.setText("Student:"+student1);
            }
        });
        JSONArray array = new JSONArray();
        List<Student> list = LGson.parseTypeTokenDataByGson(array, new TypeToken<List<Student>>(){});
    }
}
