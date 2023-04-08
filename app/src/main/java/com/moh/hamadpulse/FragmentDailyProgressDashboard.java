package com.moh.hamadpulse;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.adapters.AdapterExaminationAdm;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.ExaminationAdm;
import com.moh.hamadpulse.models.ProgressNoteModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentDailyProgressDashboard extends Fragment implements AdapterExaminationAdm.OnClickExaminationAdm {

    RequestQueue mRequestQueue;
    FloatingActionButton btnAddExam;
    ArrayList<ExaminationAdm> mListExaminationAdm;
    RecyclerView rvExaminationAdm;
    AdapterExaminationAdm mAdapterExaminationAdm;
    LinearLayoutManager mLinearLayoutManager;
    String fragment_cd = "20";
    InterfacePatient mInterfacePatient;
    TextView txt_progress_note;
    String adm_cd;
    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }


    public FragmentDailyProgressDashboard() {
        // Required empty public constructor
    }
    public FragmentDailyProgressDashboard(InterfacePatient mInterfacePatient) {
        // Required empty public constructor
        this.mInterfacePatient=mInterfacePatient;
    }
    public FragmentDailyProgressDashboard(InterfacePatient mInterfacePatient,String adm_cd) {
        // Required empty public constructor
        this.mInterfacePatient=mInterfacePatient;
        this.adm_cd=adm_cd;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_progress_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddExam = view.findViewById(R.id.btnAddExam);
        rvExaminationAdm = view.findViewById(R.id.rvExaminationAdm);
        txt_progress_note = view.findViewById(R.id.txt_progress_note);
        mListExaminationAdm = new ArrayList<>();
        mAdapterExaminationAdm = new AdapterExaminationAdm(mListExaminationAdm,this,adm_cd==null);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rvExaminationAdm.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvExaminationAdm.setLayoutManager(mLinearLayoutManager);
        rvExaminationAdm.setAdapter(mAdapterExaminationAdm);
        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPatient) getActivity()).CallFragment(new FragmentDailyProgress(null));
            }
        });
        btnAddExam.setVisibility(adm_cd==null?Controller.pref.getString(USER_TYPE, "").equals("5")?View.GONE:View.VISIBLE:View.GONE);
        getProgressNote();
//        if(!Controller.pref.getString(USER_TYPE, "").equals("1"))
//            btnAddExam.setVisibility(View.GONE);
    }

    private void getProgressNote() {
        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", adm_cd==null?((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "":adm_cd);
        map.put("P_PATRIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd + "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "View Progress Note");
        MyRequest.makeRquest(getContext(), Controller.GET_ADMISSION_NOTES_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONObject jsonObject = mJSONObject.getJSONObject("PROGRESS");
                            Gson gson = new Gson();
                            ProgressNoteModel model = gson.fromJson(jsonObject.toString(),
                                    ProgressNoteModel.class);
                            txt_progress_note.setText(model.getProgress_note());

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Log.e("api", e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            showLoading(false);

                        }

                    }

                    @Override
                    public void Error(VolleyError error) {
                        Controller.view_error(error, getContext());
//                        showLoading(false);
                    }
                });
    }

    private void get_examination_adm() {

        String AdmCd = ((ActivityPatient) getActivity()).getmCardviewDataModel().getAdmcd() + "";
        mInterfacePatient.showLoading(true);
        mRequestQueue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.POST, Controller.GET_EXAMINATION_ADM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response",response.toString());
                Gson mGson;
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    mGson = new Gson();
                    mListExaminationAdm = mGson.fromJson(mJSONObject.getString("EXAM_ADMS"), new TypeToken<ArrayList<ExaminationAdm>>() {
                    }.getType());
                    mAdapterExaminationAdm.setmListExaminationAdm(mListExaminationAdm);
                    mAdapterExaminationAdm.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ADM_CD",adm_cd==null? AdmCd:adm_cd);
                params.put("TRANS_SCREEN_CD_IN", fragment_cd);
                params.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
                params.put("TRANS_ACTION_CD_IN", "2");
                params.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
                params.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
                params.put("TRANS_DESCRIPTION_IN", "DAILY PROG ");
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                return params;
            }

        };
        mRequestQueue.add(sr);
    }

    @Override
    public void onResume() {
        super.onResume();
        get_examination_adm();
        ((ActivityPatient) getActivity()).setTitle("Daily Progrees");
    }

    public void deleteExam(ExaminationAdm mExaminationAdm) {

        Map<String, String> map = new HashMap<>();
        map.put("P_EXAM_CD", mExaminationAdm.getEXAM_SERIAL());
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        mInterfacePatient.showLoading(true);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DEL_EXAM, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", "response=" + response.toString());
                try {
                    int res = response.getInt("Result");
                    if (res == 1) {
                        Toast.makeText(getContext(), "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        mListExaminationAdm.remove(mExaminationAdm);
                        mAdapterExaminationAdm.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getContext(), "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
        jsObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                // Here goes the new timeout 3 minutes
                return 3 * 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                // The max number of attempts
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);


    }
    @Override
    public void onClick(ExaminationAdm mExaminationAdm) {
        ((ActivityPatient) getActivity()).CallFragment(new FragmentDailyProgress(mExaminationAdm));


//        if(mExaminationAdm.getDIFF_MIN()<5 && Controller.pref.getString(EMP_SERIAL, "").equals(mExaminationAdm.getEXAM_CREATED_BY())) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                    .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
//                    .setIcon(R.drawable.testicon)
//                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            deleteExam(mExaminationAdm);
//                        }
//                    })
//                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
//                            dialog.dismiss();
//                        }
//                    });
//            builder.create();
//            builder.show();
//        }
    }
}