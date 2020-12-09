package com.xaoyv.small.bean;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:更新</p>
 *
 * @author Xaoyv
 * date 2020/10/16 08:47
 */
public class JsonUpdateBean {

    /**
     * result : {"description":"测试2321","downloadUrl":"1213213","versionNum":5}
     * flag : 1
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private int flag;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * description : 测试2321
         * downloadUrl : 1213213
         * versionNum : 5
         */

        private String description;
        private String downloadUrl;
        private int versionNum;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public int getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(int versionNum) {
            this.versionNum = versionNum;
        }
    }
}
