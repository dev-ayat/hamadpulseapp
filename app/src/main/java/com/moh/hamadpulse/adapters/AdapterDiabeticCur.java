package com.moh.hamadpulse.adapters;

import static com.moh.hamadpulse.Controller.mInterfacePatient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.DiabeticCurModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterDiabeticCur extends RecyclerView.Adapter<AdapterDiabeticCur.DiabeticCurViewHolder> {
    ArrayList<DiabeticCurModel> arrrayList_DiabeticCur = new ArrayList<>();

    public AdapterDiabeticCur(ArrayList<DiabeticCurModel> arrrayList_DiabeticCur) {
        this.arrrayList_DiabeticCur = arrrayList_DiabeticCur;
    }

    public ArrayList<DiabeticCurModel> getArrrayList_DiabeticCur() {
        return arrrayList_DiabeticCur;
    }

    public void setArrrayList_DiabeticCur(ArrayList<DiabeticCurModel> arrrayList_DiabeticCur) {
        this.arrrayList_DiabeticCur = arrrayList_DiabeticCur;
    }

    @NonNull
    @Override
    public DiabeticCurViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiabeticCurViewHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_diabetic_cur, parent, false));

    }
//
    @Override
    public void onBindViewHolder(@NonNull DiabeticCurViewHolder holder, int position) {
        DiabeticCurModel diabeticCurModel = arrrayList_DiabeticCur.get(position);
        String[] date = diabeticCurModel.getInpDiabeticCreatedOn().split(" ");
        holder.txtDate.setText(date[0] + (date.length > 1 ? "\n" + date[1] : ""));
        holder.txtBS_DL.setText(diabeticCurModel.getInpDiabeticBsDl());
        holder.txtInsulinDase.setText(diabeticCurModel.getInpDiabeticInsulinDose());
        holder.txtInsulinType.setText(diabeticCurModel.getInpDiabeticInsulinTypeName());
        holder.txtNote.setText(diabeticCurModel.getInpDiabeticNote());
        holder.txtCreatedBy.setText(diabeticCurModel.getUserFullName());

        ImageButton delete_btn=holder.delete_d_btn;
        delete_btn.setVisibility(diabeticCurModel.getUSER_ID().equals((Controller.pref.getString("USER_ID", "")))?View.VISIBLE:
                View.GONE);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowDeleteMsg(diabeticCurModel,holder.itemView.getContext());
            }
        });

    }
    public void ShowDeleteMsg(DiabeticCurModel diabeticCurModel, Context cx) {

        AlertDialog.Builder builder = new AlertDialog.Builder(cx)
                .setTitle(" هل تريد بالتأكيد الحذف ؟؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendDeletOrder(diabeticCurModel,cx);

                    }


                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void sendDeletOrder(DiabeticCurModel diabeticCurModel,Context cx) {
        Map<String, String> map = new HashMap<>();
        map.put("P_DIABTIC_CD", diabeticCurModel.getInpDiabeticCd());
        map.put("P_ADM_CD", diabeticCurModel.getInpDiabeticAdmCd());
        map.put("P_PATRIC_CD", diabeticCurModel.getInpDiabeticPatrecCd());
//        map.put("P_PATRIC_CD","191211");
//        map.put("P_ADM_CD","412565");
        map.put("TRANS_SCREEN_CD_IN", "37");
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "5");
        map.put("TRANS_DOCUMENT_CD_IN",  diabeticCurModel.getInpDiabeticPatrecCd());
//        map.put("TRANS_DOCUMENT_CD_IN","191211");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "Delete Diabetic");
        Log.d("map",map.toString());
        mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DELETE_INP_DIABETIC_CHART_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1) {
                        Toast.makeText(cx, "تم عملية الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        arrrayList_DiabeticCur.remove(diabeticCurModel);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(cx, "لم تتم عملية الحذف", Toast.LENGTH_SHORT).show();

                    }
                    mInterfacePatient.showLoading(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, cx);
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
    public int getItemCount() {
        return arrrayList_DiabeticCur.size();
    }

    class DiabeticCurViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate;
        private TextView txtBS_DL;
        private TextView txtInsulinDase;
        private TextView txtInsulinType;
        private TextView txtNote;
        private TextView txtCreatedBy;
        private ImageButton delete_d_btn;

        public DiabeticCurViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDateTimeDetails);
            txtBS_DL = itemView.findViewById(R.id.txtIv_Fluid);
            txtInsulinDase = itemView.findViewById(R.id.txtOral);
            txtInsulinType = itemView.findViewById(R.id.txtInsulinType);
            txtNote = itemView.findViewById(R.id.btnShow_Details);
            txtCreatedBy = itemView.findViewById(R.id.txtCreatedBy);
            delete_d_btn = itemView.findViewById(R.id.delete_d_btn);
        }
    }
}
