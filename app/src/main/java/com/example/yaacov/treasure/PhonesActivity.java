package com.example.yaacov.treasure;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Vector;

public class PhonesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Button btnStart;
    private int numberOfPlayers=1;
    private LinearLayout linearPhones;
    private SharedPreferences.Editor editor;
    private Spinner numOfPartSpinner,citySpinner;
    private Vector<EditText> vectorEditText=new Vector<>();
    private final String MY_PREFS_NAME="prfes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phones);
        editor= getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE).edit();
        linearPhones = findViewById(R.id.linearPhones);
        numOfPartSpinner = findViewById(R.id.numOfPartSpinner);
        numOfPartSpinner.setOnItemSelectedListener(this);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOfPartSpinner.setAdapter(adapter);
        citySpinner =  findViewById(R.id.citySpinner);
        citySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterCities = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapterCities);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        linearPhones.removeAllViews();
        if(parent.getId()==R.id.numOfPartSpinner) {
            editor.putInt("numOfpart", position+1);
            editor.commit();
            numberOfPlayers=position;
            for (int i = 0; i < position; i++) {
                LinearLayout phoneLayout = new LinearLayout(this);
                phoneLayout.setOrientation(LinearLayout.HORIZONTAL);
                phoneLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200));
                EditText phone = new EditText(this);
                phone.setInputType(InputType.TYPE_CLASS_PHONE);
                vectorEditText.add(phone);
                phone.setId(1000+i);
                phone.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200));
                TextView phoneLabel = new TextView(this);
                phoneLabel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 200));
                phoneLabel.setText((i + 1) + "");
                phoneLayout.addView(phoneLabel);
                phoneLayout.addView(phone);
                linearPhones.addView(phoneLayout);
            }
        }
        else
        {
            editor.putInt("CityId", position+1);
            editor.commit();
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }

    @Override
    public void onClick(View v) {
        for (int i=0; i<numberOfPlayers;i++) {
            editor.putString("number " + i, vectorEditText.elementAt(i).getText().toString());
            editor.commit();
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}