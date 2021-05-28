package com.oket.getdata.util;

import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: getdata
 * @description: 字符串操作
 * @author: dzp
 * @create: 2021-05-13 16:08
 **/
public class CustomStringUtil {
    /**
     * 斜线
     */
    public static final String SLASH = "/";
    /**
     * id.txt
     */
    public static final String ID_TXT = "id.txt";

    public static final String INVENTORY = "inventory";
    public static final String HOSE_OUT = "hoseout";

    /**
     * csv文件后缀
     */
    public static final String CSV = ".csv";

    /**
     * mapping.txt
     */
    public static final String MAPPING_TXT = "mapping.txt";

    /**
     * 拼接油站文件夹路径
     *
     * @param rootPath
     * @param dataBaseConfigInfoEntity
     * @return
     */
    public static String getStationFolderPath(String rootPath, DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {
        StringBuffer stringBuffer = new StringBuffer(rootPath)
                .append(SLASH)
                .append(dataBaseConfigInfoEntity.getStationShortName())
                //把油站数据库的ip拼上
                .append((dataBaseConfigInfoEntity.getIpAndPort().split(":"))[0]);
        return stringBuffer.toString();
    }

    /**
     * 拼接id.txt文件路径
     *
     * @param stationFolderPath 油站文件夹路径
     * @param oilCanNo          罐号
     * @return
     */
    public static String getIdTxtPath(String stationFolderPath, String oilCanNo) {
        StringBuffer stringBuffer = new StringBuffer(stationFolderPath).append(SLASH)
                .append(oilCanNo).append(SLASH)
                .append(ID_TXT);
        return stringBuffer.toString();
    }

    /**
     * 拼接inventory.csv文件路径
     *
     * @param stationFolderPath 油站文件夹路径
     * @param oilCanNo          罐号
     * @return
     */
    public static String getInventoryCsvPath(String stationFolderPath, String oilCanNo) {
        StringBuffer stringBuffer = new StringBuffer(stationFolderPath).append(SLASH)
                .append(oilCanNo).append(SLASH)
                .append(INVENTORY).append(getFileDateSuffix()).append(CSV);
        return stringBuffer.toString();
    }

    /**
     * 拼接hoseout.csv文件路径
     *
     * @param stationFolderPath 油站文件夹路径
     * @param oilCanNo          罐号
     * @return
     */
    public static String getHoseOutCsvPath(String stationFolderPath, String oilCanNo) {
        StringBuffer stringBuffer = new StringBuffer(stationFolderPath).append(SLASH)
                .append(oilCanNo).append(SLASH)
                .append(HOSE_OUT).append(getFileDateSuffix()).append(CSV);
        return stringBuffer.toString();
    }

    /**
     * 拼接mapping.txt文件路径
     *
     * @param stationFolderPath 油站文件夹路径
     * @return
     */
    public static String getMappingTxtPath(String stationFolderPath) {
        StringBuffer stringBuffer = new StringBuffer(stationFolderPath).append(SLASH)
                .append(MAPPING_TXT);
        return stringBuffer.toString();
    }

    /**
     * 格式化时间
     * 这里是为了去掉Timestamp toString()后面带的【.0】
     *
     * @param storeTime
     * @return
     */
    public static String formatDate(Timestamp storeTime) {
        if (storeTime != null) {
            String s = storeTime.toString();
            return s.substring(0, s.length() - 2);
        } else {
            return "null";
        }
    }

    /**
     * 获取当天往前七天的0点
     *
     * @return
     */
    public static String getZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        return DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd 00:00:00");
    }

    /**
     * 获取文件日期后缀
     * 例如：2021_05_14_19_05
     *
     * @return
     */
    public static String getFileDateSuffix() {
        return DateFormatUtils.format(new Date(), "yyyy_MM_dd_HH_mm");
    }
}
