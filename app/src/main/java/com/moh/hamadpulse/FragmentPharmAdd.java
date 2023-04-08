package com.moh.hamadpulse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterPharmDocDetails;
import com.moh.hamadpulse.fragment.DialogFragment_View_Older_Medicines;
import com.moh.hamadpulse.fragment.ViewUserFavMed;
import com.moh.hamadpulse.models.DocPharmacy;
import com.moh.hamadpulse.models.DocPharmacyDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentPharmAdd extends Fragment implements OnAdapterClick, DialogFragmentMedicin.OnConfirmMedicin {

    ArrayList<DocPharmacyDetails> mListDocPharmacyDetails;
    ArrayList<DocPharmacyDetails> mListNewDrugs = new ArrayList<>();
    ArrayList<DocPharmacyDetails> mListPrevious_Medicine = new ArrayList<>();
    DialogFragmentMedicin mDialogFragmentMedicin;
    public String fragment_cd = "7";
    FragmentPharmAdd fragmentPharmAdd;
    ImageButton btnfav;
    public FragmentPharmAdd(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails) {
        // Required empty public constructor
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharm_add, container, false);
    }

    private RecyclerView rvPharmAddDetails;
    AdapterPharmDocDetails mAdapterPharmDocDetails;
    LinearLayoutManager mLinearLayoutManager;
    Button btnNewItem,btnSaveItems,btnpreItem;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPharmAdd = this;
        btnpreItem = view.findViewById(R.id.btnpreItem);
        btnfav = view.findViewById(R.id.btnfav);
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUserFavMed dialog = new ViewUserFavMed();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "UserFavMedFragment");
            }
        });
        btnpreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpreviousmedicine();

            }
        });
        btnNewItem = view.findViewById(R.id.btnNewItem);
        btnNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", "Click btnNewItem");
                showDialogMedicin(new DocPharmacyDetails(), 0);
            }
        });

        btnSaveItems = view.findViewById(R.id.btnSaveItems);
        btnSaveItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
                map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");
                map.put("USER_ID", Controller.pref.getString("USER_ID", ""));
                map.put("TRANS_SCREEN_CD_IN", fragment_cd);
                map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
                map.put("TRANS_ACTION_CD_IN", "1");
                map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
                map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
                map.put("TRANS_DESCRIPTION_IN", "صيدلية");

                for (int i = 0; i < mListDocPharmacyDetails.size(); i++) {
                    map.put("items[" + i + "][MED_CD]", mListDocPharmacyDetails.get(i).getINPMEDCD());
                    map.put("items[" + i + "][DOSE_CD]", mListDocPharmacyDetails.get(i).getINPDOSECD());
                    map.put("items[" + i + "][DOSE_TYPE]", mListDocPharmacyDetails.get(i).getINPDOSEGIVINGCD());
                    map.put("items[" + i + "][INTERVAL]", mListDocPharmacyDetails.get(i).getINPINTERVAL());
                    map.put("items[" + i + "][WANT_AMOUNT]", mListDocPharmacyDetails.get(i).getINPWANTEDAMOUNT());
                    map.put("items[" + i + "][STATUS_CD]", mListDocPharmacyDetails.get(i).getINPSTATUSCD());
                    map.put("items[" + i + "][NOTE]", mListDocPharmacyDetails.get(i).getiNPNOTE());
                }
                Log.e("map",map.toString());
                MyRequest.makeRquest(getContext(), Controller.ADD_NEW_MEDICIN, map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {
                        Log.d("response",response);
                        Gson gson = new Gson();
                        try {
                            JSONObject mJSONObject = new JSONObject(response);
                            ArrayList<DocPharmacy> mListDocPharmacy = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacy>>() {
                            }.getType());

                            FragmentPharmDetails mFragmentPharmDetails = new FragmentPharmDetails(mListDocPharmacy.get(0),true);
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            mFragmentPharmDetails.show(ft, "FragmentPharmDetails");
                            getActivity().onBackPressed();

                        } catch (JSONException e) {
                            Log.e("AYAT", e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
                    }
                });
            }
        });
        rvPharmAddDetails = view.findViewById(R.id.rvPharmAddDetails);
        mAdapterPharmDocDetails = new AdapterPharmDocDetails(mListDocPharmacyDetails,
                getContext(), this);
        mAdapterPharmDocDetails.setmOnAdapterClick(this);
//        mAdapterPharmDocDetails.setmOnAdapterLongClick(new AdapterPharmDocDetails.OnAdapterLongClick() {
//            @Override
//            public void onLongClick(DocPharmacyDetails mDocPharmacyDetails) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                        .setTitle("الإجراء")
//                        .setIcon(R.drawable.testicon)
//                        .setPositiveButton("حذف", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
////                                int pos = SearchListMedicin(mDocPharmacyDetails.getINPMEDCD());
//                                mListDocPharmacyDetails.remove(mDocPharmacyDetails);
//                                mAdapterPharmDocDetails.notifyDataSetChanged();
//                                numItems();
//                            }
//                        })
//                        .setNeutralButton("تعديل", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
//                                showDialogMedicin(mDocPharmacyDetails);
//                            }
//                        });
//                builder.create();
//                builder.show();
//            }
//        });
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvPharmAddDetails.setLayoutManager(mLinearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
//                LinearLayoutManager.VERTICAL);
//        rvPharmAddDetails.addItemDecoration(dividerItemDecoration);
        rvPharmAddDetails.setAdapter(mAdapterPharmDocDetails);
        if (mListDocPharmacyDetails.size() > 0)
            numItems();
    }

    public void numItems() {
        btnSaveItems.setText(" حفظ " + "(" + mAdapterPharmDocDetails.getmListDocPharmacyDetails().size() + ")");
    }

    public void showDialogMedicin(DocPharmacyDetails mDocPharmacyDetails, int add_update) {
        Log.e("test", "showDialogMedicin");
        mDialogFragmentMedicin = new DialogFragmentMedicin(mDocPharmacyDetails, add_update);
        mDialogFragmentMedicin.setmOnConfirmMedicin(this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mDialogFragmentMedicin.show(ft, "FragmentPharmDetails");
    }

    public int SearchListMedicin(String INP_MED_CD) {
        int pos = -1;
        for (int i = 0; i < mListDocPharmacyDetails.size(); i++)
            if (INP_MED_CD.equals(mListDocPharmacyDetails.get(i).getINPMEDCD()))
                pos = i;
        return pos;
    }

    @Override
    public void myClick(Object mObject) {
        //showDialogMedicin((DocPharmacyDetails) mObject);
    }

    @Override
    public void confirm(DocPharmacyDetails mDocPharmacyDetails, int add_update) {
//        Toast.makeText(getContext(), "Confirm", Toast.LENGTH_SHORT).show();
//        int pos = SearchListMedicin(mDocPharmacyDetails.getINPMEDCD());
//        if( pos==-1) {
//            mListDocPharmacyDetails.add(mDocPharmacyDetails);
//            mAdapterPharmDocDetails.setmListDocPharmacyDetails(mListDocPharmacyDetails);
//        }
//        else {
//            mListDocPharmacyDetails.set(pos,mDocPharmacyDetails);
//            mAdapterPharmDocDetails.notifyItemChanged(pos);
//        }
//        numItems();
        if (add_update == 0)
            refresh(mDocPharmacyDetails);
        else
            mAdapterPharmDocDetails.notifyDataSetChanged();
    }

    public void getpreviousmedicine(){
        Map<String, String> map = new HashMap<>();
        map.put("ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "");

        MyRequest.makeRquest(getContext(), Controller.GET_DOC_PHARM_MEDICINE, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Log.e("LOGIN","ayat2000"+response);

                Gson gson = new Gson();
                try {
                    Log.e("LOGIN", "ayat2100" + response);

                    JSONObject mJSONObject = new JSONObject(response);
                    Log.e("LOGIN", "ayat2200" + mJSONObject);
                    if (mListPrevious_Medicine.size() == 0)
                        mListPrevious_Medicine = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacyDetails>>() {
                        }.getType());
                    Log.e("mListDocPharmacy", "mListDocPharmacy" + mListPrevious_Medicine.size());
//                    FragmentPharmDetails mFragmentPharmDetails = new FragmentPharmDetails(mListDocPharmacy.get(0));
//                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    mFragmentPharmDetails.show(ft, "FragmentPharmDetails");


                    FragmentTransaction ft = ((AppCompatActivity) getContext()).
                            getSupportFragmentManager().beginTransaction();
                    new DialogFragment_View_Older_Medicines
                            (mListPrevious_Medicine, fragmentPharmAdd).
                            show(ft, "old medicines fragments");


//                    getActivity().onBackPressed();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });

    }

    public void refresh(DocPharmacyDetails mDocPharmacyDetails) {
        mListDocPharmacyDetails.clear();
        for (DocPharmacyDetails item : mListPrevious_Medicine) {
            if (item.getiNPNOTE() == null)
                item.setiNPNOTE("");
            if (item.isSelected())
                mListDocPharmacyDetails.add(0, item);
        }
        if (mDocPharmacyDetails != null)
            mListNewDrugs.add(mDocPharmacyDetails);
        mListDocPharmacyDetails.addAll(mListNewDrugs);

//        Toast.makeText(getContext(), mListDocPharmacyDetails.size()+""
//                , Toast.LENGTH_LONG).show();
        mAdapterPharmDocDetails.setmListDocPharmacyDetails(mListDocPharmacyDetails);
        numItems();
    }

    public void delete_medicine(DocPharmacyDetails model) {
        if (mListNewDrugs.contains(model))
            mListNewDrugs.remove(model);
        numItems();
    }
}