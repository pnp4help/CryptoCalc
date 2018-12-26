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

public class Oth_prime_fragment extends Fragment {

    TextView temp;
    EditText num1_et;
    long num1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Prime Number");
        return inflater.inflate(R.layout.sym_9_10_11,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        temp=(TextView)view.findViewById(R.id.plaintext_label);
        temp.setText("Number :");
        temp=(TextView)view.findViewById(R.id.output);
        num1_et=(EditText)view.findViewById(R.id.plaintext_input);
        num1_et.setHint("Enter Number");
        num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                    num1=Long.valueOf(num1_et.getText().toString());
                    if(num1==1)
                        temp.setText("1 is neither Prime or Composite number");
                    else {
                        boolean check=isPrime(num1);
                        if(check)
                            temp.setText("Prime Number");
                        else
                            temp.setText("Composite Number");
                    }

                }
            }
        });

    }
    boolean isPrime(long n) {
        //check if n is a multiple of 2
        if (n % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
