package com.example.assignment2_6209650479;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_1 = (Button) findViewById(R.id.button);
        final ImageButton btn_2 = (ImageButton) findViewById(R.id.imageButton7);

        EditText edit_text1 = (EditText)findViewById(R.id.editTextNumberDecimal2);
        edit_text1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});

        EditText edit_text2 = (EditText)findViewById(R.id.editTextNumberDecimal3);
        edit_text2.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4, 2)});

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history();
            }
        });

    }

    DecimalFormat formatter = new DecimalFormat("#,###.##");

    public void cal(){
        final EditText sales = (EditText)findViewById(R.id.editTextNumberDecimal2);
        String text_1 = sales.getText().toString();

        final EditText percent = (EditText)findViewById(R.id.editTextNumberDecimal3);
        String text_2 = percent.getText().toString();

        final EditText share_1 = (EditText)findViewById(R.id.editTextNumberDecimal4);
        final EditText share_2 = (EditText)findViewById(R.id.editTextNumberDecimal5);


        if(!text_1.isEmpty() && !text_2.isEmpty()) {
            Double v_1 = Double.parseDouble(text_1);
            Double v_2 = Double.parseDouble(text_2);
            Double sum_1 = (v_2/100)*v_1;
            Double sum_2 = v_1 - sum_1;

            share_1.setText(formatter.format(sum_2));
            share_2.setText(formatter.format(sum_1));



            history_save(text_1,text_2,sum_2.toString(),sum_1.toString());
        }


    }

    public void history_save(String sales, String percent,String shareC, String share){
        String filename1 = "sales_log.txt";
        String filename2 = "percent_log.txt";
        String filename3 = "shareC_log.txt";
        String filename4 = "share_log.txt";
        FileOutputStream outputStream;
        sales = sales + " ";
        percent = percent + " ";
        shareC = shareC + " ";
        share = share;


        try {
            outputStream = openFileOutput(filename1,MODE_APPEND);
            outputStream.write(sales.getBytes());
            outputStream.write(10);
            outputStream = openFileOutput(filename2,MODE_APPEND);
            outputStream.write(percent.getBytes());
            outputStream.write(10);
            outputStream = openFileOutput(filename3,MODE_APPEND);
            outputStream.write(shareC.getBytes());
            outputStream.write(10);
            outputStream = openFileOutput(filename4,MODE_APPEND);
            outputStream.write(share.getBytes());
            outputStream.write(10);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void history(){
        Intent intent = new Intent(MainActivity.this,History.class);
        startActivity(intent);
    }

}

class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;
    DecimalDigitsInputFilter(int digits, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digits - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) +
                "})?)||(\\.)?");
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}


