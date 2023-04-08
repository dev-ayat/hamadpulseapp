package com.moh.hamadpulse.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.LabCategoryDataModel;
import com.moh.hamadpulse.models.LabTestDataModel;
import com.moh.hamadpulse.models.Labtestcultureantimodel;
import com.moh.hamadpulse.models.Labtestculturemodel;

import java.util.ArrayList;

public class AdapterResultLab extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int ITEM_TYPE_RESULT = 0;
    public final int ITEM_LAB_TEST = 1;
    public final int ITEM_HEADER_LAB_TEST = 2;
    public final int ITEM_LAB_CULT = 3;
    public final int ITEM_LAB_CULT_DETAILS = 4;
    public final int ITEM_LAB_CULT_DETAILS_HEADERS = 5;
    private ArrayList<Object> mListObject;

    public AdapterResultLab(ArrayList<Object> mListObject) {
        this.mListObject = mListObject;

    }

    @Override
    public int getItemViewType(int position) {
        if (mListObject.get(position) instanceof LabCategoryDataModel)
            return ITEM_TYPE_RESULT;
        else if (mListObject.get(position) instanceof LabTestDataModel) {
            if (((LabTestDataModel) mListObject.get(position)).getHeader() != null)
                return ITEM_HEADER_LAB_TEST;
            else
                return ITEM_LAB_TEST;
        } else if (mListObject.get(position) instanceof Labtestculturemodel) {
            return ITEM_LAB_CULT;
        } else if (mListObject.get(position) instanceof Labtestcultureantimodel) {
            if (((Labtestcultureantimodel) mListObject.get(position)).getHeader() != null)
                return ITEM_LAB_CULT_DETAILS_HEADERS;
            else
                return ITEM_LAB_CULT_DETAILS;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Log.e("MYERROR","viewType="+viewType);
        switch (viewType)
        {
            case ITEM_TYPE_RESULT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_res_cat_row, parent, false);
                return new ViewHolderResult(view);
            case ITEM_LAB_TEST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_res_test_row, parent, false);
                return new ViewHolderLabTest(view);
            case ITEM_HEADER_LAB_TEST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header_lab_test, parent, false);
                return new LaborderCardviewAdapter.ViewHolderEmpty(view);
            case ITEM_LAB_CULT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.culture_result, parent, false);
                return new ViewHolderLabCult(view);
            case ITEM_LAB_CULT_DETAILS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cult_details, parent, false);
                return new ViewHolderLabCultDetails(view);
            case ITEM_LAB_CULT_DETAILS_HEADERS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header_cult_details, parent, false);
                return new ViewHolderLabCultDetails(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("list_size", mListObject.size() + "");
        switch (holder.getItemViewType()) {
            case ITEM_TYPE_RESULT:
                final LabCategoryDataModel Carddata = (LabCategoryDataModel) mListObject.get(position);
                ViewHolderResult mViewHolderResult = (ViewHolderResult) holder;
                mViewHolderResult.txtcatname.setText(Carddata.getCATEGORY_NAME());
                mViewHolderResult.txtcatid.setText(String.valueOf(Carddata.getCATEGORY_ID()));
                mViewHolderResult.txtcatstatus.setText(Carddata.getCATSTATUS());
                mViewHolderResult.txtcatstatid.setText(String.valueOf(Carddata.getCATSTATUSID()));
                mViewHolderResult.txtgroupname.setText(Carddata.getCATEGORY_NAME());
                mViewHolderResult.txtgroupid.setText(Carddata.getCATGROUPID());
                break;

            case ITEM_LAB_TEST:
                double result = 0;
                boolean flag = false;
                LabTestDataModel mLabTestDataModel = (LabTestDataModel) mListObject.get(position);
                ViewHolderLabTest mViewHolderLabTest = (ViewHolderLabTest) holder;
                mViewHolderLabTest.txttestname.setText(mLabTestDataModel.getTxttestname());
                //mViewHolderLabTest.lbtestunit.setText(mLabTestDataModel.getLbtestunit());
//                if (!mLabTestDataModel.getTxttestvalue().isEmpty()) {
//                    try {
//                        result = Double.parseDouble(mLabTestDataModel.getTxttestvalue());
//                        flag=true;
//                    }catch (Exception e){
//                        Log.e("exception",e.toString());
//                    }
//
//                }
                TextView txttestvalue = mViewHolderLabTest.txttestvalue;
                txttestvalue.setText(mLabTestDataModel.getTxttestvalue());
                if (mLabTestDataModel.isFrom_To()) {
//                    if(flag&&(result>Double.parseDouble(mLabTestDataModel.getLbtestmaxref())||
//                            result<Double.parseDouble(mLabTestDataModel.getLbtestminref())))
//                        txttestvalue.setBackgroundColor(txttestvalue.getContext().getResources()
//                                .getColor(R.color.colorCancel));
                    mViewHolderLabTest.lbtestminref.setText(mLabTestDataModel.getLbtestminref());
                    mViewHolderLabTest.lbtestmaxref.setText(mLabTestDataModel.getLbtestmaxref());
                } else {
                    mViewHolderLabTest.reference_disc_from_to.setVisibility(View.GONE);
                    mViewHolderLabTest.reference_disc_layout.setVisibility(View.VISIBLE);
                    mViewHolderLabTest.txtreference_disc.setText(mLabTestDataModel.getLbtestminref());
                }
                break;

            case ITEM_LAB_CULT_DETAILS:
                Labtestcultureantimodel mLabtestcultureantimodel = (Labtestcultureantimodel) mListObject.get(position);
                ViewHolderLabCultDetails mViewHolderLabCultDetails = (ViewHolderLabCultDetails) holder;
                mViewHolderLabCultDetails.txt_antibiotic.setText(mLabtestcultureantimodel.getTEST_NAME_EN());
                mViewHolderLabCultDetails.txt_antiA.setText(mLabtestcultureantimodel.getCULTURE_A_ABBR());
                mViewHolderLabCultDetails.txt_antiB.setText(mLabtestcultureantimodel.getCULTURE_B_ABBR());
                mViewHolderLabCultDetails.txt_antiC.setText(mLabtestcultureantimodel.getCULTURE_C_ABBR());
                break;
            case ITEM_LAB_CULT:
                //    LinearLayout testlayoutheader = (LinearLayout)((MyViewHolder2) viewHolder).itemView.findViewById(R.id.testlayout_header);
                //   testlayoutheader.setVisibility(View.GONE);
                Log.e("MYERROR","POS"+position);
                Labtestculturemodel cultdata = (Labtestculturemodel) mListObject.get(position);
                ViewHolderLabCult mViewHolderLabCult = (ViewHolderLabCult) holder;
                mViewHolderLabCult.culturname.setText(cultdata.getCulturname());
                mViewHolderLabCult.cultureres.setText(cultdata.getCultureres());
                mViewHolderLabCult.orgA.setText(cultdata.getOrgA());
                mViewHolderLabCult.orgAcount.setText(cultdata.getOrgAcount());
                mViewHolderLabCult.orgB.setText(cultdata.getOrgB());
                mViewHolderLabCult.orgBcount.setText(cultdata.getOrgBcount());
                mViewHolderLabCult.orgC.setText(cultdata.getOrgC());
                mViewHolderLabCult.orgCcount.setText(cultdata.getOrgCcount());
                mViewHolderLabCult.statcultcd.setText(cultdata.getStatcultcd());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mListObject.size();
    }

    public static class ViewHolderResult extends RecyclerView.ViewHolder {

        TextView txtcatname, txtcatstatus, txtcatid, txtcatstatid, txtgroupname, txtgroupid;
        public ViewHolderResult(View itemView) {
            super(itemView);
            this.txtcatname = itemView.findViewById(R.id.txtcatname);
            this.txtcatid = itemView.findViewById(R.id.txtcatid);
            this.txtcatstatus = itemView.findViewById(R.id.txtcatstatus);
            this.txtcatstatid = itemView.findViewById(R.id.txtcatstatid);
            this.txtgroupname = itemView.findViewById(R.id.txtgroupname);
            this.txtgroupid = itemView.findViewById(R.id.txtgroupid);
        }
    }

    public static class ViewHolderLabTest extends RecyclerView.ViewHolder {

        private TextView txttestname, txttestvalue, lbtestminref,
                lbtestmaxref, txttestunit, txtreference_disc;
        private LinearLayout reference_disc_layout, reference_disc_from_to;

        public ViewHolderLabTest(View itemView) {
            super(itemView);

            txttestname = itemView.findViewById(R.id.txttestname);
            txttestvalue = itemView.findViewById(R.id.txttestvalue);
            lbtestminref = itemView.findViewById(R.id.lbtestminref);
            lbtestmaxref = itemView.findViewById(R.id.lbtestmaxref);
            txttestunit = itemView.findViewById(R.id.txttestunit);
            txtreference_disc = itemView.findViewById(R.id.txtreference_disc);
            reference_disc_layout = itemView.findViewById(R.id.reference_disc_layout);
            reference_disc_from_to = itemView.findViewById(R.id.reference_disc_from_to);
        }
    }

    public static class ViewHolderLabCultDetails extends RecyclerView.ViewHolder {

        TextView txt_antibiotic, txt_antiA, txt_antiB, txt_antiC;


        public ViewHolderLabCultDetails(View itemView) {
            super(itemView);
            this.txt_antibiotic = (TextView) itemView.findViewById(R.id.txt_antibiotic);
            this.txt_antiA = (TextView) itemView.findViewById(R.id.txt_antiA);
            this.txt_antiB = (TextView) itemView.findViewById(R.id.txt_antiB);
            this.txt_antiC = (TextView) itemView.findViewById(R.id.txt_antiC);

        }
    }

    public static class ViewHolderLabCult extends RecyclerView.ViewHolder {

        TextView culturname, cultureres, orgA, orgAcount, orgB, orgBcount, orgC, orgCcount, statcultcd;
        RecyclerView innercultRecyclerView;

        public ViewHolderLabCult(View itemView) {
            super(itemView);
            this.culturname = (TextView) itemView.findViewById(R.id.txt_culturname);
            this.cultureres = (TextView) itemView.findViewById(R.id.txt_cultureres);
            this.orgA = (TextView) itemView.findViewById(R.id.txt_orgA);
            this.orgAcount = (TextView) itemView.findViewById(R.id.txt_orgAcount);
            this.orgB = (TextView) itemView.findViewById(R.id.txt_orgB);
            this.orgBcount = (TextView) itemView.findViewById(R.id.txt_orgBcount);
            this.orgC = (TextView) itemView.findViewById(R.id.txt_orgC);
            this.orgCcount = (TextView) itemView.findViewById(R.id.txt_orgCcount);
            this.statcultcd = (TextView) itemView.findViewById(R.id.txt_cultureresid);
            this.innercultRecyclerView = itemView.findViewById(R.id.innercultRecyclerView);

        }
    }
}
