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

import java.util.Objects;

public class Hash_sha_1 extends Fragment {

    EditText input_et;
    TextView output_tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("SHA-1");
        return inflater.inflate(R.layout.sym_9_10_11,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        output_tv=(TextView)view.findViewById(R.id.output);
        Button hash=(Button)view.findViewById(R.id.button_decryption);
        hash.setVisibility(View.GONE);
        hash=(Button)view.findViewById(R.id.button_encryption);
        hash.setText("Hash");
        hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    Hash_SHA_functions hash_sha_functions=new Hash_SHA_functions();
                    output_tv.setText("Hash : "+hash_sha_functions.convertToSHA1(input_et.getText().toString()));
                }
            }
        });
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}
