package com.huagongwuliu.waybillelectronic.pojo;

public class ResultInfo {

    private Integer result_code;//后端返回结果正常为true，发生异常返回false
    private Object result_data;//后端返回结果数据对象
    private String result_msg;//发生异常的错误消息



    //无参构造方法
    public ResultInfo() {
    }
    public ResultInfo(Integer result_code) {
        this.result_code = result_code;
    }
    /**
     * 有参构造方法
     * @param result_code
     * @param errorMsg
     */
    public ResultInfo(Integer result_code, String errorMsg) {
        this.result_code = result_code;
        this.result_data = errorMsg;
    }
    /**
     * 有参构造方法
     * @param result_code
     * @param result_data
     * @param result_msg
     */
    public ResultInfo(Integer result_code, Object result_data, String result_msg) {
        this.result_code = result_code;
        this.result_data = result_data;
        this.result_msg = result_msg;
    }


    public Integer getResult_code() {
        return result_code;
    }

    public void setResult_code(Integer result_code) {
        this.result_code = result_code;
    }

    public Object getResult_data() {
        return result_data;
    }

    public void setResult_data(Object result_data) {
        this.result_data = result_data;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "result_code=" + result_code +
                ", result_data=" + result_data +
                ", result_msg='" + result_msg + '\'' +
                '}';
    }
}
