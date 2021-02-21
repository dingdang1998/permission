package com.oarage.lsdc.commons.entity;

/**
 * 返回参数接口，用于对不同协议进行数据回复用.
 *
 * @version $Id: IParameter.java 28079 2009-07-23 08:32:58Z fanhongbo $
 */
public interface IParameter {
    public static int TYPE_NOUPDATE = 0;// 无升级
    public static int TYPE_PARAMETER = 1;// 参数变更
    public static int TYPE_UPDATE = 2;// 升级
    public static final String PACK_EQULS_SIGN = "=";
    public static final String PACK_COLON = ":";
    public static final String PACK_SPACE = " ";
    public static final String PACK_NEW_LINE = "\n";
    public static final String SUCCESS = "Success";
    public static final String OVER = "over";
    public static final String split = "||";
    public static final String oilyesFlag = "Y";
    public static final String oilnoFlag = "N";
    public static final String oilCode = "n";// 油罐油品设置
    public static final String interval = "i";// 实时上传间隔时间
    public static final String bytes = "bytes";// 字节数

    /**
     * 将升级询问信息的反馈下发到站端
     *
     * @return
     */
    public String pack();
}
