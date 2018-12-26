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

public class Sym_AutoKey_Fragment extends Fragment {

    private static String alpha = "abcdefghijklmnopqrstuvwxyz";
    EditText input,key_et;
    TextView output_tv;
    String copy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("AutoKey Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        input=(EditText)view.findViewById(R.id.plaintext_input);
        input.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        TextView setKeyTitle=(TextView)view.findViewById(R.id.key_label);
        setKeyTitle.setText("AutoKey : ");
        key_et=(EditText)view.findViewById(R.id.key_input);
        key_et.setHint("Enter AutoKey Value");
        key_et.setInputType(InputType.TYPE_CLASS_TEXT);
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        output_tv=(TextView)view.findViewById(R.id.output);

        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key_et.getText().toString().equals("")||input.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    String plaintext=input.getText().toString();
                    String key=key_et.getText().toString();

                    if (key.matches("[-+]?\\d*\\.?\\d+"))
                        key = "" + alpha.charAt(Integer.parseInt(key));
                    int len = plaintext.length();
                    String subkey = key + plaintext;
                    subkey = subkey.substring(0, subkey.length() - key.length());
                    String sb = "";
                    for (int x = 0; x < len; x++) {
                        int get1 = alpha.indexOf(plaintext.charAt(x));
                        int get2 = alpha.indexOf(subkey.charAt(x));
                        int total = (get1 + get2) % 26;
                        sb += alpha.charAt(total);
                    }
                    copy=sb;
                    output_tv.setText("CipherText : " + copy);


                }
            }
        });

        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key_et.getText().toString().equals("")||input.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {

                    String plaintext=input.getText().toString();
                    String key=key_et.getText().toString();

                    if (key.matches("[-+]?\\d*\\.?\\d+"))
                        key = "" + alpha.charAt(Integer.parseInt(key));
                    int len = plaintext.length();
                    String current = key;
                    String sb ="";

                    for(int x=0;x<len;x++){
                        int get1 = alpha.indexOf(plaintext.charAt(x));
                        int get2 = alpha.indexOf(current.charAt(x));

                        int total = (get1 - get2)%26;
                        total = (total<0)? total + 26 : total;
                        sb += alpha.charAt(total);

                        current += alpha.charAt(total);
                    }
                    copy=sb;
                    output_tv.setText("PlainText : "+copy);

                }
            }
        });

        ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(copy,TextView.BufferType.EDITABLE);
            }
        });
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

}

