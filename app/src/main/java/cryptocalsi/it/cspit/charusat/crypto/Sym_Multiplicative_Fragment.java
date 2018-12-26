package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

public class Sym_Multiplicative_Fragment extends Fragment {

    public String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private String copy;
    EditText input_et,key_et;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Multiplicative Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textView=view.findViewById(R.id.output);
        input_et=view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        key_et=view.findViewById(R.id.key_input);
        InputFilterMinMax filter = new InputFilterMinMax("0", "26") {};
        key_et.setFilters(new InputFilter[]{filter});
        Button encryption = view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_string=input_et.getText().toString();
                String key_string=key_et.getText().toString();

                if(input_string.equals("")||key_string.equals(""))
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                else {
                    int key=Integer.valueOf(key_string);
                    input_string=input_string.toLowerCase();
                    String ciphertext="";
                    for(int i=0;i<input_string.length();i++){
                        int charposition = ALPHABET.indexOf(input_string.charAt(i));
                        int keyVal=(key*charposition)%26;
                        char replaceVal=ALPHABET.charAt(keyVal);
                        ciphertext+=replaceVal;
                    }
                    copy=ciphertext;
                    textView.setText("CipherText : "+String.valueOf(ciphertext));
                }
            }
        });
        Button decryption = view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_string=input_et.getText().toString();
                String key_string=key_et.getText().toString();

                if(input_string.equals("")||key_string.equals(""))
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                else {

                    long l= Long.parseLong(key_string);
                    BigInteger bi=BigInteger.valueOf(l);
                    input_string=input_string.toLowerCase();
                    String ciphertext="";
                    int x=Integer.valueOf(key_string);
                    int r,g,b;
                    g=(x>26)?x:26;
                    b=(x<26)?x:26;

                    r=b;
                    while ((g%b)!=0){
                        r=g%b;
                        g=b;
                        b=r;
                    }
                    if (r==1){
                        BigInteger a=BigInteger.valueOf(l).modInverse(BigInteger.valueOf(26));
                        int key=a.intValue();
                        for(int i=0;i<input_string.length();i++){
                            int charposition=ALPHABET.indexOf(input_string.charAt(i));
                            int keyVal=(key*charposition)%26;
                            char replaceVal=ALPHABET.charAt(keyVal);
                            ciphertext+=replaceVal;
                        }
                        copy=ciphertext;
                        textView.setText("CipherText : "+ciphertext.toString());
                    }
                    else {
                        textView.setText("Enter another key. Multiplicative inverse not possible");

                    }
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

class InputFilterMinMax implements InputFilter {

    private int min, max;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}