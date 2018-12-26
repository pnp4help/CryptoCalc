package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.Objects;

public class Oth_gcd_euclidian_fragment extends Fragment {

    TextView temp;
    EditText num1_et,num2_et;
    long num1,num2;
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
        temp=(TextView)view.findViewById(R.id.plaintext_label);
        temp.setText("Number 1 :");
        temp=(TextView)view.findViewById(R.id.key_label);
        temp.setText("Number 2 :");
        temp=(TextView)view.findViewById(R.id.output);
        num1_et=(EditText)view.findViewById(R.id.plaintext_input);
        num1_et.setHint("Enter Number 1");
        num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
        num2_et=(EditText)view.findViewById(R.id.key_input);
        num2_et.setHint("Enter Number 2");
        Button gcd=(Button)view.findViewById(R.id.button_decryption);
        gcd.setVisibility(View.GONE);
        gcd=(Button)view.findViewById(R.id.button_encryption);
        gcd.setText("GCD");
        gcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num1_et.getText().toString().equals("")||num2_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    num1=Long.valueOf(num1_et.getText().toString());
                    num2=Long.valueOf(num2_et.getText().toString());
                    temp.setText("GCD of two numbers : "+String.valueOf(gcd(num1,num2)));

                }
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    public long gcd(long p, long q)
    {
        if (p % q == 0)
            return q;
        return gcd(q, p % q);
    }
}