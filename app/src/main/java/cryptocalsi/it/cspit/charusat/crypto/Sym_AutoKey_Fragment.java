package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Sym_AutoKey_Fragment extends Fragment {

    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    TextInputLayout til;
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
        key_et=(EditText)view.findViewById(R.id.key_input);
        key_et.setInputType(InputType.TYPE_CLASS_TEXT);
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        til = (TextInputLayout)view.findViewById(R.id.key_input_layout);
        til.setHint("Enter AutoKey Value");
        output_tv=(TextView)view.findViewById(R.id.output);

        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(key_et.getText().toString().equals("")||input.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!key_et.getText().toString().matches("[A-Z]") && !key_et.getText().toString().matches("[0-9]+")){
                    output_tv.setText("Invallid key");
                }
                else if(!input.getText().toString().matches("[A-Z]+"))
                    output_tv.setText("only alphabets are allowed in Plain text");
                else {
                    String plaintext=input.getText().toString();
                    String key=key_et.getText().toString();

                    if (key.matches("[-+]?\\d*\\.?\\d+"))
                        key = "" + alpha.charAt(Integer.parseInt(key) % 26);

                    int len = plaintext.length();
                    String subkey = key + plaintext.substring(0,plaintext.length()-1);
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
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(key_et.getText().toString().equals("")||input.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!key_et.getText().toString().matches("[A-Z]") && !key_et.getText().toString().matches("[0-9]+")){
                    output_tv.setText("Invallid key");
                }
                else if(!input.getText().toString().matches("[A-Z]+"))
                    output_tv.setText("only alphabets are allowed in Cipher text");
                else {

                    String plaintext=input.getText().toString();
                    String key=key_et.getText().toString();

                    if (key.matches("[-+]?\\d*\\.?\\d+"))
                        key = "" + alpha.charAt(Integer.parseInt(key) % 26);
                    int len = plaintext.length();
                    String current = key;
                    String sb ="";

                    for(int x=0;x<len;x++){
                        int get1 = alpha.indexOf(plaintext.charAt(x));
                        int get2 = alpha.indexOf(current.charAt(x));

                        int total = (get1 - get2 + 26) % 26;
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
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                input.setText(copy,TextView.BufferType.EDITABLE);
            }
        });
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

}

