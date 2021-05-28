package com.oket.getdata.customthread;

import com.oket.getdata.database.OperatingDatabase;
import com.oket.getdata.entity.*;
import com.oket.getdata.operatfile.OperatingFile;
import com.oket.getdata.util.CustomStringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: getdata
 * @description: 一个油站要执行的任务
 * @author: dzp
 * @create: 2021-05-15 11:32
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnStationRun implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(OnStationRun.class);
    private static final String NULL = "null";

    private OperatingDatabase operatingDatabase;
    private DataBaseConfigInfoEntity dataBaseConfigInfoEntity;
    private String rootPath;

    /**
     * 1、拿到单站要取数的油罐号
     * 2、到单罐文件夹下找id.txt文件
     * (1)能找到，且id不为null，则根据记录的id继续取数
     * (2)找不到，则改油罐为第一次取数，从当天0点开始取数
     * 3、将查询数据写文件
     * (1)将罐存数据写文件--一个罐一个
     * (2)销售数据写文件--一个罐一个
     * (3)记录取数的id--一个罐一个
     * (4)记录枪罐关系--一个站一个
     */
    @Override
    public void run() {
        logger.info(this.toString() + "---is running");
        //获取要查询数据的罐号
        List<Integer> oneStationAllOilCanNos = operatingDatabase.getOneStationAllOilCanNos(dataBaseConfigInfoEntity);
        //获取油站文件夹路径
        String stationFolderPath = CustomStringUtil.getStationFolderPath(rootPath, dataBaseConfigInfoEntity);

        //有要到导出数据的油罐，才进行数据的导出
        if (oneStationAllOilCanNos != null && oneStationAllOilCanNos.size() > 0) {
            for (Integer oilCanNo : oneStationAllOilCanNos) {
                //单罐罐存数据
                List<InventoryEntity> inventoryEntities;
                //单罐销售数据
                List<HoseOutEntity> hoseOutEntities;
                //单罐的油枪
                List<Integer> oneOilCanAllOilGunNos = operatingDatabase.getOneOilCanAllOilGunNos(oilCanNo, dataBaseConfigInfoEntity);
                //获取单罐id.txt文件路径
                String idTxtPath = CustomStringUtil.getIdTxtPath(stationFolderPath, String.valueOf(oilCanNo));

                //得到上次取数的id
                IdEntity idEntity = getIdEntity(idTxtPath);
                Integer inventoryId = null;
                Integer hoseOutId = null;
                if (idEntity != null) {
                    inventoryId = idEntity.getInventoryId();
                    hoseOutId = idEntity.getHouseOutId();
                }

                //获取罐存数据
                inventoryEntities = operatingDatabase.getInventoryEntitiesByIdAndOilCanNo(oilCanNo, inventoryId, dataBaseConfigInfoEntity);
                //获取销售数据
                hoseOutEntities = operatingDatabase.getHoseOutEntitiesByIdAndOilGunNos(oneOilCanAllOilGunNos, hoseOutId, dataBaseConfigInfoEntity);

                //将单罐数据数据写文件
                if (inventoryEntities != null && inventoryEntities.size() > 0) {
                    OperatingFile.writeQueryDataToFile(inventoryEntities, CustomStringUtil.getInventoryCsvPath(stationFolderPath, String.valueOf(oilCanNo)));
                    //更新罐存数据取数id
                    inventoryId = inventoryEntities.get(inventoryEntities.size() - 1).getId();
                }
                //将单罐销售数据写文件
                if (hoseOutEntities != null && hoseOutEntities.size() > 0) {
                    OperatingFile.writeQueryDataToFile(hoseOutEntities, CustomStringUtil.getHoseOutCsvPath(stationFolderPath, String.valueOf(oilCanNo)));
                    //更新销售数据取数id
                    hoseOutId = hoseOutEntities.get(hoseOutEntities.size() - 1).getId();
                }
                //记录单罐下id.txt文件
                List<IdEntity> idEntities = new ArrayList<>();
                idEntities.add(new IdEntity(inventoryId, hoseOutId));
                OperatingFile.writeQueryDataToFile(idEntities, CustomStringUtil.getIdTxtPath(stationFolderPath, String.valueOf(oilCanNo)));
            }
            //查询枪罐关系
            List<MappingEntity> oneStationMappingEntity = operatingDatabase.getOneStationMappingEntity(dataBaseConfigInfoEntity);
            //将单站枪罐关系写文件
            if (oneStationMappingEntity != null && oneStationMappingEntity.size() > 0) {
                OperatingFile.writeQueryDataToFile(oneStationMappingEntity, CustomStringUtil.getMappingTxtPath(stationFolderPath));
            }
        }
    }

    /**
     * 获取该油站该罐上次取数的id
     * 1、找到id.txt文件，返回实体
     * (a)返回的实体两个id都有
     * (b)返回的实体只有inventoryId
     * (c)返回的实体只有hoseOutId
     * 2、如果没有找到id.txt文件，该罐为第一次取数
     *
     * @param idTxtPath
     * @return
     */
    public IdEntity getIdEntity(String idTxtPath) {

        int inventoryId;
        int hoseOutId;

        //读取
        String[] ids = OperatingFile.readIdFile(idTxtPath);
        if (ids != null) {
            //id.txt中两个id都有
            if (!NULL.equals(ids[0]) && !NULL.equals(ids[1])) {
                inventoryId = Integer.parseInt(ids[0]);
                hoseOutId = Integer.parseInt(ids[1]);
                return new IdEntity(inventoryId, hoseOutId);
            }
            //只有【oss_monitor_rtinventory表】的取数id
            if (!NULL.equals(ids[0])) {
                inventoryId = Integer.parseInt(ids[0]);
                return new IdEntity(inventoryId, null);
            }
            //只有【rt_vouch表】的取数id
            if (!NULL.equals(ids[1])) {
                hoseOutId = Integer.parseInt(ids[1]);
                return new IdEntity(null, hoseOutId);
            }
            //全为"null"字符串
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        return "OnStationRun---" + dataBaseConfigInfoEntity.getStationShortName();
    }
}
