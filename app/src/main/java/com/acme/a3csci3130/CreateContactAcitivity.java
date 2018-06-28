package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField,numberFile,addressFile;
    private MyApplicationData appState;
    private Spinner proSp,priSp;
    private  String pro,pri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberFile=(EditText) findViewById(R.id.number_editText2);
        addressFile=(EditText) findViewById(R.id.address_editText3);
        proSp=(Spinner) findViewById(R.id.pro_spinner2);
        priSp=(Spinner) findViewById(R.id.pri_spinner1);

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String number=numberFile.getText().toString();
        String address=addressFile.getText().toString();
        pro=proSp.getSelectedItem().toString();
        pri=priSp.getSelectedItem().toString();
        if(formatting(name,number,address)) {
            Contact person = new Contact(personID, name, email, number, pri, address, pro);

            appState.firebaseReference.child(personID).setValue(person);

        finish();
        }else {

            Toast.makeText(this,"formatting issue!",Toast.LENGTH_LONG).show();
        }

    }

    public boolean formatting(String name,String number,String addr)
    {
        if (name.length()<2||name.length()>50)
        {
            return false;
        }
        if (number.length()!=9)
        {
            return false;
        }
        if (addr.length()>50)
        {
            return false;
        }
        return true;
    }

}
