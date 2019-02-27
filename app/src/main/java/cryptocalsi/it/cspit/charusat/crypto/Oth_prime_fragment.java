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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

public class Oth_prime_fragment extends Fragment {

    TextInputLayout til;
    TextView temp;
    EditText num1_et;
    BigInteger num1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Prime Number");
        return inflater.inflate(R.layout.sym_9_10_11,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        temp=(TextView)view.findViewById(R.id.output);
        num1_et=(EditText)view.findViewById(R.id.plaintext_input);
        num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
        til = (TextInputLayout)view.findViewById(R.id.plaintext_input_layout);
        til.setHint("Enter Number");
        Button prime=(Button)view.findViewById(R.id.button_decryption);
        prime.setVisibility(View.GONE);
        prime=(Button)view.findViewById(R.id.button_encryption);
        prime.setText("Is Prime ?");
        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num1_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = new BigInteger(num1_et.getText().toString());
                    BigInteger one = new BigInteger(String.valueOf((1)));
                    if(num1.compareTo(one) == 0 || num1.compareTo(one) == -1)
                        temp.setText("neither composite nor prime");
                    else{
                        if(num1.isProbablePrime(100))
                            temp.setText("Prime Number");
                        else
                            temp.setText("Composite Number");
                    }
                }
            }
        });

    }
    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
