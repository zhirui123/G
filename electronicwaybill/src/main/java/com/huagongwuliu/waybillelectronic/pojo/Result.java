package com.huagongwuliu.waybillelectronic.pojo;

import com.huagongwuliu.waybillelectronic.constant.ErrorCode;
import lombok.Data;
import lombok.ToString;

/**
 * 接口返回数据模型
 * 比Result类多了个result_data属性
 * Created by lifuxiang on 2016/9/21.
 */

@Data
@ToString
public class Result {

    /**
     * 结果码：0:成功；1:数据为空，-1程序出错
     */
    private int result_code ;

    /**
     * 结果描述
     * 成功：空或提示成功的信息
     * 失败：失败原因
     */
    private String result_msg;

    /**
     * 结果数据
     */
    private Object result_data;


    public Result(){}

    public Result(int result_code, String result_msg, Object result_data){
        this.result_code = result_code;
        this.result_msg = result_msg;
        this.result_data = result_data;
    }

    /**
     * 直接传枚举对象
     * @param errorCode
     * @param result_data
     */
    public Result(ErrorCode errorCode, Object result_data){
        this.result_code = errorCode.getCode();
        this.result_msg = errorCode.getMsg();
        this.result_data = result_data;
    }

    /**
     * 出现错误时，统一在这里设置返回结果为null
     * @param errorCode
     */
    public Result(ErrorCode errorCode){
        this.result_code = errorCode.getCode();
        this.result_msg = errorCode.getMsg();
        this.result_data = null;
    }

    /**
     * 根据传入的参数返回，不用提前订阅枚举
     * @param errorCode
     * @param errorMsg
     */
    public Result(int errorCode, String errorMsg){
        this.result_code = errorCode;
        this.result_msg = errorMsg;
    }
}
