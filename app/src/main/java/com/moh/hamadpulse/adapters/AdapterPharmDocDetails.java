package com.moh.hamadpulse.adapters;

import static com.moh.hamadpulse.constants.ConstShared.USER_TYPE;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.FragmentPharmAdd;
import com.moh.hamadpulse.FragmentPharmDetails;
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.FragmentMedecineEX;
import com.moh.hamadpulse.models.DocPharmacyDetails;
import com.moh.hamadpulse.models.GetNurseMedExecution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterPharmDocDetails extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<DocPharmacyDetails> mListDocPharmacyDetails;
    public ArrayList<GetNurseMedExecution> mMedExDetails;
    OnAdapterClick mOnAdapterClick;
    OnAdapterLongClick mOnAdapterLongClick;
    private Context context;
    Controller mcontroller;
    NuserMedEXecAdapter nuserMedEXecAdapter;
    FragmentPharmDetails fragmentPharmDetails;
    FragmentPharmAdd fragmentPharmAdd;
    int showdetails;
    public static boolean flag;

    public interface OnAdapterLongClick {
        void onLongClick(DocPharmacyDetails mDocPharmacyDetails);

    }


    public AdapterPharmDocDetails(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails
            , Context context, FragmentPharmAdd fragmentPharmAdd) {
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
        this.showdetails = 0;
        this.context = context;
        this.fragmentPharmAdd = fragmentPharmAdd;
    }

//    public AdapterPharmDocDetails(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails,Context context) {
//        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
//        this.showdetails=0;
//        this.context=context;
//    }


    public AdapterPharmDocDetails(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails, ArrayList<GetNurseMedExecution> mMedExDetails,
                                  Context context, FragmentPharmDetails fragmentPharmDetails) {
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
        this.mMedExDetails = mMedExDetails;
        this.context = context;
        this.showdetails = 1;
        this.fragmentPharmDetails = fragmentPharmDetails;
    }

    public ArrayList<DocPharmacyDetails> getmListDocPharmacyDetails() {
        return mListDocPharmacyDetails;
    }

    public void setmListDocPharmacyDetails(ArrayList<DocPharmacyDetails> mListDocPharmacyDetails) {
        this.mListDocPharmacyDetails = mListDocPharmacyDetails;
        notifyDataSetChanged();

    }

    public OnAdapterClick getmOnAdapterClick() {
        return mOnAdapterClick;
    }

    public void setmOnAdapterClick(OnAdapterClick mOnAdapterClick) {
        this.mOnAdapterClick = mOnAdapterClick;
    }

    public OnAdapterLongClick getmOnAdapterLongClick() {
        return mOnAdapterLongClick;
    }

    public void setmOnAdapterLongClick(OnAdapterLongClick mOnAdapterLongClick) {
        this.mOnAdapterLongClick = mOnAdapterLongClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pharm_doc_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mMyViewHolder = (MyViewHolder) holder;
        //hide medicine stop or execute if the user is pharmacist
        if (Controller.pref.getString(USER_TYPE, "").equals("5"))
            mMyViewHolder.btn_image_exc.setVisibility(View.GONE);


        DocPharmacyDetails model = mListDocPharmacyDetails.get(position);
        mMyViewHolder.txtItemName.setText(model.getITEMNAME());
        mMyViewHolder.txtInterval.setText(model.getINPINTERVAL());
        mMyViewHolder.txtRoute.setText(model.getDOSGNAME());
        mMyViewHolder.txtDosage.setText(model.getDOSENAME());
        mMyViewHolder.txtTotal.setText(model.getINPWANTEDAMOUNT());
        Log.e("status", "status" + model.getINPSTATUSCD());
        if (model.getINPSTATUSCD().equals("1")) {
            LinearLayout Itemlayout = (LinearLayout) mMyViewHolder.itemView.findViewById(R.id.Itemlayout);
            Itemlayout.setBackgroundColor(Color.RED);
        } else {

        }
        mMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    mOnAdapterClick.myClick(mListDocPharmacyDetails.get(position));
            }
        });
        mMyViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnAdapterLongClick != null)
                    mOnAdapterLongClick.onLongClick(mListDocPharmacyDetails.get(position));
                return false;
            }
        });

        if (showdetails == 0) {
            mMyViewHolder.imgDEtails.setVisibility(View.GONE);
            mMyViewHolder.btn_image_exc.setVisibility(View.GONE);
            mMyViewHolder.edit_medicine_btn.setVisibility(View.VISIBLE);
            mMyViewHolder.edit_medicine_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentPharmAdd.showDialogMedicin(model, 1);
                }
            });
            mMyViewHolder.delete_medicine_btn.setVisibility(View.VISIBLE);
            mMyViewHolder.delete_medicine_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("رسالة تأكيد")
                            .setIcon(R.drawable.testicon)
                            .setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
//                                int pos = SearchListMedicin(mDocPharmacyDetails.getINPMEDCD());
                                    model.setSelected(false);
                                    mListDocPharmacyDetails.remove(model);
                                    notifyDataSetChanged();
                                    fragmentPharmAdd.delete_medicine(model);
                                }
                            })
                            .setNeutralButton("إلغاء", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                }
            });
        }
//
//         nuserMedEXecAdapter=new NuserMedEXecAdapter(mMedExDetails,context);
//        mMyViewHolder.mednurseExlist.setLayoutManager(new LinearLayoutManager(context));
//        mMyViewHolder.mednurseExlist.setHasFixedSize(true);
//        mMyViewHolder.mednurseExlist.setAdapter(nuserMedEXecAdapter);
        mMyViewHolder.imgDEtails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Hi Hi", Toast.LENGTH_LONG).show();
                if (mMyViewHolder.EXlayout.getVisibility() == View.GONE &&
                        mMyViewHolder.rvlayout.getVisibility() == View.GONE &&
                        mMyViewHolder.NOEXlayout.getVisibility() == View.GONE) {
                    GET_EXECUTE_MED_NURSE(model.getINPMEDCD(),
                            model.getINPORDERCD(),
                            mMyViewHolder);

                } else if (mMyViewHolder.EXlayout.getVisibility() == View.VISIBLE && mMyViewHolder.rvlayout.getVisibility() == View.VISIBLE) {
                    mMyViewHolder.EXlayout.setVisibility(View.GONE);
                    mMyViewHolder.rvlayout.setVisibility(View.GONE);
                } else if (mMyViewHolder.NOEXlayout.getVisibility() == View.VISIBLE) {
                    mMyViewHolder.NOEXlayout.setVisibility(View.GONE);

                }

            }
        });
        mMyViewHolder.txtNote.setText(model.getiNPNOTE());
        //  mcontroller.Msg_DIALOG = new DialogMsg(context);
        mMyViewHolder.txtNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListDocPharmacyDetails.get(position).getiNPNOTE() != null) {
                    //   Toast.makeText(getContext(), ""+mDocPharmacyDetails.getiNPNOTE(), Toast.LENGTH_SHORT).show();

                    //  mcontroller.Msg_DIALOG.showDialog(""+mListDocPharmacyDetails.get(position).getiNPNOTE());
                } else {
                    //  mcontroller.Msg_DIALOG.showDialog("لا يوجد ملاحظات");

                }
            }
        });

        mMyViewHolder.btn_image_exc.setImageResource(
                Controller.pref.getString(USER_TYPE, "").equals("1") ||
                        Controller.pref.getString(USER_TYPE, "").equals("3") ?
                        R.drawable.stop : R.drawable.administration);
//        منع التنفيذ قبل الصرف
//        if (!model.isFromPharmacy() && showdetails != 0) {
//            mMyViewHolder.btn_image_exc.setImageResource(R.drawable.stop);
//        }
//        mMyViewHolder.btn_image_exc.setEnabled(Controller.pref.getString(USER_TYPE, "")
//                .equals("1") ||
//                Controller.pref.getString(USER_TYPE, "").equals("3") || model.isFromPharmacy());

        mMyViewHolder.btn_image_exc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Controller.pref.getString(USER_TYPE, "").equals("1") ||
                        Controller.pref.getString(USER_TYPE, "").equals("3")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("الإجراء")
                            .setIcon(R.drawable.testicon)
                            .setPositiveButton("إيقاف العلاج", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    update_medecine(model);
                                }
                            })
                            .setNeutralButton("إلغاء", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                } else {
                    ((ActivityPatient) context).CallFragment(new FragmentMedecineEX(model));
                    fragmentPharmDetails.dismiss();
                }
            }
        });
//

    }

    @Override
    public int getItemCount() {
        return mListDocPharmacyDetails.size();
    }

    public void GET_EXECUTE_MED_NURSE(String ITEM_CD, String ORDER_CD, MyViewHolder mMyViewHolder) {

        Map<String, String> map = new HashMap<>();
        map.put("P_ADM_CD", ((ActivityPatient) context).getmCardviewDataModel().getAdmcd() + "");
        //  map.put("P_ADM_CD", "6135");
        map.put("P_TREAT_CD", ITEM_CD);
        map.put("P_INMED_ORDER_CD", ORDER_CD);
        Log.e("ayatEX", map.toString());
        MyRequest.makeRquest(context, Controller.GET_MED_EXECUTION_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                JSONObject mJSONObject = null;
                try {
                    mJSONObject = new JSONObject(response);
                    JSONArray jsonArray = mJSONObject.getJSONArray("MED_EXE_CUR");
                    //////////////////
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<GetNurseMedExecution>>() {
                    }.getType();
                    mMedExDetails = gson.fromJson(jsonArray.toString(), type);
                    Log.e("ayat44", mMedExDetails.size() + "");
                    Log.e("ayat43", "ayyyat" + mMedExDetails);
                    /////////////////

                    nuserMedEXecAdapter = new NuserMedEXecAdapter(mMedExDetails, context);
                    mMyViewHolder.mednurseExlist.setLayoutManager(new LinearLayoutManager(context));
                    mMyViewHolder.mednurseExlist.setHasFixedSize(true);
                    mMyViewHolder.mednurseExlist.setAdapter(nuserMedEXecAdapter);
                    if (mMedExDetails.size() > 0) {
                        mMyViewHolder.EXlayout.setVisibility(View.VISIBLE);
                        mMyViewHolder.rvlayout.setVisibility(View.VISIBLE);
                        int sum = 0;
                        for (GetNurseMedExecution item : mMedExDetails) {
                            sum += Integer.parseInt(item.getInmedDose());
                        }

                    } else {
                        mMyViewHolder.NOEXlayout.setVisibility(View.VISIBLE);

                    }
                    nuserMedEXecAdapter.setNurseMedExecutionList(mMedExDetails);
                    nuserMedEXecAdapter.notifyDataSetChanged();
                    Log.e("ayat42", nuserMedEXecAdapter.getItemCount() + "");
                    // getIcdConstArrayList.add(getIcdConstArrayList);
                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemName;
        private TextView txtInterval;
        private TextView txtDosage;
        private TextView txtRoute;
        private TextView txtTotal;
        private TextView txtNote;
        private ImageView imgDEtails, btn_image_exc;
        private RecyclerView mednurseExlist;
        private LinearLayout EXlayout, rvlayout, NOEXlayout;
        private MaterialCardView cardView;
        private ImageButton edit_medicine_btn, delete_medicine_btn;

        public MyViewHolder(View view) {
            super(view);
            txtItemName = view.findViewById(R.id.txtItemName);
            txtInterval = view.findViewById(R.id.txtInterval);
            txtDosage = view.findViewById(R.id.txtDosage);
            txtRoute = view.findViewById(R.id.txtRoute);
            txtTotal = view.findViewById(R.id.txtTotal);
            txtNote = view.findViewById(R.id.txtNote);
            imgDEtails = view.findViewById(R.id.imgDEtails);
            imgDEtails.setVisibility(flag?View.VISIBLE:View.GONE);
            mednurseExlist = view.findViewById(R.id.rvMedDEtails);
            EXlayout = view.findViewById(R.id.EXlayout);
            rvlayout = view.findViewById(R.id.rvlayout);
            NOEXlayout = view.findViewById(R.id.NOEXlayout);
            btn_image_exc = view.findViewById(R.id.btn_image_exc);
            btn_image_exc.setVisibility(flag?View.VISIBLE:View.GONE);
            cardView = view.findViewById(R.id.pharm_doc_cardview);
            cardView = view.findViewById(R.id.pharm_doc_cardview);
            edit_medicine_btn =
                    view.findViewById(R.id.edit_medicine_btn);
            delete_medicine_btn =
                    view.findViewById(R.id.delete_medicine_btn);

        }

    }

    public void update_medecine(DocPharmacyDetails mDocPharmacyDetails) {

        fragmentPharmDetails.imgLoading.setVisibility(View.VISIBLE);
        HashMap<String, String> map = new HashMap<>();
        map.put("INP_DETAIL_CODE", mDocPharmacyDetails.getINPDETAILCODE());
        map.put("INP_ORDER_CD", mDocPharmacyDetails.getINPORDERCD());

        map.put("TRANS_SCREEN_CD_IN", fragmentPharmDetails.fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) context).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "صيدلية");

        MyRequest.makeRquest(context, Controller.UPDATE_MEDICINE_STATUS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    int res = mJSONObject.getInt("P_RESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تم إيقاف العلاج بنجاح");
                            }
                        }, 2000);
                    }
                    if (res == 2) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("العلاج مصروف فلا يمكن إيقافه");
                            }
                        }, 2000);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                fragmentPharmDetails.imgLoading.setVisibility(View.GONE);
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, context);
                fragmentPharmDetails.imgLoading.setVisibility(View.GONE);
            }
        });
    }

}