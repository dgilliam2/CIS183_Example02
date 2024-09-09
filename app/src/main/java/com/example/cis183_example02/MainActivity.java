package com.example.cis183_example02;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etxt_j_usr;
    EditText etxt_j_fname;
    EditText etxt_j_lname;
    EditText etxt_j_email;

    Spinner spn_j_dept;

    Button btn_j_reg;

    ListView lst_j_entries;

    ArrayList<User> list_of_users;

    TextView txt_j_error;
    TextView txt_j_uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etxt_j_usr          = findViewById(R.id.etxt_v_usr);
        etxt_j_fname        = findViewById(R.id.etxt_v_fname);
        etxt_j_lname        = findViewById(R.id.etxt_v_lname);
        etxt_j_email        = findViewById(R.id.etxt_v_email);

        spn_j_dept          = findViewById(R.id.spn_v_dept);

        btn_j_reg           = findViewById(R.id.btn_v_reg);

        lst_j_entries       = findViewById(R.id.lst_v_entries);

        txt_j_error         = findViewById(R.id.txt_v_error);
        txt_j_uname         = findViewById(R.id.txt_v_uname);

        regButtonClick();
        unameKeyListener();
        list_of_users = new ArrayList<User>();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void regButtonClick()
    {
        btn_j_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addUserToList();
                listUsers();
            }
        });
    }

    private void unameKeyListener()
    {
        etxt_j_usr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(validUname(etxt_j_usr.getText().toString()))
                {
                    txt_j_uname.setTextColor(Color.BLACK);
                    txt_j_uname.setText("Username");
                }
                else
                {
                    txt_j_uname.setTextColor(Color.RED);
                    txt_j_uname.setText("Username Invalid");
                };
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private boolean validUname(String u)
    {
        for (User user : list_of_users)
        {
            if(user.getUsername().equals(u))
            {
                return false;
            }
        }
        return true;
    }

    private void addUserToList()
    {
        if (!etxt_j_usr.getText().toString().isEmpty()
                && !etxt_j_fname.getText().toString().isEmpty()
                && !etxt_j_lname.getText().toString().isEmpty()
                && !etxt_j_email.getText().toString().isEmpty())
        {
            User usr = new User();
            usr.setUsername(etxt_j_usr.getText().toString());
            usr.setFname(etxt_j_fname.getText().toString());
            usr.setLname(etxt_j_lname.getText().toString());
            usr.setEmail(etxt_j_email.getText().toString());
            list_of_users.add(usr);
            txt_j_error.setVisibility(View.INVISIBLE);
            clearTextBoxes();
        }
        else
        {
            txt_j_error.setVisibility(View.VISIBLE);
            Log.d("Error: ", "Form not filled");
        }
    }

    private void clearTextBoxes()
    {
        etxt_j_usr.setText("");
        etxt_j_fname.setText("");
        etxt_j_lname.setText("");
        etxt_j_email.setText("");
    }

    private void listUsers()
    {
        for (User user : list_of_users)
        {
            Log.d("fname", user.getFname());
        }
    }
}