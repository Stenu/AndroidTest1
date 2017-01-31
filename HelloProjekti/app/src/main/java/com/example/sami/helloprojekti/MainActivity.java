package com.example.sami.helloprojekti;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.sami.helloprojekti.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void laske(View view) {

        EditText arvoEka = (EditText) findViewById(R.id.edit_message);
        EditText arvoToka = (EditText) findViewById(R.id.edit_message1);

        int luku1 = Integer.parseInt(arvoEka.getText().toString());
        int luku2 = Integer.parseInt(arvoToka.getText().toString());
        int summa = luku1+luku2;

        TextView summaArvo = (TextView) findViewById(R.id.textView2);
        summaArvo.setText(""+summa);

    }

}
