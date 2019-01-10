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


public class Sym_Hill_Fragment extends Fragment {
    Boolean flag;
    EditText input_et,key_et;
    TextView output_tv;
    String copy,copy_1;
    int extraChars;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Hill Cipher");
        return inflater.inflate(R.layout.sym_hill,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView setKeyTitle=(TextView)view.findViewById(R.id.key_label);
        setKeyTitle.setText("Matrix Value of Key : ");
        key_et=(EditText)view.findViewById(R.id.key_input);
        key_et.setHint("Enter Key Value");
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        output_tv=(TextView)view.findViewById(R.id.output);
        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    HillCipher obj = new HillCipher();
                    double sq = Math.sqrt(key_string.length());
                    if (sq != (long) sq) {
                        output_tv.setText("Invalid key length!!! Does not form a square matrix...");
                        flag = false;
                    }
                    else
                    {
                        int s = (int) sq;
                        if (obj.check(key_string, s))
                        {
                            copy=obj.divide(input_string, s);
                            copy_1=obj.cofact(obj.keymatrix, s);
                            extraChars = input_string.length()%s;
                            output_tv.setText("CipherText : "+copy+"\nInverseKey : "+copy_1);
                        }
                        else {
                            output_tv.setText("Key Inverse is not possible. Try another key...");
                            flag = false;
                        }
                    }

                }
            }
        });

        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    HillCipher obj = new HillCipher();
                    double sq = Math.sqrt(key_string.length());
                    if (sq != (long) sq) {
                        output_tv.setText("Invalid key length!!! Does not form a square matrix...");
                        flag = false;
                    }
                    else
                    {
                        int s = (int) sq;
                        if (obj.check(key_string, s))
                        {
                            copy_1=obj.cofact(obj.keymatrix, s);
                            obj.check(copy_1, s);
                            copy=obj.divide(input_string, s).substring(0,input_string.length() - extraChars);
                            output_tv.setText("CipherText : "+copy+"\nInverseKey : "+copy_1);
                        }
                        else {
                            output_tv.setText("Key Inverse is not possible. Try another key...");
                            flag = false;
                        }
                    }
                }
            }
        });
        Button reset=(Button)view.findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_et.setText("");
                key_et.setText("");
                output_tv.setText("Output :");
                copy="";
                copy_1="";

            }
        });
        final ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    input_et.setText(copy,TextView.BufferType.EDITABLE);
                }
            }
        });


    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

}

class HillCipher {
    int keymatrix[][];
    int linematrix[];
    int resultmatrix[];
    TextView output_tv;

    public String divide(String temp, int s) {
        String ans="";
        while (temp.length() > s) {
            String sub = temp.substring(0, s);
            temp = temp.substring(s, temp.length());
            ans=ans+perform(sub);
        }
        if (temp.length() == s)
            ans=ans+perform(temp);
        else if (temp.length() < s) {
            for (int i = temp.length(); i < s; i++) {
                temp = temp + 'X';
            }
            ans=ans+perform(temp);
        }
        return ans;
    }

    public String perform(String line) {
        linetomatrix(line);
        linemultiplykey(line.length());
        return result(line.length());
    }

    public void keytomatrix(String key, int len) {
        keymatrix = new int[len][len];
        int c = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                keymatrix[i][j] = ((int) key.charAt(c)) - 65;
                c++;
            }
        }
    }

    public void linetomatrix(String line) {
        linematrix = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            linematrix[i] = ((int) line.charAt(i)) - 65;
        }
    }

    public void linemultiplykey(int len) {
        resultmatrix = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                resultmatrix[i] += keymatrix[i][j] * linematrix[j];
            }
            resultmatrix[i] %= 26;
        }
    }

    public String result(int len) {
        String result = "";
        for (int i = 0; i < len; i++) {
            result += (char) (resultmatrix[i] + 65);
        }
        return result;
    }

    public boolean check(String key, int len) {
        keytomatrix(key, len);
        int d = determinant(keymatrix, len);
        d = d % 26;
        if (d == 0) {
            System.out.println("Invalid key!!! Key is not invertible because determinant=0...");
            return false;
        } else if (d % 2 == 0 || d % 13 == 0) {
            System.out.println("Invalid key!!! Key is not invertible because determinant has common factor with 26...");
            return false;
        } else {
            return true;
        }
    }

    public int determinant(int A[][], int N) {
        int res;
        if (N == 1)
            res = A[0][0];
        else if (N == 2) {
            res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        } else {
            res = 0;
            for (int j1 = 0; j1 < N; j1++) {
                int m[][] = new int[N - 1][N - 1];
                for (int i = 1; i < N; i++) {
                    int j2 = 0;
                    for (int j = 0; j < N; j++) {
                        if (j == j1)
                            continue;
                        m[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                res += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                        * determinant(m, N - 1);
            }
        }
        return res;
    }

    public String cofact(int num[][], int f) {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++) {
            for (p = 0; p < f; p++) {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++) {
                    for (j = 0; j < f; j++) {
                        b[i][j] = 0;
                        if (i != q && j != p) {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * determinant(b, f - 1);
            }
        }
        return trans(fac, f);
    }

    String trans(int fac[][], int r) {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = determinant(keymatrix, r);
        int mi = mi(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++) {
            for (j = 0; j < r; j++) {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++) {
            for (j = 0; j < r; j++) {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }
        System.out.println("\nInverse key:");
        return matrixtoinvkey(inv, r);
    }

    public int mi(int d) {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0) {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }

    public String matrixtoinvkey(int inv[][], int n) {
        String invkey = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                invkey += (char) (inv[i][j] + 65);
            }
        }
        return invkey;
    }
}