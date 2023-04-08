package com.moh.hamadpulse.Tableviewtest;

import java.util.List;

public class test {

    /**
     * id : 1
     * name : TEMP-C
     * resp : [{"VS_NAME":"TEMP-C","CREATED_ON":"12/04/2020 AM","VSVALUE":"36"},{"VS_NAME":"TEMP-C","CREATED_ON":"13/04/2020 AM","VSVALUE":"36"},{"VS_NAME":"TEMP-C","CREATED_ON":"13/04/2020 PM","VSVALUE":"36"}]
     */


    private String id;
    private String name;
    private List<RespBean> resp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RespBean> getResp() {
        return resp;
    }

    public void setResp(List<RespBean> resp) {
        this.resp = resp;
    }

    public static class RespBean {
        /**
         * VS_NAME : TEMP-C
         * CREATED_ON : 12/04/2020 AM
         * VSVALUE : 36
         */

        private String VS_NAME;
        private String CREATED_ON;
        private String VSVALUE;

        public String getVS_NAME() {
            return VS_NAME;
        }

        public void setVS_NAME(String VS_NAME) {
            this.VS_NAME = VS_NAME;
        }

        public String getCREATED_ON() {
            return CREATED_ON;
        }

        public void setCREATED_ON(String CREATED_ON) {
            this.CREATED_ON = CREATED_ON;
        }

        public String getVSVALUE() {
            return VSVALUE;
        }

        public void setVSVALUE(String VSVALUE) {
            this.VSVALUE = VSVALUE;
        }
    }
}
