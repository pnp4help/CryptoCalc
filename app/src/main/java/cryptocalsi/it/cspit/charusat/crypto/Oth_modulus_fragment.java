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
import java.math.*;

import java.util.Objects;

public class Oth_modulus_fragment extends Fragment {


    TextView temp;
    EditText num1_et,num2_et;
    BigInteger n1,n2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Modulus");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageView imageView=(ImageView)view.findViewById(R.id.swap);
        imageView.setVisibility(View.GONE);
        temp=(TextView)view.findViewById(R.id.plaintext_label);
        temp.setText("Number :");
        temp=(TextView)view.findViewById(R.id.key_label);
        temp.setText("Mod :");
        temp=(TextView)view.findViewById(R.id.output);
        num1_et=(EditText)view.findViewById(R.id.plaintext_input);
        num1_et.setHint("Enter Number");
        num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
        num2_et=(EditText)view.findViewById(R.id.key_input);
        num2_et.setHint("Enter Mod");
        Button mod=(Button)view.findViewById(R.id.button_decryption);
        mod.setVisibility(View.GONE);
        mod=(Button)view.findViewById(R.id.button_encryption);
        mod.setText("Modulus");
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num1_et.getText().toString().equals("")||num2_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    n1 = new BigInteger(num1_et.getText().toString());
                    n2 = new BigInteger(num2_et.getText().toString());

                    temp.setText(""+n1.mod(n2));
                }
            }
        });
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
