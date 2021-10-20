package com.example.assignment2_6209650479;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History extends AppCompatActivity {

    DecimalFormat formatter = new DecimalFormat("#,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TableLayout table = (TableLayout) findViewById(R.id.mytable);

        List<String> data1 = readFileData("sales_log.txt");
        List<String> data2 = readFileData("percent_log.txt");
        List<String> data3 = readFileData("shareC_log.txt");
        List<String> data4 = readFileData("share_log.txt");


        int maxDataSetSize = Math.max(data1.size(), Math.max(data2.size(), Math.max(data3.size(),data4.size())));

        for (int i = 0; i < maxDataSetSize; i++) {
            String Value_1 = data1.size() > i ? data1.get(i) : null;
            String Value_2 = data2.size() > i ? data2.get(i) : null;
            String Value_3 = data3.size() > i ? data3.get(i) : null;
            String Value_4 = data4.size() > i ? data4.get(i) : null;

            View r_view = getLayoutInflater().inflate(R.layout.history_row, null);

            fillView(r_view, Value_1, Value_2, Value_3 ,Value_4);

            table.addView(r_view);
        }

    }

    private List<String> readFileData(String s) {
        List<String> val = new ArrayList<>();

        try {
            BufferedReader fileBufferedReader = new BufferedReader(new InputStreamReader(openFileInput(s)));
            String value;
            while ((value = fileBufferedReader.readLine()) != null) {
                value = formatter.format(Double.parseDouble(value));
                val.add(value);
            }
            fileBufferedReader.close();
        } catch (IOException ignore) {
        }

        Collections.reverse(val);
        return val;
    }

    private void fillView(View view, String Value_1 , String Value_2 , String Value_3,String Value_4) {
        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);
        TextView textView4 = (TextView) view.findViewById(R.id.textView4);

        textView1.setText(Value_1);
        textView2.setText(Value_2);
        textView3.setText(Value_3);
        textView4.setText(Value_4);
    }

}
