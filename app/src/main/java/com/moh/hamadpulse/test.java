package com.moh.hamadpulse;

import java.util.List;

public class test {

    /**
     * COVID_SERIAL : {"lbl":"COVID SERIAL","value":"2"}
     * EMR_CODE : {"lbl":"EMR CODE","value":"1303655"}
     * OCCUPATION : {"lbl":"OCCUPATION","value":"teacher"}
     * COV_CH_DATE : {"lbl":"Date","value":"24-SEP-20"}
     * COV_CH_PCR_RESULT : {"lbl":"PCR Result","value":"1"}
     * COV_CH_PCR_RESULT_DATE : {"lbl":"PCR Result Date","value":""}
     * MY_GROUP1 : [{"lbl":"Fever","value":"1"},{"lbl":"Cough","value":"1"},{"lbl":"Fatigue","value":"0"},{"lbl":"Anorexia","value":"0"},{"lbl":"Dyspnea","value":"0"},{"lbl":"Myalgia Bone Pain","value":"0"},{"lbl":"Sore throat/Nasal congestion","value":"0"},{"lbl":"loss of smell and taste","value":"0"},{"lbl":"Nausea/vomiting/abd pain","value":"0"},{"lbl":"Diarrhea","value":"0"},{"lbl":"Headache","value":"0"},{"lbl":"Others","value":"0"}]
     * MY_GROUP2 : [{"lbl":"SMOKING HISTORY","value":"0"},{"lbl":"PMH_(NCDS)","value":"0"},{"lbl":"DM","value":"0"},{"lbl":"HTN","value":"0"},{"lbl":"CVD","value":"0"},{"lbl":"CHRONIC LUNG DISEASES","value":"0"},{"lbl":"CVA","value":"0"},{"lbl":"CKD","value":"0"},{"lbl":"CLD","value":"0"},{"lbl":"Cancer","value":"0"}]
     * PHYSICAL_EXAMINATION : [{"lbl":"Temp","value":"0"},{"lbl":"HR","value":"0"},{"lbl":"RR","value":"0"},{"lbl":"BP","value":"0"},{"lbl":"O2Sat","value":"0"}]
     * COV_CH_LABORATORY_FINDINGS : {"lbl":"LABORATORY FINDINGS","value":"cbc"}
     * COV_CH_CXR_C_T_FINDINGS : {"lbl":"CXR C_T FINDINGS","value":"normal"}
     * COV_CH_ECG_FINDINGS : {"lbl":"ECG FINDINGS","value":"nsr"}
     * SEVERITY : [{"lbl":"Asymptomatic","value":"0"},{"lbl":"Mild","value":"1"},{"lbl":"Moderste","value":"0"},{"lbl":"Servere","value":"0"},{"lbl":"critical","value":"0"}]
     * COV_CH_MINOR_SYMPTOMS : {"lbl":"minor symptoms and no moderate features","value":"0"}
     * MODERATE_SYMPTOM : [{"lbl":"fever","value":"0"},{"lbl":"Cough","value":"0"},{"lbl":"Dyspnea","value":"0"},{"lbl":"Tachypnea","value":"0"},{"lbl":"SpO2 < 97% on room air","value":"0"},{"lbl":"no signs of severe Pneumonia","value":"1"}]
     * SEVERE : [{"lbl":"Clinical signs of \tpneumonia plus one of the following","value":"0"},{"lbl":"repiratory rate> 30 \tbreaths/min","value":"0"},{"lbl":"severe respiratory distress;or","value":"0"},{"lbl":"SpO2 < 94% on room \t\tair","value":"0"}]
     * CRITICAL : [{"lbl":"Respiratory Failure","value":"0"},{"lbl":"Septic \t\tShock","value":"0"},{"lbl":"Multiorgan Failure","value":"0"}]
     * ARDS : [{"lbl":"Mild ARDS:PaO2/FiO2>200mmHg - \t\t<=300mmHg(with PEEP or CPAP <=5 cmH2O)","value":"0"},{"lbl":"Moderate:PaO2/FiO2>200mmHg - <=100mmHg(with PEEP>= \t\t5 cmH2O).b","value":"0"},{"lbl":"Severe ARDS:PaO2/FiO2 <= 100mmHg(with PEEP>= 5 \t\t\tcmH2O)","value":"0"}]
     * CRITICAL_DISEASE : [{"lbl":"altered mental \t\t\tstatus","value":"0"},{"lbl":"tachypnes","value":"0"},{"lbl":"low oxygen \t\t\tsaturation","value":"0"},{"lbl":"reduced urine \t\t\toutput","value":"0"},{"lbl":"tachycardia","value":"0"},{"lbl":"low blood pressure","value":"0"},{"lbl":"skin \t\t\tmottling","value":"0"},{"lbl":"coagulopathy","value":"0"},{"lbl":"thrombocytopenia","value":"0"},{"lbl":"metabolic \t\t\tacidosis","value":"0"},{"lbl":"high \t\t\tlacate","value":"0"},{"lbl":"hyperbiltirubinemia","value":"0"}]
     * SEPTIC_SHOCK : [{"lbl":"presistent \t\t\thypotension(MAP>=65)despite volume resuscitation","value":"0"},{"lbl":"vasopressors to maintain MAP>=65 mmHg \t\t\tand serum lactate level>2 \t\t\tmmol/L","value":"0"}]
     * COV_CH_SEPTIC_ASYMPTOMATIC : {"lbl":"Asymptomatic","value":"0"}
     * COV_CH_SEPTIC_MILD : {"lbl":"Mild","value":"1"}
     * COV_CH_SEPTIC_MODERATE : {"lbl":"Moderste","value":"0"}
     * COV_CH_SEPTIC_SEVERE : {"lbl":"Servere","value":"0"}
     * COV_CH_CRITICAL : {"lbl":"critical","value":"0"}
     * COV_CH_ASYMP_CD : {"lbl":"Patient \t\t\tStatus","value":"mild"}
     * CREATED_ON : {"value":"24-SEP-20"}
     * CREATED_BY : {"value":""}
     * UPDATE_BY : {"value":""}
     * UPDATED_ON : {"value":""}
     * USER_NAME : {"value":"سليم \t\t\tياسر سليم الاغا"}
     */

    private ReportVal COVID_SERIAL;
    private ReportVal EMP_AGE;
    private ReportVal V_ADDRESS;
    private ReportVal EMR_CODE;
    private ReportVal OCCUPATION;
    private ReportVal COV_CH_DATE;
    private ReportVal COV_CH_PCR_RESULT;
    private ReportVal COV_CH_PCR_RESULT_DATE;
    private ReportVal COV_CH_LABORATORY_FINDINGS;
    private ReportVal COV_CH_CXR_C_T_FINDINGS;
    private ReportVal COV_CH_ECG_FINDINGS;
    private ReportVal COV_CH_MINOR_SYMPTOMS;
    private ReportVal COV_CH_SEPTIC_ASYMPTOMATIC;
    private ReportVal COV_CH_SEPTIC_MILD;
    private ReportVal COV_CH_SEPTIC_MODERATE;
    private ReportVal COV_CH_SEPTIC_SEVERE;
    private ReportVal COV_CH_CRITICAL;
    private ReportVal COV_CH_ASYMP_CD;
    private CREATEDONBean CREATED_ON;
    private CREATEDBYBean CREATED_BY;
    private UPDATEBYBean UPDATE_BY;
    private UPDATEDONBean UPDATED_ON;
    private ReportVal USER_NAME;
    private List<ReportVal> MY_GROUP1;
    private List<ReportVal> MY_GROUP2;
    private List<ReportVal> PHYSICAL_EXAMINATION;
    private List<ReportVal> SEVERITY;
    private List<ReportVal> MODERATE_SYMPTOM;
    private List<ReportVal> SEVERE;
    private List<ReportVal> CRITICAL;
    private List<ReportVal> ARDS;
    private List<ReportVal> CRITICAL_DISEASE;
    private List<SEPTICSHOCKBean> SEPTIC_SHOCK;


    public ReportVal getCOVID_SERIAL() {
        return COVID_SERIAL;
    }

    public void setCOVID_SERIAL(ReportVal COVID_SERIAL) {
        this.COVID_SERIAL = COVID_SERIAL;
    }

    public ReportVal getEMP_AGE() {
        return EMP_AGE;
    }

    public void setEMP_AGE(ReportVal EMP_AGE) {
        this.EMP_AGE = EMP_AGE;
    }

    public ReportVal getV_ADDRESS() {
        return V_ADDRESS;
    }

    public void setV_ADDRESS(ReportVal v_ADDRESS) {
        V_ADDRESS = v_ADDRESS;
    }

    public ReportVal getEMR_CODE() {
        return EMR_CODE;
    }

    public void setEMR_CODE(ReportVal EMR_CODE) {
        this.EMR_CODE = EMR_CODE;
    }

    public ReportVal getOCCUPATION() {
        return OCCUPATION;
    }

    public void setOCCUPATION(ReportVal OCCUPATION) {
        this.OCCUPATION = OCCUPATION;
    }

    public ReportVal getCOV_CH_DATE() {
        return COV_CH_DATE;
    }

    public void setCOV_CH_DATE(ReportVal COV_CH_DATE) {
        this.COV_CH_DATE = COV_CH_DATE;
    }

    public ReportVal getCOV_CH_PCR_RESULT() {
        return COV_CH_PCR_RESULT;
    }

    public void setCOV_CH_PCR_RESULT(ReportVal COV_CH_PCR_RESULT) {
        this.COV_CH_PCR_RESULT = COV_CH_PCR_RESULT;
    }

    public ReportVal getCOV_CH_PCR_RESULT_DATE() {
        return COV_CH_PCR_RESULT_DATE;
    }

    public void setCOV_CH_PCR_RESULT_DATE(ReportVal COV_CH_PCR_RESULT_DATE) {
        this.COV_CH_PCR_RESULT_DATE = COV_CH_PCR_RESULT_DATE;
    }

    public ReportVal getCOV_CH_LABORATORY_FINDINGS() {
        return COV_CH_LABORATORY_FINDINGS;
    }

    public void setCOV_CH_LABORATORY_FINDINGS(ReportVal COV_CH_LABORATORY_FINDINGS) {
        this.COV_CH_LABORATORY_FINDINGS = COV_CH_LABORATORY_FINDINGS;
    }

    public ReportVal getCOV_CH_CXR_C_T_FINDINGS() {
        return COV_CH_CXR_C_T_FINDINGS;
    }

    public void setCOV_CH_CXR_C_T_FINDINGS(ReportVal COV_CH_CXR_C_T_FINDINGS) {
        this.COV_CH_CXR_C_T_FINDINGS = COV_CH_CXR_C_T_FINDINGS;
    }

    public ReportVal getCOV_CH_ECG_FINDINGS() {
        return COV_CH_ECG_FINDINGS;
    }

    public void setCOV_CH_ECG_FINDINGS(ReportVal COV_CH_ECG_FINDINGS) {
        this.COV_CH_ECG_FINDINGS = COV_CH_ECG_FINDINGS;
    }

    public ReportVal getCOV_CH_MINOR_SYMPTOMS() {
        return COV_CH_MINOR_SYMPTOMS;
    }

    public void setCOV_CH_MINOR_SYMPTOMS(ReportVal COV_CH_MINOR_SYMPTOMS) {
        this.COV_CH_MINOR_SYMPTOMS = COV_CH_MINOR_SYMPTOMS;
    }

    public ReportVal getCOV_CH_SEPTIC_ASYMPTOMATIC() {
        return COV_CH_SEPTIC_ASYMPTOMATIC;
    }

    public void setCOV_CH_SEPTIC_ASYMPTOMATIC(ReportVal COV_CH_SEPTIC_ASYMPTOMATIC) {
        this.COV_CH_SEPTIC_ASYMPTOMATIC = COV_CH_SEPTIC_ASYMPTOMATIC;
    }

    public ReportVal getCOV_CH_SEPTIC_MILD() {
        return COV_CH_SEPTIC_MILD;
    }

    public void setCOV_CH_SEPTIC_MILD(ReportVal COV_CH_SEPTIC_MILD) {
        this.COV_CH_SEPTIC_MILD = COV_CH_SEPTIC_MILD;
    }

    public ReportVal getCOV_CH_SEPTIC_MODERATE() {
        return COV_CH_SEPTIC_MODERATE;
    }

    public void setCOV_CH_SEPTIC_MODERATE(ReportVal COV_CH_SEPTIC_MODERATE) {
        this.COV_CH_SEPTIC_MODERATE = COV_CH_SEPTIC_MODERATE;
    }

    public ReportVal getCOV_CH_SEPTIC_SEVERE() {
        return COV_CH_SEPTIC_SEVERE;
    }

    public void setCOV_CH_SEPTIC_SEVERE(ReportVal COV_CH_SEPTIC_SEVERE) {
        this.COV_CH_SEPTIC_SEVERE = COV_CH_SEPTIC_SEVERE;
    }

    public ReportVal getCOV_CH_CRITICAL() {
        return COV_CH_CRITICAL;
    }

    public void setCOV_CH_CRITICAL(ReportVal COV_CH_CRITICAL) {
        this.COV_CH_CRITICAL = COV_CH_CRITICAL;
    }

    public ReportVal getCOV_CH_ASYMP_CD() {
        return COV_CH_ASYMP_CD;
    }

    public void setCOV_CH_ASYMP_CD(ReportVal COV_CH_ASYMP_CD) {
        this.COV_CH_ASYMP_CD = COV_CH_ASYMP_CD;
    }

    public CREATEDONBean getCREATED_ON() {
        return CREATED_ON;
    }

    public void setCREATED_ON(CREATEDONBean CREATED_ON) {
        this.CREATED_ON = CREATED_ON;
    }

    public CREATEDBYBean getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(CREATEDBYBean CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public UPDATEBYBean getUPDATE_BY() {
        return UPDATE_BY;
    }

    public void setUPDATE_BY(UPDATEBYBean UPDATE_BY) {
        this.UPDATE_BY = UPDATE_BY;
    }

    public UPDATEDONBean getUPDATED_ON() {
        return UPDATED_ON;
    }

    public void setUPDATED_ON(UPDATEDONBean UPDATED_ON) {
        this.UPDATED_ON = UPDATED_ON;
    }

    public ReportVal getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(ReportVal USER_NAME) {
        this.USER_NAME = USER_NAME;
    }


    public List<ReportVal> getSEVERITY() {
        return SEVERITY;
    }

    public void setSEVERITY(List<ReportVal> SEVERITY) {
        this.SEVERITY = SEVERITY;
    }

    public List<ReportVal> getMODERATE_SYMPTOM() {
        return MODERATE_SYMPTOM;
    }

    public void setMODERATE_SYMPTOM(List<ReportVal> MODERATE_SYMPTOM) {
        this.MODERATE_SYMPTOM = MODERATE_SYMPTOM;
    }

    public List<ReportVal> getSEVERE() {
        return SEVERE;
    }

    public void setSEVERE(List<ReportVal> SEVERE) {
        this.SEVERE = SEVERE;
    }

    public List<ReportVal> getCRITICAL() {
        return CRITICAL;
    }

    public void setCRITICAL(List<ReportVal> CRITICAL) {
        this.CRITICAL = CRITICAL;
    }

    public List<ReportVal> getARDS() {
        return ARDS;
    }

    public void setARDS(List<ReportVal> ARDS) {
        this.ARDS = ARDS;
    }

    public List<ReportVal> getCRITICAL_DISEASE() {
        return CRITICAL_DISEASE;
    }

    public void setCRITICAL_DISEASE(List<ReportVal> CRITICAL_DISEASE) {
        this.CRITICAL_DISEASE = CRITICAL_DISEASE;
    }

    public List<SEPTICSHOCKBean> getSEPTIC_SHOCK() {
        return SEPTIC_SHOCK;
    }

    public void setSEPTIC_SHOCK(List<SEPTICSHOCKBean> SEPTIC_SHOCK) {
        this.SEPTIC_SHOCK = SEPTIC_SHOCK;
    }

    public static class ReportVal {
        private String lbl;
        private String value;

        public String getLbl() {
            if (lbl != null)
                return lbl;
            else
                return "";
        }

        public void setLbl(String lbl) {
            this.lbl = lbl;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }




    public static class CREATEDONBean {
        /**
         * value : 24-SEP-20
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class CREATEDBYBean {
        /**
         * value :
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class UPDATEBYBean {
        /**
         * value :
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class UPDATEDONBean {
        /**
         * value :
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class USERNAMEBean {
        /**
         * value : سليم 			ياسر سليم الاغا
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class SEPTICSHOCKBean {
        /**
         * lbl : presistent 			hypotension(MAP>=65)despite volume resuscitation
         * value : 0
         */

        private String lbl;
        private String value;

        public String getLbl() {
            return lbl;
        }

        public void setLbl(String lbl) {
            this.lbl = lbl;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<ReportVal> getMY_GROUP1() {
        return MY_GROUP1;
    }

    public void setMY_GROUP1(List<ReportVal> MY_GROUP1) {
        this.MY_GROUP1 = MY_GROUP1;
    }

    public List<ReportVal> getMY_GROUP2() {
        return MY_GROUP2;
    }

    public void setMY_GROUP2(List<ReportVal> MY_GROUP2) {
        this.MY_GROUP2 = MY_GROUP2;
    }

    public List<ReportVal> getPHYSICAL_EXAMINATION() {
        return PHYSICAL_EXAMINATION;
    }

    public void setPHYSICAL_EXAMINATION(List<ReportVal> PHYSICAL_EXAMINATION) {
        this.PHYSICAL_EXAMINATION = PHYSICAL_EXAMINATION;
    }
}