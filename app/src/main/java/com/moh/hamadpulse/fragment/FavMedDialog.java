package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.Fav_Med_Adapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.FavMedModel;
import com.moh.hamadpulse.models.PharmMedModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavMedDialog extends DialogFragment {

    RecyclerView all_med_rv;
    MaterialButton save_fav_btn;
    EditText med_search_ed;
    Fav_Med_Adapter adapter;
    ArrayList<FavMedModel> favMedModels;
    ViewUserFavMed viewUserFavMed;
    public FavMedDialog(ViewUserFavMed viewUserFavMed) {
        this.viewUserFavMed=viewUserFavMed;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_med_dialog, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        all_med_rv = view.findViewById(R.id.all_med_rv);
        save_fav_btn = view.findViewById(R.id.save_fav_btn);
        med_search_ed = view.findViewById(R.id.med_search_ed);
        med_search_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String med_name = charSequence.toString().trim();
                if (med_name.trim().length() > 2)
                    getAllMed(med_name);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getFavMed();
        save_fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_fav_med();
            }
        });
    }

    private void getFavMed() {
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "getfavmed");
        MyRequest.makeRquest(getContext(), Controller.GET_FAV_MED_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("FAV_MEDS");

                            //////////////////
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<FavMedModel>>() {
                            }.getType();
                            favMedModels = gson.fromJson(jsonArray.toString(),
                                    type);

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        if (getActivity() != null)
                            Controller.view_error(error, getContext());
                    }
                });
    }

    private void getAllMed(String med_name) {
        Map<String, String> map = new HashMap<>();
        map.put("P_NAME", med_name);
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "getfavmed");
        MyRequest.makeRquest(getContext(), Controller.GET_ALL_PHARMS_MED_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("PHARM_MED_CUR");
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<PharmMedModel>>() {
                            }.getType();
                            ArrayList<PharmMedModel> list = gson.fromJson(jsonArray.toString(),
                                    type);
                            all_med_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            if (favMedModels != null) {
                                adapter = new Fav_Med_Adapter(list, favMedModels);
                                all_med_rv.setAdapter(adapter);
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                        if (getActivity() != null)
                            Controller.view_error(error, getContext());
                    }
                });

    }

    void add_fav_med() {
        ArrayList<PharmMedModel> pList = adapter.getNewItem(), rList = adapter.getRemovedItem();
        int size = adapter.getNewItem().size(), re_size = rList.size();
        ;
        Log.d("size_new_list", pList.size() + "");
        if (size > 0) {
            Map<String, String> map = new HashMap<>();

            map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")));
            for (int i = 0; i < size; i++) {
                map.put("items[" + i + "][P_MED_CD]", pList.get(i).getMedMCode());
                map.put("items[" + i + "][P_MED_NAME]", pList.get(i).getItemName());
            }
            map.put("TRANS_SCREEN_CD_IN", "");
            map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
            map.put("TRANS_ACTION_CD_IN", "1");
            map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                    getActivity()).getmCardviewDataModel().getPatid() + "");
            map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
            map.put("TRANS_DESCRIPTION_IN", "Add new Diabetic");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
            Log.e("ventmap", map.toString());

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.ADD_TO_MED_FAV_URL, map,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int res = response.getInt("P_RESULT");
                                if (res == 1) {

                                    if (re_size > 0)
                                        remove_items(rList);
                                    else {
                                        process_done();
//                                        Controller.Msg_DIALOG.showDialog("تمت العملية بنجاح");

                                    }


                                } else {
                                    Toast.makeText(getContext(), "لم تتم العملية بنجاح", Toast.LENGTH_SHORT).show();
//                                    Controller.Msg_DIALOG.showDialog("لم تتم العملية بنجاح");
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("json", "ERROR");
                            }
//                mInterfacePatient.showLoading(false);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    Controller.view_error(volleyError, getContext());
//                mInterfacePatient.showLoading(false);
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
        else if (re_size > 0)
            remove_items(rList);
        else
            Toast.makeText(getContext(), "لا يوجد تغيرات ليتم حفظها", Toast.LENGTH_SHORT).show();
//            Controller.Msg_DIALOG.showDialog("لا يوجد تغيرات ليتم حفظها");
    }
    void process_done(){
        Toast.makeText(getContext(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();
        viewUserFavMed.getAllFavMed();
        getDialog().dismiss();
    }
    private void remove_items(ArrayList<PharmMedModel> rList) {
        Map<String, String> map = new HashMap<>();
        int size = rList.size();
        map.put("P_USER_ID", (Controller.pref.getString("USER_ID", "")));
        for (int i = 0; i < size; i++) {
            map.put("items[" + i + "][P_MED_CD]", rList.get(i).getMedMCode());
            map.put("items[" + i + "][P_MED_NAME]", rList.get(i).getItemName());
        }
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient)
                getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Add new Diabetic");
//        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        Log.e("ventmap", map.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.REMOVE_FROM_MED_FAV_URL, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("P_RESULT");
                            if (res == 1) {
//                                Controller.Msg_DIALOG.showDialog("تمت العملية بنجاح");
                                process_done();
                            } else {
//                                Controller.Msg_DIALOG.showDialog("لم تتم العملية بنجاح");
                                Toast.makeText(getContext(), "لم تتم العملية بنجاح", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("json", "ERROR");
                        }
//                mInterfacePatient.showLoading(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
//                mInterfacePatient.showLoading(false);
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
    public void onResume() {
        super.onResume();
//        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
//                if ((keyEvent.getKeyCode() ==  android.view.KeyEvent.KEYCODE_BACK))
//                {
//                    // To dismiss the fragment when the back-button is pressed.
//                    dismiss();
//                    return true;
//                }
//                // Otherwise, do nothing else
//                else return false;
//            }
//        });
}}