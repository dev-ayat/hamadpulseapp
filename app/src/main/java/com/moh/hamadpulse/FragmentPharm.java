 package com.moh.hamadpulse;

 import static com.moh.hamadpulse.Controller.GET_NEW_DOC_PHARMACY;
 import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

 import android.os.Bundle;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.CheckBox;
 import android.widget.LinearLayout;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.fragment.app.Fragment;
 import androidx.fragment.app.FragmentTransaction;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.android.volley.VolleyError;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.moh.hamadpulse.adapters.AdapterPharmDoc;
 import com.moh.hamadpulse.models.DocPharmacy;

 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.HashMap;

 public class FragmentPharm extends Fragment implements OnAdapterClick {

    String patadmcd;
     public String fragment_cd = "8";
     String adm_cd;
     LinearLayout pharm_order,no_pharm_orders;
    public FragmentPharm() {
        // Required empty public constructor
    }

    public FragmentPharm(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }
     public FragmentPharm(InterfacePatient mInterfacePatient,String adm_cd) {
         this.mInterfacePatient = mInterfacePatient;
         this.adm_cd=adm_cd;
     }

    InterfacePatient mInterfacePatient;
    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }
    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharm, container, false);
    }

    private RecyclerView rvPharmDoc;
    AdapterPharmDoc mAdapterPharmDoc;
    LinearLayoutManager mLinearLayoutManager;
    public ArrayList<DocPharmacy> mListDocPharmacy;
    FloatingActionButton btnAddDocPharm;
    CheckBox checkBoxorder;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patadmcd = ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"";
        rvPharmDoc = view.findViewById(R.id.rvPharmDoc);
        btnAddDocPharm = view.findViewById(R.id.btnAddDocPharm);
        pharm_order = view.findViewById(R.id.pharm_order);
        no_pharm_orders = view.findViewById(R.id.no_pharm_orders);
//        if () {  // nurse
//            btnAddDocPharm.setVisibility(View.GONE);
//        }
        btnAddDocPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityPatient) getActivity()).CallFragment(new FragmentPharmAdd(new ArrayList<>()));
            }
        });
//      hide add medicine
        btnAddDocPharm.setVisibility(adm_cd==null?
                !(Controller.pref.getString(USER_TYPE, "").equals("1") || Controller.pref.getString(USER_TYPE, "").equals("3"))
                        ?View.GONE
                :View.VISIBLE:View.GONE);


        mListDocPharmacy = new ArrayList<>();
        mAdapterPharmDoc = new AdapterPharmDoc(mListDocPharmacy);
        mAdapterPharmDoc.setmOnAdapterClick(this);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvPharmDoc.setLayoutManager(mLinearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
//                LinearLayoutManager.VERTICAL);
//        rvPharmDoc.addItemDecoration(dividerItemDecoration);
        rvPharmDoc.setAdapter(mAdapterPharmDoc);
        getPharmDocs();

    }

    public void getPharmDocs() {
        mInterfacePatient.showLoading(true);
        HashMap<String, String> h = new HashMap<>();
//        for(int i=0;i<10;i++) {
//            h.put("items[" + i + "][item_code]", "item_code" + i);
//        }
        //h.put("ADM_CD","1140451");
        h.put("TRANS_SCREEN_CD_IN", fragment_cd);
        h.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        h.put("TRANS_ACTION_CD_IN", "2");
        h.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        h.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        h.put("TRANS_DESCRIPTION_IN", "صيدلية");
        h.put("ADM_CD", adm_cd==null?patadmcd:adm_cd);
        MyRequest.makeRquest(getActivity(), GET_NEW_DOC_PHARMACY, h, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Log.e("LOGIN", response);
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    mListDocPharmacy = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacy>>() {
                    }.getType());
                    Log.d("list_size",mListDocPharmacy.size()+"");
                    if(mListDocPharmacy.size()==0){
                        pharm_order.setVisibility(View.GONE);
                        no_pharm_orders.setVisibility(View.VISIBLE);
                    }else {
                        pharm_order.setVisibility(View.VISIBLE);
                        no_pharm_orders.setVisibility(View.GONE);
                        mAdapterPharmDoc.setmListDocPharmacy(mListDocPharmacy);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
    }

    @Override
    public void myClick(Object mObject) {
        FragmentPharmDetails mFragmentPharmDetails = new FragmentPharmDetails(((DocPharmacy)mObject),adm_cd==null);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mFragmentPharmDetails.show(ft, "FragmentPharmDetails");
        //((ActivityPatient)getActivity()).CallFragment(mFragmentPharmDetails);
    }
}