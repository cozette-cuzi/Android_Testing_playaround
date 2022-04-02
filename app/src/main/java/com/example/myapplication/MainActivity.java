package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button b = (Button) findViewById(R.id.button);
        final EditText stringInput = (EditText) findViewById(R.id.editText_string);
        final EditText numberInput = (EditText) findViewById(R.id.editText_number);
        final TextView output = (TextView) findViewById(R.id.textView_output);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = stringInput.getText().toString();
                    int n = Integer.parseInt(numberInput.getText().toString());

                    String result = new CropString(n, s).calculate();
                    output.setText(result);
                } catch (NumberFormatException e) {
                    output.setText(R.string.not_a_number_err);
                } catch (Exception e) {
                    output.setText(e.getMessage());
                }
            }
        });
    }
}