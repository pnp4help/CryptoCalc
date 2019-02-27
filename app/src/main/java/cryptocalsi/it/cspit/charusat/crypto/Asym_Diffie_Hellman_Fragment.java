package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

public class Asym_Diffie_Hellman_Fragment extends Fragment {

    EditText prime_number_1,prime_number_2,secret_SENDER,secret_RECEIVER;
    TextView print;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Diffie Hellman Cipher");
        return inflater.inflate(R.layout.asym_diffie_hellman,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        prime_number_1=(EditText)view.findViewById(R.id.primenumber_input);
        prime_number_2=(EditText)view.findViewById(R.id.primitive_root_input);
        secret_SENDER=(EditText)view.findViewById(R.id.value_x_input);
        secret_RECEIVER=(EditText)view.findViewById(R.id.value_y_input);
        print=(TextView)view.findViewById(R.id.output);

        Button key=(Button)view.findViewById(R.id.button_key);
        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(prime_number_1.getText().toString().equals("")||prime_number_2.getText().toString().equals("")
                        ||secret_SENDER.getText().toString().equals("")
                        ||secret_RECEIVER.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();

                }

                else {
                    BigInteger p=BigInteger.valueOf(Long.valueOf(prime_number_1.getText().toString()));
                    double q=Double.valueOf(prime_number_2.getText().toString());
                    double a=Double.valueOf(secret_SENDER.getText().toString());
                    double b=Double.valueOf(secret_RECEIVER.getText().toString());
                    BigInteger r,s,t,u;
                    r=power(q,a,p);
                    s=power(q,b,p);
                    t=power(s.doubleValue(),a,p);
                    u=power(r.doubleValue(),b,p);
                    if(t.equals(u)){
                        print.setText("Key Exchanged Successfully \nKey : "+String.valueOf(t));
                    }
                    else
                        print.setText("Key Exchange Fails");


                }

            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    private BigInteger power(double a,double b,BigInteger c){
        BigInteger i,j;
        i=BigInteger.valueOf((Double.valueOf(Math.pow(a, b))).longValue());
        j=i.mod(c);
        return j;
    }
}