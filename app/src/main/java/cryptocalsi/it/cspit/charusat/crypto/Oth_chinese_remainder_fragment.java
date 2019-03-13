package cryptocalsi.it.cspit.charusat.crypto;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.support.v4.os.LocaleListCompat.create;

public class Oth_chinese_remainder_fragment extends Fragment {
    EditText n1,n2,n3,r1,r2,r3;
    TextView output;
    long[] n = {3,5,7};
    long[] a = {2,3,2};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Chinese Remainder Theorem");
        return inflater.inflate(R.layout.other_crt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        InputFilterMinMax filter = new InputFilterMinMax("0", "999") {};

        n1=(EditText)view.findViewById(R.id.number_1_input);
        n1.setFilters(new InputFilter[]{filter});

        n2=(EditText)view.findViewById(R.id.number_2_input);
        n2.setFilters(new InputFilter[]{filter});

        n3=(EditText)view.findViewById(R.id.number_3_input);
        n3.setFilters(new InputFilter[]{filter});

        r1=(EditText)view.findViewById(R.id.remainder_1_input);
        r1.setFilters(new InputFilter[]{filter});

        r2=(EditText)view.findViewById(R.id.remainder_2_input);
        r2.setFilters(new InputFilter[]{filter});

        r3=(EditText)view.findViewById(R.id.remainder_3_input);
        r3.setFilters(new InputFilter[]{filter});

        output=(TextView)view.findViewById(R.id.output);
        Button crt=(Button)view.findViewById(R.id.button_crt);
        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(n1.getText().toString().equals("")||n2.getText().toString().equals("")||n3.getText().toString().equals("")
                        ||r1.getText().toString().equals("")||r2.getText().toString().equals("")||r3.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    n[0]=Long.valueOf(n1.getText().toString());
                    n[1]=Long.valueOf(n2.getText().toString());
                    n[2]=Long.valueOf(n3.getText().toString());
                    a[0]=Long.valueOf(r1.getText().toString());
                    a[1]=Long.valueOf(r2.getText().toString());
                    a[2]=Long.valueOf(r3.getText().toString());
                    if((BigInteger.valueOf(n[0]).gcd(BigInteger.valueOf(n[1])).compareTo(BigInteger.valueOf(1)) == 0) &&
                            (BigInteger.valueOf(n[0]).gcd(BigInteger.valueOf(n[2])).compareTo(BigInteger.valueOf(1)) == 0) &&
                            (BigInteger.valueOf(n[1]).gcd(BigInteger.valueOf(n[2])).compareTo(BigInteger.valueOf(1)) == 0)){
                        output.setText(String.valueOf(chineseRemainder(n,a)));
                    }
                    else{
                        output.setText("Number1, Number2 & Number3 must be coprime");
                    }
                }
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    public static long inv(long a, long m) {
        long m0 = m, t, q;
        long x0 = 0, x1 = 1;
        if (m == 1)
            return 0;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    public static long chineseRemainder(long num[], long rem[]) {
        // Compute product of all numbers
        int k = num.length;
        long prod = 1;
        for (int i = 0; i < k; i++)
            prod *= num[i];

        // Initialize result
        long result = 0;

        // Apply above formula
        for (int i = 0; i < k; i++) {
            long pp = prod / num[i];
            result += rem[i] * inv(pp, num[i]) * pp;
        }

        return result % prod;
    }
}
