package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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

import java.math.BigInteger;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Oth_gcd_euclidian_fragment extends Fragment {

    TextView temp;
    TextInputLayout til;
    EditText num1_et,num2_et;
    BigInteger num1,num2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("GCD Euclidian");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView=(ImageView)view.findViewById(R.id.swap);
        imageView.setVisibility(View.GONE);
        temp=(TextView)view.findViewById(R.id.output);
        num1_et=(EditText)view.findViewById(R.id.plaintext_input);
        num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
        til = (TextInputLayout)view.findViewById(R.id.plaintext_input_layout);
        til.setHint("Enter Number 1");
        num2_et=(EditText)view.findViewById(R.id.key_input);
        til = (TextInputLayout)view.findViewById(R.id.key_input_layout);
        til.setHint("Enter Number 2");
        Button gcd=(Button)view.findViewById(R.id.button_decryption);
        gcd.setVisibility(View.GONE);
        gcd=(Button)view.findViewById(R.id.button_encryption);
        gcd.setText("GCD");
        gcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(num1_et.getText().toString().equals("")||num2_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = new BigInteger(num1_et.getText().toString());
                    num2 = new BigInteger(num2_et.getText().toString());
                    temp.setText("GCD of two numbers : "+ num1.gcd(num2));
                }
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}