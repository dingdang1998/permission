package com.oket.getdata.database;

import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import com.oket.getdata.entity.HoseOutEntity;
import com.oket.getdata.entity.InventoryEntity;
import com.oket.getdata.entity.MappingEntity;

import java.util.List;

/**
 * @program: getdata
 * @description: 操作数据库
 * @author: dzp
 * @create: 2021-05-14 09:02
 **/
public interface OperatingDatabase {
    /**
     * 获取一个油站下所有的油罐
     * dataBaseConfigInfoEntity中oilCanNos不为null，直接将返回
     * 否则，去查询
     *
     * @param dataBaseConfigInfoEntity
     * @return
     */
    List<Integer> getOneStationAllOilCanNos(DataBaseConfigInfoEntity dataBaseConfigInfoEntity);

    /**
     * 获取一个罐的所有油枪
     *
     * @param oilCanNo
     * @param dataBaseConfigInfoEntity
     * @return
     */
    List<Integer> getOneOilCanAllOilGunNos(int oilCanNo, DataBaseConfigInfoEntity dataBaseConfigInfoEntity);

    /**
     * 根据上次取数id和罐号查询罐存数据
     * 如果上次取数id为null，则从今天的0点开始取数
     *
     * @param oilCanNo
     * @param inventoryId
     * @param dataBaseConfigInfoEntity
     * @return
     */
    List<InventoryEntity> getInventoryEntitiesByIdAndOilCanNo(int oilCanNo, Integer inventoryId, DataBaseConfigInfoEntity dataBaseConfigInfoEntity);

    /**
     * 单罐销售数据
     * 根据上次取数id和枪号查询
     * 如果上次取数id为null，则从今天的0点开始取数
     *
     * @param oilGunNos
     * @param hoseOutId
     * @param dataBaseConfigInfoEntity
     * @return
     */
    List<HoseOutEntity> getHoseOutEntitiesByIdAndOilGunNos(List<Integer> oilGunNos, Integer hoseOutId, DataBaseConfigInfoEntity dataBaseConfigInfoEntity);

    /**
     * 获取单站枪罐关系
     *
     * @param dataBaseConfigInfoEntity
     * @return
     */
    List<MappingEntity> getOneStationMappingEntity(DataBaseConfigInfoEntity dataBaseConfigInfoEntity);
}
