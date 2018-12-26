package cryptocalsi.it.cspit.charusat.crypto;

import android.annotation.TargetApi;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;

import static android.support.v4.os.LocaleListCompat.create;

public class Oth_chinese_remainder_fragment extends Fragment {
    EditText n1,n2,n3,r1,r2,r3;
    TextView output;
    int[] n={3,5,7};
    int[] a={2,3,2};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Chinese Remainder Theorem");
        return inflater.inflate(R.layout.other_crt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        InputFilterMinMax filter = new InputFilterMinMax("0", "9") {};

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
                if(n1.getText().toString().equals("")||n2.getText().toString().equals("")||n3.getText().toString().equals("")
                        ||r1.getText().toString().equals("")||r2.getText().toString().equals("")||r3.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    n[0]=Integer.valueOf(n1.getText().toString());
                    n[1]=Integer.valueOf(n2.getText().toString());
                    n[2]=Integer.valueOf(n3.getText().toString());
                    a[0]=Integer.valueOf(r1.getText().toString());
                    a[1]=Integer.valueOf(r2.getText().toString());
                    a[2]=Integer.valueOf(r3.getText().toString());
                    output.setText(String.valueOf(chineseRemainder(n,a)));
                }
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static int chineseRemainder(int[] n, int[] a) {

        int prod = Arrays.stream(n).reduce(1, (i, j) -> i * j);

        int p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }

    private static int mulInv(int a, int b) {
        int b0 = b;
        int x0 = 0;
        int x1 = 1;

        if (b == 1)
            return 1;

        while (a > 1) {
            int q = a / b;
            int amb = a % b;
            a = b;
            b = amb;
            int xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }

        if (x1 < 0)
            x1 += b0;

        return x1;
    }
}
