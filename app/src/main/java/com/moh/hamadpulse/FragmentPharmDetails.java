package com.moh.hamadpulse;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterPharmDocDetails;
import com.moh.hamadpulse.adapters.NuserMedEXecAdapter;
import com.moh.hamadpulse.models.DocPharmacy;
import com.moh.hamadpulse.models.DocPharmacyDetails;
import com.moh.hamadpulse.models.GetNurseMedExecution;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentPharmDetails extends DialogFragment implements OnAdapterClick {
    DocPharmacy mDocPharmacy;
    public String fragment_cd = "28";
    boolean flag;
    public FragmentPharmDetails(DocPharmacy mDocPharmacy,boolean flag) {
        // Required empty public constructor
        this.mDocPharmacy = mDocPharmacy;
        this.flag=flag;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharm_details, container, false);
    }

    private RecyclerView rvPharmDetails_Once_Only;
    private RecyclerView rvPharmDetails_AS_Required;
    private RecyclerView rvPharmDetails_Parenteral_Therapy;
    private RecyclerView rvPharmDetails_Oral_Others;
    private TextView txt_once_only, txt_as_required, txt_Parenteral_Therapy, txt_Oral_Others;
    private RecyclerView rvNurseDetails;
    AdapterPharmDocDetails mAdapterPharmDocDetails;
    AdapterPharmDocDetails mAdapterNurseDetails;
    LinearLayoutManager mLinearLayoutManager;
    public ArrayList<DocPharmacyDetails> mListDocPharmacyDetails;
    public ArrayList<DocPharmacyDetails> mListNurseDetails;
    public ArrayList<DocPharmacyDetails> mListpharmDetails;
    public AVLoadingIndicatorView imgLoading;
    private TextView txtPharmDetailsDate;
    private TextView txtPharmDetailsDoc;
    private TextView txtPharmDetailsDoctor;
    private LinearLayout llClosePharmResult;
    private ImageButton btnDC;
    private ImageView imgDEtails;
    AdapterPharmDocDetails mAdapterAsRequired;
    AdapterPharmDocDetails mAdapterParenteral;
    AdapterPharmDocDetails mAdapterOthers;
    ArrayList<GetNurseMedExecution> getnurseArrayList;
    NuserMedEXecAdapter nuserMedEXecAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLoading = view.findViewById(R.id.imgLoading);
        btnDC = view.findViewById(R.id.btnDC);
        if (Controller.pref.getString(USER_TYPE, "").equals("2")) {  // nurse
            btnDC.setVisibility(View.GONE);
        }
        btnDC.setEnabled(false);
        btnDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityPatient)getActivity()).CallFragment(new FragmentPharmAdd(mListDocPharmacyDetails));
                dismiss();
            }
        });
        txtPharmDetailsDate = view.findViewById(R.id.txtPharmDetailsDate);
        txtPharmDetailsDoc = view.findViewById(R.id.txtPharmDetailsDoc);
        txtPharmDetailsDoctor = view.findViewById(R.id.txtPharmDetailsDoctor);
        llClosePharmResult = view.findViewById(R.id.llClosePharmResult);
        rvNurseDetails = view.findViewById(R.id.rvNurseDetails);
        llClosePharmResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//      pharmacy RV
        rvPharmDetails_Once_Only = view.findViewById(R.id.rvPharmDetails_Once_Only);
        rvPharmDetails_AS_Required = view.findViewById(R.id.rvPharmDetails_AS_Required);
        rvPharmDetails_Parenteral_Therapy = view.findViewById(R.id.rvPharmDetails_Parenteral_Therapy);
        rvPharmDetails_Oral_Others = view.findViewById(R.id.rvPharmDetails_Oral_Others);
//      pharmacy TextView
        txt_once_only = view.findViewById(R.id.txt_once_only);
        txt_as_required = view.findViewById(R.id.txt_as_required);
        txt_Parenteral_Therapy = view.findViewById(R.id.txt_Parenteral_Therapy);
        txt_Oral_Others = view.findViewById(R.id.txt_Oral_Others);

        txtPharmDetailsDate.setText(mDocPharmacy.getiNPPHARMCREATEDON());
        txtPharmDetailsDoc.setText(mDocPharmacy.getiNPPHARMCODE());
        txtPharmDetailsDoctor.setText(mDocPharmacy.getdOCNAME());

        mListDocPharmacyDetails = new ArrayList<>();
        mListNurseDetails = new ArrayList<>();
        mListpharmDetails = new ArrayList<>();
        getnurseArrayList = new ArrayList<>();
        nuserMedEXecAdapter = new NuserMedEXecAdapter(getnurseArrayList, getContext());
        AdapterPharmDocDetails.flag=flag;

        mAdapterPharmDocDetails = new AdapterPharmDocDetails(
                mListDocPharmacyDetails, getnurseArrayList, getContext(), this);
        mAdapterAsRequired = new AdapterPharmDocDetails(
                mListDocPharmacyDetails, getnurseArrayList, getContext(), this);
        mAdapterParenteral = new AdapterPharmDocDetails(
                mListDocPharmacyDetails, getnurseArrayList, getContext(), this);
        mAdapterOthers = new AdapterPharmDocDetails(
                mListDocPharmacyDetails, getnurseArrayList, getContext(), this);
//        mAdapterPharmDocDetails.setmOnAdapterLongClick(new AdapterPharmDocDetails.OnAdapterLongClick() {
//            @Override
//            public void onLongClick(DocPharmacyDetails mDocPharmacyDetails) {
//
//                if (Controller.pref.getString(USER_TYPE, "").equals("1")||
//                        Controller.pref.getString(USER_TYPE, "").equals("3")) {  //1- doctor 3-super doctor
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                            .setTitle("الإجراء")
//                            .setIcon(R.drawable.testicon)
//                            .setPositiveButton("إيقاف العلاج", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    update_medecine(mDocPharmacyDetails);
//
//                                }
//                            })
//                            .setNeutralButton("إلغاء", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dismiss();
//                                }
//                            });
//                    builder.create();
//                    builder.show();
//                }else{
////                    FragmentMedecineEX fragmentEX=new FragmentMedecineEX(mDocPharmacyDetails);
////                    FragmentTransaction ft = getFragmentManager().beginTransaction();
////                    fragmentEX.show(ft, "fragmentEX");
//                    ((ActivityPatient)getActivity()).CallFragment(new FragmentMedecineEX(mDocPharmacyDetails));
//                    dismiss();
//                }
//            }
//        });
//        mLinearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvPharmDetails_Once_Only.setLayoutManager(mLinearLayoutManager);
        LinearLayoutManager mLinearLayoutManager_1 = new LinearLayoutManager(getContext());
        rvPharmDetails_AS_Required.setLayoutManager(mLinearLayoutManager_1);
        LinearLayoutManager mLinearLayoutManager_2 = new LinearLayoutManager(getContext());
        rvPharmDetails_Parenteral_Therapy.setLayoutManager(mLinearLayoutManager_2);
        LinearLayoutManager mLinearLayoutManager_3 = new LinearLayoutManager(getContext());
        rvPharmDetails_Oral_Others.setLayoutManager(mLinearLayoutManager_3);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
//                LinearLayoutManager.VERTICAL);
//        rvPharmDetails.addItemDecoration(dividerItemDecoration);
        rvPharmDetails_Once_Only.setAdapter(mAdapterPharmDocDetails);
        rvPharmDetails_AS_Required.setAdapter(mAdapterAsRequired);
        rvPharmDetails_Parenteral_Therapy.setAdapter(mAdapterParenteral);
        rvPharmDetails_Oral_Others.setAdapter(mAdapterOthers);
//-----------------------------------------------------------------
        mAdapterNurseDetails = new AdapterPharmDocDetails(
                mListNurseDetails, getnurseArrayList, getContext(),
                this);
//        mAdapterNurseDetails.setmOnAdapterLongClick(new AdapterPharmDocDetails.OnAdapterLongClick() {
//            @Override
//            public void onLongClick(DocPharmacyDetails mDocPharmacyDetails) {
//
//                if (Controller.pref.getString(USER_TYPE, "").equals("1")||
//                        Controller.pref.getString(USER_TYPE, "").equals("3")) {  //1- doctor 3-super doctor
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                            .setTitle("الإجراء")
//                            .setIcon(R.drawable.testicon)
//                            .setPositiveButton("إيقاف العلاج", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    update_medecine(mDocPharmacyDetails);
//
//                                }
//                            })
//                            .setNeutralButton("إلغاء", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dismiss();
//                                }
//                            });
//                    builder.create();
//                    builder.show();
//                }else{
////                    FragmentMedecineEX fragmentEX=new FragmentMedecineEX(mDocPharmacyDetails);
////                    FragmentTransaction ft = getFragmentManager().beginTransaction();
////                    fragmentEX.show(ft, "fragmentEX");
//                    ((ActivityPatient)getActivity()).CallFragment(new FragmentMedecineEX(mDocPharmacyDetails));
//                    dismiss();
//                }
//            }
//        });
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        rvNurseDetails.setLayoutManager(layout);
        rvNurseDetails.setAdapter(mAdapterNurseDetails);
        getDocPharmacyDetails();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    public void update_medecine(DocPharmacyDetails mDocPharmacyDetails) {
//
//        imgLoading.setVisibility(View.VISIBLE);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("INP_DETAIL_CODE", mDocPharmacyDetails.getINPDETAILCODE());
//        map.put("INP_ORDER_CD", mDocPharmacyDetails.getINPORDERCD());
//
//        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
//        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
//        map.put("TRANS_ACTION_CD_IN", "1");
//        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
//        map.put("TRANS_DESCRIPTION_IN", "صيدلية");
//
//        MyRequest.makeRquest(getContext(), Controller.UPDATE_MEDICINE_STATUS_URL, map, new MyRequest.CallBack() {
//            @Override
//            public void Result(String response) {
//                Gson gson = new Gson();
//                try {
//                    JSONObject mJSONObject = new JSONObject(response);
//                    int res = mJSONObject.getInt("P_RESULT");
//                    if (res == 1) {
//                        Handler aHandler = new Handler();
//                        aHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Controller.LOADER_DIALOG.hideDialog();
//                                Controller.Msg_DIALOG.showDialog("تم إيقاف العلاج بنجاح");
//                            }
//                        }, 2000);
//                    }
//                    if (res == 2) {
//                        Handler aHandler = new Handler();
//                        aHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Controller.LOADER_DIALOG.hideDialog();
//                                Controller.Msg_DIALOG.showDialog("العلاج مصروف فلا يمكن إيقافه");
//                            }
//                        }, 2000);
//                    }
//                } catch (JSONException e) {
//                    Log.e("test",e.getMessage());
//                    e.printStackTrace();
//                }
//                imgLoading.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void Error(VolleyError error) {
//                imgLoading.setVisibility(View.GONE);
//            }
//        });
//    }


    public void getDocPharmacyDetails() {
        String frag_cd = "8";
        imgLoading.setVisibility(View.VISIBLE);
        HashMap<String, String> h = new HashMap<>();
        h.put("ORDER_CD", mDocPharmacy.getiNPPHARMCODE());
        h.put("TRANS_SCREEN_CD_IN", frag_cd);
        h.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        h.put("TRANS_ACTION_CD_IN", "2");
        h.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        h.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        h.put("TRANS_DESCRIPTION_IN", "صيدلية");

        Log.e("maph", h.toString());
        MyRequest.makeRquest(getContext(), Controller.GET_DOC_PHARMACY_DETAILS, h, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    Log.e("json", mJSONObject.toString());
                    mListDocPharmacyDetails = gson.fromJson(mJSONObject.getString("ADM_PHARM"), new TypeToken<ArrayList<DocPharmacyDetails>>() {
                    }.getType());
                    Log.d("test", mListDocPharmacyDetails.size() + "");
                    for (DocPharmacyDetails item : mListDocPharmacyDetails) {
                        if (item.getDischargedFromNursing() != null)
                            if (item.getDischargedFromNursing().equals("1")) {
                                mListNurseDetails.add(item);
                            } else {
                                mListpharmDetails.add(item);
                            }
                    }
                    ArrayList<DocPharmacyDetails> mListpharmDetails_Once_Only = new ArrayList<>();
                    ArrayList<DocPharmacyDetails> mListpharmDetails_As_Required = new ArrayList<>();
                    ArrayList<DocPharmacyDetails> mListpharmDetails_Parenteral = new ArrayList<>();
                    ArrayList<DocPharmacyDetails> mListpharmDetails_Others = new ArrayList<>();
                    for (DocPharmacyDetails item : mListpharmDetails) {
                        if (item.getINPDOSECD().equals("16"))
                            mListpharmDetails_Once_Only.add(item);
                        else if (item.getINPDOSECD().equals("15"))
                            mListpharmDetails_As_Required.add(item);
                        else if (item.getINPDOSEGIVINGCD() != null
                                && item.getINPDOSEGIVINGCD().equals("5"))
                            mListpharmDetails_Parenteral.add(item);
                        else
                            mListpharmDetails_Others.add(item);
                    }
                    if (mListpharmDetails_Once_Only.size() != 0) {
                        mAdapterPharmDocDetails.setmListDocPharmacyDetails(mListpharmDetails_Once_Only);
                    } else {
                        rvPharmDetails_Once_Only.setVisibility(View.GONE);
                        txt_once_only.setVisibility(View.GONE);
                    }
                    if (mListpharmDetails_As_Required.size() != 0) {
                        mAdapterAsRequired.setmListDocPharmacyDetails(mListpharmDetails_As_Required);
                    } else {
                        rvPharmDetails_AS_Required.setVisibility(View.GONE);
                        txt_as_required.setVisibility(View.GONE);
                    }
                    if (mListpharmDetails_Parenteral.size() != 0) {
                        mAdapterParenteral.setmListDocPharmacyDetails(mListpharmDetails_Parenteral);
                    } else {
                        rvPharmDetails_Parenteral_Therapy.setVisibility(View.GONE);
                        txt_Parenteral_Therapy.setVisibility(View.GONE);
                    }
                    if (mListpharmDetails_Others.size() != 0) {
                        mAdapterOthers.setmListDocPharmacyDetails(mListpharmDetails_Others);
                    } else {
                        rvPharmDetails_Oral_Others.setVisibility(View.GONE);
                        txt_Oral_Others.setVisibility(View.GONE);
                    }

                    mAdapterNurseDetails.setmListDocPharmacyDetails(mListNurseDetails);
                    Log.d("test", mListDocPharmacyDetails.size() + "");
                    btnDC.setEnabled(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                imgLoading.setVisibility(View.GONE);
            }

            @Override
            public void Error(VolleyError error) {
                imgLoading.setVisibility(View.GONE);
                Controller.view_error(error, getContext());
            }
        });
    }

//    public void GET_EXECUTE_MED_NURSE() {
//
//        Map<String,String> map = new HashMap<>();
//         map.put("P_ADM_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd()+ "");
//     //   map.put("P_ADM_CD", "6135");
//        map.put("P_TREAT_CD","30");
//
//        MyRequest.makeRquest(getContext(), Controller.GET_MED_EXECUTION_URL, map, new MyRequest.CallBack() {
//            @Override
//            public void Result(String response) {
//                JSONObject mJSONObject = null;
//                try {
//                    mJSONObject = new JSONObject(response);
//                    JSONArray jsonArray = mJSONObject.getJSONArray("MED_EXE_CUR");
//                    //////////////////
//                    Gson gson = new Gson();
//                    Type type = new TypeToken<ArrayList<GetNurseMedExecution>>() {}.getType();
//                    getnurseArrayList = gson.fromJson(jsonArray.toString(), type);
//                    /////////////////
//                    nuserMedEXecAdapter.setNurseMedExecutionList(getnurseArrayList);
//                    nuserMedEXecAdapter.notifyDataSetChanged();
//
//
//                    // getIcdConstArrayList.add(getIcdConstArrayList);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void Error(VolleyError error) {
//            }
//        });
//    }

    @Override
    public void myClick(Object mObject) {

    }

}