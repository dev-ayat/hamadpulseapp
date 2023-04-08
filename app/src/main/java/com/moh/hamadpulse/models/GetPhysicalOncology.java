package com.moh.hamadpulse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPhysicalOncology {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("DATE_DIAG")
    @Expose
    private String dateDiag;
    @SerializedName("DATE_F_CNTCT")
    @Expose
    private String dateFCntct;
    @SerializedName("DAIG_HEALTH_CNTR_ID")
    @Expose
    private String daigHealthCntrId;
    @SerializedName("TOPOLOGY_ID")
    @Expose
    private String topologyId;
    @SerializedName("MORPHOLOGY_ID")
    @Expose
    private String morphologyId;
    @SerializedName("LATERALITY_ID")
    @Expose
    private String lateralityId;
    @SerializedName("BASIS_DIAG_ID")
    @Expose
    private String basisDiagId;
    @SerializedName("GRADE_ID")
    @Expose
    private String gradeId;
    @SerializedName("STAGE_T_ID")
    @Expose
    private String stageTId;
    @SerializedName("STAGE_N_ID")
    @Expose
    private String stageNId;
    @SerializedName("STAGE_M_ID")
    @Expose
    private String stageMId;
    @SerializedName("HOS_NO")
    @Expose
    private String hosNo;
    @SerializedName("RSK_ID")
    @Expose
    private String rskId;
    @SerializedName("DAIG_HEALTH_CNTR_O")
    @Expose
    private Object daigHealthCntrO;
    @SerializedName("CDI_NAME")
    @Expose
    private String cdiName;
    @SerializedName("CT_ICD_NAME_EN")
    @Expose
    private String ctIcdNameEn;
    @SerializedName("CM_ICD_NAME_EN")
    @Expose
    private String cmIcdNameEn;
    @SerializedName("CM_ICD_CODE")
    @Expose
    private String cmIcdCode;
    @SerializedName("CL_NAME")
    @Expose
    private String clName;
    @SerializedName("CB_NAME")
    @Expose
    private String cbName;
    @SerializedName("CG_NAME")
    @Expose
    private String cgName;
    @SerializedName("CG_DISCRIPTION")
    @Expose
    private String cgDiscription;
    @SerializedName("TMR_STAGE_T")
    @Expose
    private String tmrStageT;
    @SerializedName("TMR_STAGE_N")
    @Expose
    private String tmrStageN;
    @SerializedName("TMR_STAGE_M")
    @Expose
    private String tmrStageM;
    @SerializedName("VISIT_ID")
    @Expose
    private String visitId;

    @SerializedName("PATRIC_CD")
    @Expose
    private String patric_cd;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetPhysicalOncology() {
    }

    /**
     *
     * @param morphologyId
     * @param hosNo
     * @param daigHealthCntrId
     * @param cmIcdCode
     * @param topologyId
     * @param cdiName
     * @param stageTId
     * @param cgName
     * @param stageMId
     * @param stageNId
     * @param clName
     * @param tmrStageN
     * @param tmrStageM
     * @param tmrStageT
     * @param visitId
     * @param cmIcdNameEn
     * @param id
     * @param cgDiscription
     * @param basisDiagId
     * @param gradeId
     * @param dateDiag
     * @param ctIcdNameEn
     * @param dateFCntct
     * @param daigHealthCntrO
     * @param cbName
     * @param rskId
     * @param lateralityId
     * @param patric_cd
     */
    public GetPhysicalOncology(String id, String dateDiag, String dateFCntct,
                               String daigHealthCntrId, String topologyId,
                               String morphologyId, String lateralityId, String basisDiagId,
                               String gradeId, String stageTId, String stageNId, String stageMId,
                               String hosNo, String rskId, Object daigHealthCntrO, String cdiName,
                               String ctIcdNameEn, String cmIcdNameEn, String cmIcdCode, String clName,
                               String cbName, String cgName, String cgDiscription, String tmrStageT,
                               String tmrStageN, String tmrStageM, String visitId,String patric_cd) {
        super();
        this.id = id;
        this.dateDiag = dateDiag;
        this.dateFCntct = dateFCntct;
        this.daigHealthCntrId = daigHealthCntrId;
        this.topologyId = topologyId;
        this.morphologyId = morphologyId;
        this.lateralityId = lateralityId;
        this.basisDiagId = basisDiagId;
        this.gradeId = gradeId;
        this.stageTId = stageTId;
        this.stageNId = stageNId;
        this.stageMId = stageMId;
        this.hosNo = hosNo;
        this.rskId = rskId;
        this.daigHealthCntrO = daigHealthCntrO;
        this.cdiName = cdiName;
        this.ctIcdNameEn = ctIcdNameEn;
        this.cmIcdNameEn = cmIcdNameEn;
        this.cmIcdCode = cmIcdCode;
        this.clName = clName;
        this.cbName = cbName;
        this.cgName = cgName;
        this.cgDiscription = cgDiscription;
        this.tmrStageT = tmrStageT;
        this.tmrStageN = tmrStageN;
        this.tmrStageM = tmrStageM;
        this.visitId = visitId;
        this.patric_cd = patric_cd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDiag() {
        return dateDiag;
    }

    public void setDateDiag(String dateDiag) {
        this.dateDiag = dateDiag;
    }

    public String getDateFCntct() {
        return dateFCntct;
    }

    public void setDateFCntct(String dateFCntct) {
        this.dateFCntct = dateFCntct;
    }

    public String getDaigHealthCntrId() {
        return daigHealthCntrId;
    }

    public void setDaigHealthCntrId(String daigHealthCntrId) {
        this.daigHealthCntrId = daigHealthCntrId;
    }

    public String getTopologyId() {
        return topologyId;
    }

    public void setTopologyId(String topologyId) {
        this.topologyId = topologyId;
    }

    public String getMorphologyId() {
        return morphologyId;
    }

    public void setMorphologyId(String morphologyId) {
        this.morphologyId = morphologyId;
    }

    public String getLateralityId() {
        return lateralityId;
    }

    public void setLateralityId(String lateralityId) {
        this.lateralityId = lateralityId;
    }

    public String getBasisDiagId() {
        return basisDiagId;
    }

    public void setBasisDiagId(String basisDiagId) {
        this.basisDiagId = basisDiagId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getStageTId() {
        return stageTId;
    }

    public void setStageTId(String stageTId) {
        this.stageTId = stageTId;
    }

    public String getStageNId() {
        return stageNId;
    }

    public void setStageNId(String stageNId) {
        this.stageNId = stageNId;
    }

    public String getStageMId() {
        return stageMId;
    }

    public void setStageMId(String stageMId) {
        this.stageMId = stageMId;
    }

    public String getHosNo() {
        return hosNo;
    }

    public void setHosNo(String hosNo) {
        this.hosNo = hosNo;
    }

    public String getRskId() {
        return rskId;
    }

    public void setRskId(String rskId) {
        this.rskId = rskId;
    }

    public Object getDaigHealthCntrO() {
        return daigHealthCntrO;
    }

    public void setDaigHealthCntrO(Object daigHealthCntrO) {
        this.daigHealthCntrO = daigHealthCntrO;
    }

    public String getCdiName() {
        return cdiName;
    }

    public void setCdiName(String cdiName) {
        this.cdiName = cdiName;
    }

    public String getCtIcdNameEn() {
        return ctIcdNameEn;
    }

    public void setCtIcdNameEn(String ctIcdNameEn) {
        this.ctIcdNameEn = ctIcdNameEn;
    }

    public String getCmIcdNameEn() {
        return cmIcdNameEn;
    }

    public void setCmIcdNameEn(String cmIcdNameEn) {
        this.cmIcdNameEn = cmIcdNameEn;
    }

    public String getCmIcdCode() {
        return cmIcdCode;
    }

    public void setCmIcdCode(String cmIcdCode) {
        this.cmIcdCode = cmIcdCode;
    }

    public String getClName() {
        return clName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

    public String getCbName() {
        return cbName;
    }

    public void setCbName(String cbName) {
        this.cbName = cbName;
    }

    public String getCgName() {
        return cgName;
    }

    public void setCgName(String cgName) {
        this.cgName = cgName;
    }

    public String getCgDiscription() {
        return cgDiscription;
    }

    public void setCgDiscription(String cgDiscription) {
        this.cgDiscription = cgDiscription;
    }

    public String getTmrStageT() {
        return tmrStageT;
    }

    public void setTmrStageT(String tmrStageT) {
        this.tmrStageT = tmrStageT;
    }

    public String getTmrStageN() {
        return tmrStageN;
    }

    public void setTmrStageN(String tmrStageN) {
        this.tmrStageN = tmrStageN;
    }

    public String getTmrStageM() {
        return tmrStageM;
    }

    public void setTmrStageM(String tmrStageM) {
        this.tmrStageM = tmrStageM;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getPatric_cd() {
        return patric_cd;
    }

    public void setPatric_cd(String patric_cd) {
        this.patric_cd = patric_cd;
    }
}
