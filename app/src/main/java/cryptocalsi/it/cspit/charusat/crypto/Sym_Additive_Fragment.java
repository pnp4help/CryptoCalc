package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Sym_Additive_Fragment extends Fragment {


    private String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private String copy;
    EditText input_et,key_et;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Additive Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textView=view.findViewById(R.id.output);
        input_et=view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        key_et=view.findViewById(R.id.key_input);

        Button encryption=view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_string=input_et.getText().toString();
                String key_string=key_et.getText().toString();

                if(input_string.equals("")||key_string.equals(""))
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                else {

                    int key=Integer.valueOf(key_string);
                    String temp=input_string;
                    input_string=input_string.toLowerCase();
                    String ciphertext="";
                    for(int i=0;i<input_string.length();i++){
                        int charPosition=ALPHABET.indexOf(input_string.charAt(i));
                        int keyVal=(key + charPosition)%26;
                        char replaceVal=ALPHABET.charAt(keyVal);
                        ciphertext+=replaceVal;
                    }

                    char [] ci=ciphertext.toCharArray();
                    for (int i=0;i<input_string.length();i++){
                        if(Character.isUpperCase(temp.charAt(i)))
                            ci[i]=Character.toUpperCase(ci[i]);
                    }
                    copy=String.valueOf(ci);
                    textView.setText("CipherText : "+String.valueOf(ci));

                }

            }
        });

        Button decryption=view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_string=input_et.getText().toString();
                String key_string=key_et.getText().toString();

                if(input_string.equals("")||key_string.equals(""))
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                else {
                    int key=Integer.valueOf(key_string);
                    String check=input_string;
                    input_string=input_string.toLowerCase();
                    String temp="";
                    for (int i=0;i<input_string.length();i++){
                        int charPosition = ALPHABET.indexOf(input_string.charAt(i));
                        int keyVal=(charPosition-key)%26;
                        if (keyVal<0)
                            keyVal=ALPHABET.length()+keyVal;
                        char replaceVal=ALPHABET.charAt(keyVal);
                        temp+=replaceVal;
                    }
                    char [] ci=temp.toCharArray();
                    for (int i=0;i<input_string.length();i++){
                        if (Character.isUpperCase(check.charAt(i)))
                            ci[i]=Character.toUpperCase(ci[i]);
                    }
                    temp=String.valueOf(ci);
                    copy=temp;
                    textView.setText("PlainText : "+temp);
                }
            }
        });

        ImageView swap=view.findViewById(R.id.swap);
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