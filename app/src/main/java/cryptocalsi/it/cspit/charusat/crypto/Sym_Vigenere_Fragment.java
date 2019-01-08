package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Sym_Vigenere_Fragment extends Fragment {

    EditText input_et,key_et;
    TextView output_tv;
    String copy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Vigenere Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        input_et=view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        key_et=view.findViewById(R.id.key_input);
        key_et.setInputType(InputType.TYPE_CLASS_TEXT);
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        output_tv=(TextView)view.findViewById(R.id.output);
        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();

                    String res = "";
                    input_string = input_string.toUpperCase();
                    for (int i = 0, j = 0; i < input_string.length(); i++)
                    {
                        char c = input_string.charAt(i);
                        if (c < 'A' || c > 'Z')
                            continue;
                        res += (char) ((c + key_string.charAt(j) - 2 * 'A') % 26 + 'A');
                        j = ++j % key_string.length();
                    }
                    copy=res;
                    output_tv.setText("CipherText : "+copy);

                }

            }
        });
        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();

                    String res = "";
                    input_string = input_string.toUpperCase();
                    for (int i = 0, j = 0; i < input_string.length(); i++)
                    {
                        char c = input_string.charAt(i);
                        if (c < 'A' || c > 'Z')
                            continue;
                        res += (char) ((c - key_string.charAt(j) + 26) % 26 + 'A');
                        j = ++j % key_string.length();
                    }
                    copy=res;
                    output_tv.setText("PlainText : "+copy);

                }
            }
        });

        ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_et.setText(copy,TextView.BufferType.EDITABLE);
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}