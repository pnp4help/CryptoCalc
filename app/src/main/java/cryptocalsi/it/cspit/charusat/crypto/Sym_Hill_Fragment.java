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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;


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
        key_et=(EditText)view.findViewById(R.id.key_input);
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
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
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
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
                            output_tv.setText("PlainText : "+copy+"\nInverseKey : "+copy_1);
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
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
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
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
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

    public String divide(String temp, int s) {
        /*Function is used to divide the input string so that it can be multiply with keymatrix and divided part is passed
         to perform function with will convert it into matrix and after that multiply this matrix to keymatrix and obtain
         the result of only divided part*/
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
        /*Convert the line into matrix and multiply with key and produce the result*/
        linetomatrix(line);
        linemultiplykey(line.length());
        return result(line.length());
    }

    public void keytomatrix(String key, int len) {
        /*Function to change key into matrix*/
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
        /*COnverting string line into matrix*/
        linematrix = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            linematrix[i] = ((int) line.charAt(i)) - 65;
        }
    }

    public void linemultiplykey(int len) {
        /*Multiply line with key matrix and modulo it with 26*/
        resultmatrix = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                resultmatrix[i] += keymatrix[j][i] * linematrix[j];
            }
            resultmatrix[i] %= 26;
        }
    }

    public String result(int len) {
        /*Generating result from resultmatrix*/
        String result = "";
        for (int i = 0; i < len; i++) {
            result += (char) (resultmatrix[i] + 65);
        }
        return result;
    }

    public boolean check(String key, int len) {
        /*Function to convert key into matrix using keytomatrix function and find determinant of that matrix and checking if
         inverse of that key exist or not */
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
        /*Function to find the determinant of key*/
        int res;
        if (N == 1)
            res = A[0][0];
        else if (N == 2) {
            res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        else {
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
        /*Finding Cofactor */
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
        /*Transform of cofactor matrix which will give adjoint of matrix and multiply with multiplicativeInverse to give
         inverse of matrix */
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = determinant(keymatrix, r);
        BigInteger temp = (BigInteger.valueOf(d)).modInverse(BigInteger.valueOf(26));
        int mi = temp.intValue();
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

    public String matrixtoinvkey(int inv[][], int n) {
        /*Convert matrix into string*/
        String invkey = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                invkey += (char) (inv[i][j] + 65);
            }
        }
        return invkey;
    }
}