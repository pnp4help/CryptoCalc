package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.math.BigInteger;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class Oth_miller_rabin_fragment extends Fragment {
  TextInputLayout til;
  TextView temp;
  EditText num1_et,num2_et;
  long num1;
  int num2;

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getActionBar().setTitle("Miller Rabin Primality Test");
    return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    InputFilterMinMax filter1 = new InputFilterMinMax("0", "1000000000") {};
    InputFilterMinMax filter2 = new InputFilterMinMax("1", "5") {};

    ImageView imageView=(ImageView)view.findViewById(R.id.swap);
    imageView.setVisibility(View.GONE);
    temp=(TextView)view.findViewById(R.id.output);
    num1_et=(EditText)view.findViewById(R.id.plaintext_input);
    num1_et.setFilters(new InputFilter[]{filter1});
    num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
    til = (TextInputLayout)view.findViewById(R.id.plaintext_input_layout);
    til.setHint("Enter Number");

    num2_et=(EditText)view.findViewById(R.id.key_input);
    num2_et.setFilters(new InputFilter[]{filter2});
    til = (TextInputLayout)view.findViewById(R.id.key_input_layout);
    til.setHint("Enter Number of Iterations");
    Button chk_prime=(Button)view.findViewById(R.id.button_decryption);
    chk_prime.setVisibility(View.GONE);
    chk_prime=(Button)view.findViewById(R.id.button_encryption);
    chk_prime.setText("Is Prime ?");
    chk_prime.setOnClickListener(new View.OnClickListener() {
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
          num1=Long.valueOf(num1_et.getText().toString());
          num2=Integer.valueOf(num2_et.getText().toString());
          MillerRabin millerRabin=new MillerRabin();
          boolean check=millerRabin.isPrime(num1,num2);
          if(check){
            temp.setText(String.valueOf(num1)+" is Probable Prime Number");
          }
          else
            temp.setText(String.valueOf(num1)+" is Composite Number");

        }
      }
    });
  }

  private ActionBar getActionBar() {
    return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
  }
}

class MillerRabin{
  public boolean isPrime(long n, int iteration)
  {
    /* base case */
    if (n == 0 || n == 1)
      return false;
    /* base case - 2 is prime */
    if (n == 2)
      return true;
    /* an even number other than 2 is composite */
    if (n % 2 == 0)
      return false;

    long s = n - 1;
    while (s % 2 == 0)
      s /= 2;

    Random rand = new Random();
    for (int i = 0; i < iteration; i++)
    {
      long r = Math.abs(rand.nextLong());
      long a = r % (n - 1) + 1, temp = s;
      long mod = modPow(a, temp, n);
      while (temp != n - 1 && mod != 1 && mod != n - 1)
      {
        mod = mulMod(mod, mod, n);
        temp *= 2;
      }
      if (mod != n - 1 && temp % 2 == 0)
        return false;
    }
    return true;
  }
  /** Function to calculate (a ^ b) % c **/
  public long modPow(long a, long b, long c)
  {
    long res = 1;
    for (int i = 0; i < b; i++)
    {
      res *= a;
      res %= c;
    }
    return res % c;
  }
  /** Function to calculate (a * b) % c **/
  public long mulMod(long a, long b, long mod)
  {
    return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
  }
}