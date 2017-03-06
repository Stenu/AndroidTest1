package com.example.sami.helloprojekti;


import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;
import java.math.*;
import android.content.Context;



public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.sami.helloprojekti.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //   EditText editText = (EditText) findViewById(R.id.edit_message);
        //   String message = editText.getText().toString();
        //    intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void laske(View view) {


        // tämä koodi piilottaa näppäimistön
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);



        // haetaan kaikki syotteet tekstimuodossa
        EditText kuivaainePitoisuus = (EditText) findViewById(R.id.edit_messageKuivaainePitoisuus);
        EditText kompApitoisuus = (EditText) findViewById(R.id.edit_messageKompA);
        EditText kompBpitoisuus = (EditText) findViewById(R.id.edit_messageKompB);
        EditText neliota = (EditText) findViewById(R.id.edit_messageNeliot);
        EditText kuivakalvo = (EditText) findViewById(R.id.edit_messageDFT);

        // muunnetaan syötteet lukuarvoiksi
        double pintaAla = Double.parseDouble(neliota.getText().toString());
        double kApitoisuus = Double.parseDouble(kuivaainePitoisuus.getText().toString()) / 100; // prosenttikertoimeksi
        Double kompA = Double.parseDouble(kompApitoisuus.getText().toString());
        Double kompB = Double.parseDouble(kompBpitoisuus.getText().toString());
        double dft = Double.parseDouble(kuivakalvo.getText().toString());

        // ratkaise spinner ja kulutuskerroin tässä
        double kulutusKerroin = 1.6;

        // ratkaistaan tiedot
        double maara = ((kulutusKerroin * pintaAla * dft / 1000) / kApitoisuus);
        double Amaara = maara * (kompA / (kompA + kompB));
        double Bmaara = maara * (kompB / (kompA + kompB));
        double markakalvonPaksuus = dft / kApitoisuus;
        double nelioKulutus = maara / pintaAla;

        // pyöristetään luvut ennen tulostamista näytölle
        maara = round(maara, 1);
        Amaara = round(Amaara, 1);
        Bmaara = round(Bmaara, 1);
        kompA = round(kompA,0);
        kompB = round(kompB,0);
        markakalvonPaksuus = round(markakalvonPaksuus, 0);
        nelioKulutus = round(nelioKulutus, 2);




        // haetaan kaikkien tulostettavien tietojen paikat
        TextView menekki = (TextView) findViewById(R.id.textViewMenekki);
        // tulee kaksiriviseksi
        TextView sekoitusSuhde = (TextView) findViewById(R.id.textViewSuhde);
        TextView markaKalvo = (TextView) findViewById(R.id.textViewMarkakalvo);

        // kirjoitetaan tiedot oikeisiin paikkoihin
        menekki.setText("Arvioitu maalimenekki " + maara + " litraa. ");
        sekoitusSuhde.setText("Sekoitussuhde A:B " + kompA.intValue() + ":" + kompB.intValue() + "\nKomponentti A " + Amaara + " l\nKomponentti B " + Bmaara + " l");
        markaKalvo.setText("Märkäkalvon paksuus on " + markakalvonPaksuus + " um.\nNeliölle maalia kuluu " + nelioKulutus + " litraa.");




    }

    //pyöristysfunktio stack overflowsta
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    //tyhjennysfunktio
    public void tyhjenna(View view) {

        // haetaan kaikkien tulostettavien tietojen paikat
        TextView menekki = (TextView) findViewById(R.id.textViewMenekki);
        TextView sekoitusSuhde = (TextView) findViewById(R.id.textViewSuhde);
        TextView markaKalvo = (TextView) findViewById(R.id.textViewMarkakalvo);

        menekki.setText("");
        sekoitusSuhde.setText("");
        markaKalvo.setText("");

    }




}
