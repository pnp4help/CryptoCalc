package cryptocalsi.it.cspit.charusat.crypto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class homeFragment extends Fragment {

    private String[] symmetric;
    private String[] asymmetric;
    private String[] hashing;
    private String[] other;
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //to clear BackStack fragments on home fragment
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        getActionBar().setTitle("CryptoCalc");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = this.getContext();

        symmetric =  mContext.getResources().getStringArray(R.array.Symmetric);
        asymmetric =  mContext.getResources().getStringArray(R.array.Asymmetric);
        hashing =  mContext.getResources().getStringArray(R.array.Hashing);
        other =  mContext.getResources().getStringArray(R.array.Other);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        homeFragment.RecyclerDataAdapter recyclerDataAdapter = new homeFragment.RecyclerDataAdapter(getDummyDataToPass());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(recyclerDataAdapter);
        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    private ArrayList<ParentDataItem> getDummyDataToPass() {
        ArrayList<ParentDataItem> dummyDataItems = new ArrayList<>();
        ArrayList<ChildDataItem> ChildDataItems;
        ParentDataItem ParentDataItem;
        ChildDataItem ChildDataItem;
        /////////
        ParentDataItem = new ParentDataItem();
        ParentDataItem.setParentName("Symmetric Algorithms");
        ChildDataItems = new ArrayList<>();
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[0]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[1]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[2]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[3]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[4]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[5]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(symmetric[6]);
        ChildDataItems.add(ChildDataItem);
        //
        ParentDataItem.setChildDataItems(ChildDataItems);
        dummyDataItems.add(ParentDataItem);
        ////////
        ParentDataItem = new ParentDataItem();
        ParentDataItem.setParentName("Asymmetric Algorithms");
        ChildDataItems = new ArrayList<>();
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(asymmetric[0]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(asymmetric[1]);
        ChildDataItems.add(ChildDataItem);
        //
        ParentDataItem.setChildDataItems(ChildDataItems);
        dummyDataItems.add(ParentDataItem);
        ////////
        ParentDataItem = new ParentDataItem();
        ParentDataItem.setParentName("Hashing Algorithms");
        ChildDataItems = new ArrayList<>();
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[0]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[1]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[2]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[3]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[4]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(hashing[5]);
        ChildDataItems.add(ChildDataItem);
        //
        ParentDataItem.setChildDataItems(ChildDataItems);
        dummyDataItems.add(ParentDataItem);
        ////////
        ParentDataItem = new ParentDataItem();
        ParentDataItem.setParentName("Other Algorithms");
        ChildDataItems = new ArrayList<>();
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[0]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[1]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[2]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[3]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[4]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[5]);
        ChildDataItems.add(ChildDataItem);
        //
        ChildDataItem = new ChildDataItem();
        ChildDataItem.setChildName(other[6]);
        ChildDataItems.add(ChildDataItem);
        //
        ParentDataItem.setChildDataItems(ChildDataItems);
        dummyDataItems.add(ParentDataItem);
        return dummyDataItems;
    }

    private class RecyclerDataAdapter extends RecyclerView.Adapter<homeFragment.RecyclerDataAdapter.MyViewHolder> {
        private ArrayList<ParentDataItem> ParentDataItems;

        RecyclerDataAdapter(ArrayList<ParentDataItem> ParentDataItems) {
            this.ParentDataItems = ParentDataItems;
        }

        @Override
        public homeFragment.RecyclerDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent_child_listing, parent, false);
            return new homeFragment.RecyclerDataAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(homeFragment.RecyclerDataAdapter.MyViewHolder holder, int position) {
            ParentDataItem ParentDataItem = ParentDataItems.get(position);
            holder.textView_parentName.setText(ParentDataItem.getParentName());
            //
            int noOfChildTextViews = holder.linearLayout_childItems.getChildCount();
            int noOfChild = ParentDataItem.getChildDataItems().size();
            if (noOfChild < noOfChildTextViews) {
                for (int index = noOfChild; index < noOfChildTextViews; index++) {
                    TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
                    currentTextView.setVisibility(View.GONE);
                }
            }
            for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
                TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(textViewIndex);
                currentTextView.setText(ParentDataItem.getChildDataItems().get(textViewIndex).getChildName());
                currentTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clicked_child = ((TextView) view).getText().toString();
                        if(clicked_child.equals(symmetric[0])){
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_Additive_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[1])){
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_Multiplicative_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[2])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_Affine_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[3])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_AutoKey_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[4])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_PlayFair_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[5])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_Vigenere_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(symmetric[6])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Sym_Hill_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(asymmetric[0])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Asym_Diffie_Hellman_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(asymmetric[1])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Asym_RSA_Fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[0])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_md5()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[1])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_1()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[2])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_224()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[3])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_256()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[4])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_384()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(hashing[5])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_512()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[0])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_gcd_euclidian_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[1])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_modulus_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[2])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_prime_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[3])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_multiplicative_inverse_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[4])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_miller_rabin_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[5])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_chinese_remainder_fragment()).addToBackStack("Stack").commit();
                        }
                        else if (clicked_child.equals(other[6])) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_square_multiply_fragment()).addToBackStack("Stack").commit();
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return ParentDataItems.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private Context context;
            private TextView textView_parentName;
            private LinearLayout linearLayout_childItems,linearLayout_parentItems;
            private ImageView expand_icon;

            MyViewHolder(View itemView) {
                super(itemView);
                context = itemView.getContext();
                textView_parentName = itemView.findViewById(R.id.tv_parentName);
                linearLayout_parentItems = itemView.findViewById(R.id.ll_parent_items);
                expand_icon = itemView.findViewById(R.id.expand_icon);
                linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
                linearLayout_childItems.setVisibility(View.GONE);
                int intMaxNoOfChild = 0;
                for (int index = 0; index < ParentDataItems.size(); index++) {
                    int intMaxSizeTemp = ParentDataItems.get(index).getChildDataItems().size();
                    if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
                }
                for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                    TextView textView = new TextView(context);
                    textView.setId(indexView);
                    textView.setPadding(0, 23, 0, 23);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.lightTheme));
                    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    textView.setOnClickListener(this);
                    linearLayout_childItems.addView(textView, layoutParams);
                }
                linearLayout_parentItems.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ll_parent_items) {
                    if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                        linearLayout_childItems.setVisibility(View.GONE);
                        expand_icon.setImageResource(R.drawable.ic_arrow_down);
                    } else {
                        linearLayout_childItems.setVisibility(View.VISIBLE);
                        expand_icon.setImageResource(R.drawable.ic_arrow_up);
                    }
                }
            }
        }
    }
}
