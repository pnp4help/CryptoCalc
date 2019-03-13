package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Html;
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

public class Oth_square_multiply_fragment extends Fragment {

  TextView temp;
  EditText num1_et,num2_et,num3_et;
  BigInteger num1, num2, num3;
  Button calculate;

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getActionBar().setTitle("Square and Multiply");
    return inflater.inflate(R.layout.other_square_multiply,container,false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    temp=(TextView)view.findViewById(R.id.output);
    num1_et=(EditText)view.findViewById(R.id.plaintext_input);
    num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
    num2_et=(EditText)view.findViewById(R.id.key_input);
    num3_et = (EditText)view.findViewById(R.id.mod_input);
    calculate = (Button)view.findViewById(R.id.button_calculate);
    calculate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        try {
          InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
          imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
          // TODO: handle exception
        }
        if(num1_et.getText().toString().equals("")||num2_et.getText().toString().equals("") || num3_et.getText().toString().equals("")){
          Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
        }
        else {
          num1 = new BigInteger(num1_et.getText().toString());
          num2 = new BigInteger(num2_et.getText().toString());
          num3 = new BigInteger(num3_et.getText().toString());
          try {
            temp.setText("Answer : "+String.valueOf(num1.modPow(num2, num3)));
          }
          catch (Exception e){
            temp.setText("Mod 0 is not Possible");
          }
        }
      }
    });
  }

  private ActionBar getActionBar() {
    return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
  }
}