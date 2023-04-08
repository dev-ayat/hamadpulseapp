package com.moh.hamadpulse.fragment;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.CustemException.NoDepartmentFound;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.adapters.CardviewAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.constants.RecyclerTouchListener;
import com.moh.hamadpulse.models.CardviewDataModel;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import android.support.v7.widget.SearchView.OnQueryTextListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment {//implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    static View.OnClickListener myOnClickListener;
    private static CardviewAdapter cardviewadapter;
    private static RecyclerView recyclerView;
    private static ArrayList<CardviewDataModel> Carddata;
    private static ArrayList<Integer> removedItems;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerTouchListener touchListener;
    SearchView emptyTextView;
    private AVLoadingIndicatorView imgLoading;
    int SpanCnt;
    int ScreenCode;
    String Screen_cd = "13";
    String DEPT_CODE = "",NURSE_DEPT_CODE="";
    public PatientFragment(int ScreenCode) {
        Log.e("test", getClass().getSimpleName());
        // Required empty public constructor
        this.ScreenCode = ScreenCode;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient, container, false);

        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            SpanCnt = 2;
        else
            SpanCnt = 1;



        imgLoading = view.findViewById(R.id.imgLoading);
        recyclerView = view.findViewById(R.id.patient_recycler_view);
        emptyTextView = view.findViewById(android.R.id.empty);
        Carddata = new ArrayList<>();
        cardviewadapter = new CardviewAdapter(getContext(), Carddata);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), SpanCnt));
        // for swipe
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardviewadapter);
        DEPT_CODE = Controller.pref.getString("DEPT_CD_SELEC", "");
        NURSE_DEPT_CODE = Controller.pref.getString("NURSE_DEPT_CD_SELEC", "");
        if ((Controller.pref.getString(USER_TYPE, "").equals("1") ||
                Controller.pref.getString(USER_TYPE, "").equals("3")) && DEPT_CODE.equals("null")) {
            Controller.view_error(new NoDepartmentFound("المستخدم غير مسكن على قسم طبي"), getContext());

        } else if ((Controller.pref.getString(USER_TYPE, "").equals("2") ||
                Controller.pref.getString(USER_TYPE, "").equals("4")) && NURSE_DEPT_CODE.equals("null")) {
            Controller.view_error(new NoDepartmentFound("المستخدم غير مسكن على قسم تمريضي"), getContext());
        } else if (DEPT_CODE != null || NURSE_DEPT_CODE != null) {
            preparedeptData();
        }

        //Touchlistner();

        emptyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyTextView.setIconified(false);
            }
        });

        emptyTextView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s == null || s.trim().isEmpty()) {
                    resetSearch();
                    return false;
                }

                ArrayList<CardviewDataModel> filteredValues = new ArrayList<CardviewDataModel>(Carddata);
                for (CardviewDataModel value : Carddata) {
                    if (!value.getPatname().toLowerCase().contains(s.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }

                cardviewadapter = new CardviewAdapter(getContext(), filteredValues);
                recyclerView.setAdapter(cardviewadapter);
                cardviewadapter.notifyDataSetChanged();

                return false;
            }
        });
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        MenuItem item2= menu.findItem(R.id.nurse_dept);
        MenuItem item3= menu.findItem(R.id.action_info);
        MenuItem item4= menu.findItem(R.id.admin_nurse_dept);

        if (Controller.pref.getString(USER_TYPE, "").equals("1") || // doctor=1 , nurse=2, super medical=3 , admin nurse=4,pharmacist=5
            Controller.pref.getString(USER_TYPE, "").equals("3")){

            item.setVisible(true);
            item2.setVisible(true);
            item3.setVisible(true);
        }else if(Controller.pref.getString(USER_TYPE, "").equals("4")||
                Controller.pref.getString(USER_TYPE, "").equals("5")) {
            Log.e("user_type", "user_type" + Controller.pref.getString(USER_TYPE, ""));
            item.setVisible(false);
            item2.setVisible(true);
            item3.setVisible(true);
        }else{
            item.setVisible(false);
            item2.setVisible(false);
            item3.setVisible(true);
        }
        super.onPrepareOptionsMenu(menu);

    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
    private void preparedeptData() {

        //Controller.LOADER_DIALOG.showDialog("جاري جلب بيانات المرضى");
        String url = "";
        switch (ScreenCode)
        {
            case 537:// departments
                url = Controller.DEPT_PAT_URL;
                Controller.ORDER_DEP_CD = "3";
                break;

            case 554:  // emergency
                url = Controller.GET_EMR_PATIENT;
                Controller.ORDER_DEP_CD = "2";
                break;

            case 1332:  // daly care
                url = Controller.GET_DailyCare_PATIENT_URL;
                Controller.ORDER_DEP_CD = "10";
                break;
        }


        Log.e("DEPT_CD_SELEC1","ayat"+DEPT_CODE);
        Log.e("NURSE_DEPT_CD_SELEC1", "ayat" + NURSE_DEPT_CODE);
        Map<String, String> map = new HashMap<>();
        if (!DEPT_CODE.equals("null")) {
            map.put("DEPT_CODE", DEPT_CODE);
        }
        if (!NURSE_DEPT_CODE.equals("null")) {
            map.put("NURSE_DEPT_CODE", NURSE_DEPT_CODE);
            //  map.put("DEPT_CODE","");

        }
        map.put("TRANS_SCREEN_CD_IN", Screen_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "عرض مرضى الأقسام");
        map.put("HOS_NO", "");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        map.put("P_EMR_DATE", strDate);
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("map", "" + map.toString());
        imgLoading.setVisibility(View.VISIBLE);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("iyad", jsonObject.toString());
                try {
                    JSONArray patient_arr = null;
                    switch (ScreenCode)
                    {
                        case 537:
                            patient_arr = jsonObject.getJSONArray("PATIENTS");
                            Log.e("json", jsonObject.toString());
                            Log.e("json", "" + patient_arr.length());
                            if (patient_arr.length() > 0) {
                                Log.e("jsonmrp", "" + patient_arr.getJSONObject(0));

                                for (int i = 0; i < patient_arr.length(); i++) {
                                    JSONObject obj = patient_arr.getJSONObject(i);

                                    if (obj != null) {

                                        if (obj.getString("INPATIENT_CODE") != null) {

                                            CardviewDataModel Card = new CardviewDataModel(obj.getString("FULL_NAME_AR")
                                                    , obj.getString("ADMISSION_ENTER_DOC_NAME")
                                                    , obj.getString("ADMISSION_ROOM_OUT_NAME").equals("null") ? " " : obj.getString("ADMISSION_ROOM_OUT_NAME")
                                                    , obj.getString("ADMISSION_BED_OUT_NAME").equals("null") ? " " : obj.getString("ADMISSION_BED_OUT_NAME")
                                                    , obj.getString("MRP_ID")
                                                    , Integer.parseInt(obj.getString("MRP_PATREC_CODE"))
                                                    , Integer.parseInt(obj.getString("INPATIENT_ADMISSION_CD"))
                                                    , obj.getString("ADMISSION_ENTER_DATE")
                                                    , obj.getString("HOS_NO")
                                                    , obj.getString("H_NAME_AR")
                                                    , obj.getString("MRP_SEX_CD")
                                                    , obj.getString("SEX_NAME_AR")
                                                    , obj.getString("MRP_DOB")
                                                    , obj.getString("HOS_PERMISSION")
                                                    , obj.getString("LOC_CODE")
                                                    , !obj.isNull("MRP_MOBILE_NO") ? obj.getString("MRP_MOBILE_NO") : ""
                                                    , obj.getString("LOC_NAME_AR")

                                            );
                                            //Card.setEXAM_SPIN_CD(obj.getString("EXAM_SPIN_CD"));
                                            Card.setEXAM_SPIN_CD("1");
//                                            Card.setICD_CD(obj.getString("CM_ICD_CODE")==null?
//                                                    "":obj.getString("CM_ICD_CODE"));
//                                            Card.setICD_NAME_EN(obj.getString("CM_ICD_NAME_EN")==null?"":
//                                                    obj.getString("CM_ICD_NAME_EN"));
                                            Card.setADMISSION_STATUS(obj.getString("ADMISSION_STATUS"));
                                            Card.setPATIENT_STATUS_NAME_EN(!obj.isNull("STATUS")?obj.getString("STATUS"):"");
                                            Card.setRESULT_DATE(!obj.isNull("RESULT_DATE")?obj.getString("RESULT_DATE"):"");
                                            Card.setTEST_RESULT(!obj.isNull("TEST_RESULT")?obj.getString("TEST_RESULT"):"");
                                            Carddata.add(Card);

                                        }
                                    }
                                }
                            }
                            break;
                        // طوارئ
                        case 554:
                            patient_arr = jsonObject.getJSONArray("ALL_EMRG");
                            Log.e("json", jsonObject.toString());
                            Log.e("json", "" + patient_arr.length());
                            if (patient_arr.length() > 0) {

                                for (int i = 0; i < patient_arr.length(); i++) {
                                    JSONObject obj = patient_arr.getJSONObject(i);

                                    CardviewDataModel Card = new CardviewDataModel(obj.getString("FULL_NAME_AR")
                                            , obj.isNull("USER_FULL_NAME") ? " " : obj.getString("USER_FULL_NAME")
                                            , obj.isNull("ADMISSION_ROOM_OUT_NAME") ? " " : obj.getString("ADMISSION_ROOM_OUT_NAME")
                                            , obj.isNull("ADMISSION_BED_OUT_NAME") ? " " : obj.getString("ADMISSION_BED_OUT_NAME")
                                            , obj.getString("EMP_ID")
                                            //, Integer.parseInt(obj.getString("EMP_ID"))
                                            , Integer.parseInt(obj.getString("EMP_PATREC_CODE").equals("null")?"0":obj.getString("EMP_PATREC_CODE"))
                                            , Integer.parseInt(obj.getString("EMR_SERIAL"))
                                            , obj.getString("EMR_IN_EVENT_DT")
                                            , obj.getString("HOS_NO")
                                            , obj.getString("H_NAME_AR")
                                            , obj.getString("EMP_SEX_CD")
                                            , obj.getString("SEX_NAME_AR")
                                            , obj.getString("EMP_DOB")
                                            , obj.isNull("HOS_PERMISSION") ? "" : obj.getString("HOS_PERMISSION")
                                            , obj.isNull("LOC_CODE") ? "" : obj.getString("LOC_CODE")
                                            , obj.isNull("TEST_RESULT") ? "" : obj.getString("TEST_RESULT")
                                            , obj.getString("LOC_NAME_AR")


                                    );

                                    Card.setEXAM_SPIN_CD("1");
                                    Card.setPATIENT_STATUS_NAME_EN(!obj.isNull("STATUS") ? obj.getString("STATUS") : "");
                                    Card.setRESULT_DATE(!obj.isNull("RESULT_DATE") ? obj.getString("RESULT_DATE") : "");
                                    Card.setTEST_RESULT(!obj.isNull("TEST_RESULT") ? obj.getString("TEST_RESULT") : "");
                                    Carddata.add(Card);
                                }
                            }
                            break;
                        case 1332:
                            patient_arr = jsonObject.getJSONArray("ALL_DAILYCARE");
                            Log.e("json", jsonObject.toString());
                            Log.e("json", "" + patient_arr.length());
                            if (patient_arr.length() > 0) {

                                for (int i = 0; i < patient_arr.length(); i++) {
                                    JSONObject obj = patient_arr.getJSONObject(i);
                                    CardviewDataModel Card = new CardviewDataModel(obj.getString("FULL_NAME_AR")
                                            , obj.isNull("USER_FULL_NAME") ? " " : obj.getString("USER_FULL_NAME")
                                            , obj.isNull("ADMISSION_ROOM_OUT_NAME") ? " " : obj.getString("ADMISSION_ROOM_OUT_NAME")
                                            , obj.isNull("ADMISSION_BED_OUT_NAME") ? " " : obj.getString("ADMISSION_BED_OUT_NAME")
                                            , obj.getString("MRP_ID")
                                            //, Integer.parseInt(obj.getString("EMP_ID"))
                                            , Integer.parseInt(obj.getString("MRP_PATREC_CODE"))

                                            , Integer.parseInt(obj.getString("DAILYCARE_CODE"))
                                            , obj.getString("DAILYCARE_ENTER_DATE")
                                            , obj.getString("HOS_NO")
                                            , obj.getString("H_NAME_AR")
                                            , obj.getString("MRP_SEX_CD")
                                            , obj.getString("SEX_NAME_AR")
                                            , obj.getString("MRP_DOB")
                                            , obj.isNull("HOS_PERMISSION") ? "" : obj.getString("HOS_PERMISSION")
                                            , obj.isNull("LOC_CODE") ? "" : obj.getString("LOC_CODE")
                                            , obj.isNull("TEST_RESULT") ? "" : obj.getString("TEST_RESULT")
                                            , obj.getString("LOC_NAME_AR")

                                    );
                                    Card.setEXAM_SPIN_CD("1");
                                    Card.setPATIENT_STATUS_NAME_EN(!obj.isNull("STATUS") ? obj.getString("STATUS") : "");
                                    Card.setRESULT_DATE(!obj.isNull("RESULT_DATE") ? obj.getString("RESULT_DATE") : "");
                                    Card.setTEST_RESULT(!obj.isNull("TEST_RESULT") ? obj.getString("TEST_RESULT") : "");
                                    Carddata.add(Card);
                                }
                            }
                            break;
                    }
                    



                    recyclerView.setAdapter(cardviewadapter);
                    cardviewadapter.notifyDataSetChanged();
                    imgLoading.setVisibility(View.GONE);
                    setTitle();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR" + e);
                    imgLoading.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //  inflater.inflate(R.menu.search_menu, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
        //  searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        //  super.onCreateOptionsMenu(menu, inflater);
    }

    public void resetSearch() {
        cardviewadapter = new CardviewAdapter(getContext(), Carddata);

        recyclerView.setAdapter(cardviewadapter);
    }

//    public void Touchlistner() {
//        touchListener = new RecyclerTouchListener((Activity) getContext(), recyclerView);
//        touchListener
//                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
//                    @Override
//                    public void onRowClicked(int position) {
//                        Toast.makeText(getContext(), " Not Available", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onIndependentViewClicked(int independentViewID, int position) {
//                    }
//                })
//                .setSwipeOptionViews(R.id.delete_task, R.id.edit_task, R.id.rad_task ,R.id.radiology_task)
//                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
//                    @Override
//                    public void onSwipeOptionClicked(int viewID, int position) {
//
//                        String patname = cardviewadapter.getDataSet().get(position).getPatname().toString();
//                        String patmrpid = cardviewadapter.getDataSet().get(position).getPtmrpid().toString();
//                        String indate = Carddata.get(position).getIndate().toString();
//                        int patidint = cardviewadapter.getDataSet().get(position).getPatid();
//                        int patadmcd = cardviewadapter.getDataSet().get(position).getAdmcd();
//
//                        String patid = String.valueOf(patidint);
//                        String patmrp = String.valueOf(patmrpid);
//                        String patadm = String.valueOf(patadmcd);
//                        Bundle args = new Bundle();
//                        args.putString("patname", patname);
//                        args.putString("patid", patid);
//                        args.putString("patmrp", patmrp);
//                        args.putString("indate", indate);
//                        args.putString("patadm", patadm);
//                        switch (viewID) {
//                            case R.id.delete_task:
//                                LabFragment labFragment = new LabFragment();
//                                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                                labFragment.setArguments(args);
//                                Log.d("ayat", patid);
//                                Log.d("ayat2", patname);
//
//                                ft.replace(R.id.content_frame, labFragment);
//                                ft.addToBackStack(null);
//                                ft.commit();
//                                break;
//                            case R.id.edit_task:
//                                Efile_Fragment efile_Fragment = new Efile_Fragment();
//                                FragmentTransaction efileft = getFragmentManager().beginTransaction();
//                                efile_Fragment.setArguments(args);
//                                Log.d("ayat", patid);
//                                Log.d("ayat2", patname);
//                                efileft.replace(R.id.content_frame, efile_Fragment);
//                                efileft.addToBackStack(null);
//                                efileft.commit();
//                                break;
//                            case R.id.rad_task:
//                                Toast.makeText(getContext(), "pharm Not Available", Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.radiology_task:
//                               // Toast.makeText(getContext(), "rad  Available", Toast.LENGTH_SHORT).show();
//                                RadFragment radFragment = new RadFragment();
//                                FragmentTransaction fragtrans = getFragmentManager().beginTransaction();
//                                radFragment.setArguments(args);
//                                Log.d("ayat", patid);
//                                Log.d("ayat2", patname);
//                                fragtrans.replace(R.id.content_frame, radFragment);
//                                fragtrans.addToBackStack(null);
//                                fragtrans.commit();
//                                break;
//                        }
//                    }
//                });
//    }

    public void setTitle()
    {

        switch (ScreenCode)
        {
            case 537:
                ((HomeActivity) getActivity()).setActionBarTitle("الأقسام الداخلية"+" ("+cardviewadapter.getDataSet().size()+")");
                break;
            // طوارئ
            case 554:
                ((HomeActivity) getActivity()).setActionBarTitle("مرضى الطوارئ"+" ("+cardviewadapter.getDataSet().size()+")");

                break;

            case 1332:
                ((HomeActivity) getActivity()).setActionBarTitle("العناية النهارية"+" ("+cardviewadapter.getDataSet().size()+")");

                break;
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        //recyclerView.addOnItemTouchListener(touchListener);
        // Set title bar
        setTitle();
    }


}
