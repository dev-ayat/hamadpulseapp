package com.moh.hamadpulse.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.UserFavMedAdapter;
import com.moh.hamadpulse.models.GetMedicineConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewUserFavMed  extends DialogFragment {

   RecyclerView rv;
   MaterialButton btn;
    ViewUserFavMed viewUserFavMed;
    public ViewUserFavMed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_user_fav_med, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewUserFavMed=this;
        rv=view.findViewById(R.id.rv_fav_med);
        btn=view.findViewById(R.id.add_user_fav_med);
//        getAllFavMed();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavMedDialog dialog = new FavMedDialog(viewUserFavMed);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "FavMedFragment");
            }
        });
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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        getAllFavMed();
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllFavMed();
    }

    public void getAllFavMed() {
        Map<String, String> map = new HashMap<>();
        map.put("P_USER_ID",  (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_SCREEN_CD_IN", "");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "get user fav med");
        MyRequest.makeRquest(getContext(), Controller.GET_USER_FAV_MED_URL,
                map, new MyRequest.CallBack() {
                    @Override
                    public void Result(String response) {

                        JSONObject mJSONObject = null;
                        try {
                            mJSONObject = new JSONObject(response);
                            JSONArray jsonArray = mJSONObject.getJSONArray("USER_FAV_MED");
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<GetMedicineConst>>() {
                            }.getType();
                            ArrayList<GetMedicineConst> list = gson.fromJson(jsonArray.toString(),
                                    type);
                            rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            UserFavMedAdapter adapter = new UserFavMedAdapter(list);
                            rv.setAdapter(adapter);
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
}