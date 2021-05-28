package com.oket.getdata.database;

import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import com.oket.getdata.entity.HoseOutEntity;
import com.oket.getdata.entity.InventoryEntity;
import com.oket.getdata.entity.MappingEntity;
import com.oket.getdata.util.CustomJdbcUtil;
import com.oket.getdata.util.CustomStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: getdata
 * @description: 实现类
 * @author: dzp
 * @create: 2021-05-14 09:04
 **/
public class OperatingDatabaseImpl implements OperatingDatabase {

    private static final Logger logger = LoggerFactory.getLogger(OperatingDatabaseImpl.class);

    @Override
    public List<Integer> getOneStationAllOilCanNos(DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {
        List<Integer> oilCanNos;
        oilCanNos = dataBaseConfigInfoEntity.getOilCanNos();
        //有要查询的罐号直接返回
        if (oilCanNos.size() > 0) {
            return oilCanNos;
        }
        //去数据库查询
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomJdbcUtil.getConnection(CustomJdbcUtil.DRIVER,
                    CustomJdbcUtil.getUrl(dataBaseConfigInfoEntity.getIpAndPort()),
                    dataBaseConfigInfoEntity.getUsername(),
                    dataBaseConfigInfoEntity.getPassword());
            if (connection != null) {
                String sql = "SELECT OilCanNo FROM `oss_monitor_rtinventory` GROUP BY OilCanNo";
                logger.info("sql获取一个油站下所有的油罐---" + sql);

                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    oilCanNos.add(resultSet.getInt("OilCanNo"));
                }
            } else {
                logger.error("未获取到连接---" + dataBaseConfigInfoEntity.getStationShortName());
            }
            return oilCanNos;
        } catch (Exception e) {
            logger.error("获取一个油站下所有的油罐出错---" + e.getMessage());
            return oilCanNos;
        } finally {
            CustomJdbcUtil.closeResource(resultSet, statement, connection);
        }
    }

    @Override
    public List<Integer> getOneOilCanAllOilGunNos(int oilCanNo, DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {

        List<Integer> oilGunNos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = CustomJdbcUtil.getConnection(CustomJdbcUtil.DRIVER,
                    CustomJdbcUtil.getUrl(dataBaseConfigInfoEntity.getIpAndPort()),
                    dataBaseConfigInfoEntity.getUsername(),
                    dataBaseConfigInfoEntity.getPassword());

            if (connection != null) {
                String sql = "SELECT oilgunno FROM sm_oilgun WHERE OilCanNo = ?";
                logger.info("sql获取一个罐的所有油枪---" + sql);

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, oilCanNo);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    oilGunNos.add(resultSet.getInt("oilgunno"));
                }
            } else {
                logger.error("未获取到连接---" + dataBaseConfigInfoEntity.getStationShortName());
            }
            return oilGunNos;
        } catch (Exception e) {
            logger.error("获取一个罐的所有油枪出错---" + e.getMessage());
            return null;
        } finally {
            CustomJdbcUtil.closeResource(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public List<InventoryEntity> getInventoryEntitiesByIdAndOilCanNo(int oilCanNo, Integer inventoryId, DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {

        List<InventoryEntity> inventoryEntities = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;

        try {
            connection = CustomJdbcUtil.getConnection(CustomJdbcUtil.DRIVER,
                    CustomJdbcUtil.getUrl(dataBaseConfigInfoEntity.getIpAndPort()),
                    dataBaseConfigInfoEntity.getUsername(),
                    dataBaseConfigInfoEntity.getPassword());
            if (inventoryId != null) {
                sql = "SELECT id,OilCanNo,TotalHeight,OilCubage,StoreTime,Temp FROM oss_monitor_rtinventory WHERE OilCanNo = ? AND id > ? ORDER BY id ASC";
                logger.info(dataBaseConfigInfoEntity.getStationShortName() + "-" + oilCanNo + "号罐" + "上次取数的id为-" + inventoryId);
            } else {
                String zeroTime = CustomStringUtil.getZeroTime();
                sql = "SELECT id,OilCanNo,TotalHeight,OilCubage,StoreTime,Temp FROM oss_monitor_rtinventory WHERE OilCanNo = ? AND StoreTime > '" + zeroTime + "' ORDER BY id ASC";
                logger.info(dataBaseConfigInfoEntity.getStationShortName() + "-" + oilCanNo + "号罐第一次取数");
            }
            logger.info("sql查询单罐罐存---" + sql);

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, oilCanNo);
                if (inventoryId != null) {
                    preparedStatement.setInt(2, inventoryId);
                }

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    InventoryEntity inventoryEntity = new InventoryEntity(resultSet.getInt("id"),
                            resultSet.getInt("OilCanNo"),
                            resultSet.getDouble("TotalHeight"),
                            resultSet.getDouble("OilCubage"),
                            resultSet.getTimestamp("StoreTime"),
                            resultSet.getDouble("Temp"));
                    inventoryEntities.add(inventoryEntity);
                }
            } else {
                logger.error("未获取到连接---" + dataBaseConfigInfoEntity.getStationShortName());
            }
            return inventoryEntities;
        } catch (Exception e) {
            logger.error("查询单罐罐存数据出错---" + e.getMessage());
            return null;
        } finally {
            CustomJdbcUtil.closeResource(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public List<HoseOutEntity> getHoseOutEntitiesByIdAndOilGunNos(List<Integer> oilGunNos, Integer hoseOutId, DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {

        List<HoseOutEntity> hoseOutEntities = new ArrayList<>();

        //拼接枪号列表
        String s = oilGunNos.toString();
        String oilGunNosStr = s.substring(1, s.length() - 1);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;

        try {
            connection = CustomJdbcUtil.getConnection(CustomJdbcUtil.DRIVER,
                    CustomJdbcUtil.getUrl(dataBaseConfigInfoEntity.getIpAndPort()),
                    dataBaseConfigInfoEntity.getUsername(),
                    dataBaseConfigInfoEntity.getPassword());
            if (hoseOutId != null) {
                sql = "SELECT id,amount,oilgunno,takedate,gettime,pumpno FROM rt_vouch WHERE oilgunno in (" + oilGunNosStr + ") AND id > ? ORDER BY id ASC";
                logger.info(dataBaseConfigInfoEntity.getStationShortName() + "-" + oilGunNos.toString() + "号枪" + "上次取数的id为-" + hoseOutId);
            } else {
                String zeroTime = CustomStringUtil.getZeroTime();
                sql = "SELECT id,amount,oilgunno,takedate,gettime,pumpno FROM rt_vouch WHERE oilgunno in (" + oilGunNosStr + ") AND takedate > '" + zeroTime + "' ORDER BY id ASC";
                logger.info(dataBaseConfigInfoEntity.getStationShortName() + "-" + oilGunNos.toString() + "号枪第一次取数据");
            }
            logger.info("sql单罐销售数据---" + sql);

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                if (hoseOutId != null) {
                    preparedStatement.setInt(1, hoseOutId);
                }

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    HoseOutEntity hoseOutEntity = new HoseOutEntity(resultSet.getInt("id"),
                            resultSet.getDouble("amount"),
                            resultSet.getInt("oilgunno"),
                            resultSet.getTimestamp("takedate"),
                            resultSet.getTimestamp("gettime"),
                            resultSet.getDouble("pumpno"));
                    hoseOutEntities.add(hoseOutEntity);
                }
            } else {
                logger.error("未获取到连接---" + dataBaseConfigInfoEntity.getStationShortName());
            }
            return hoseOutEntities;
        } catch (Exception e) {
            logger.error("单罐销售数据出错---" + e.getMessage());
            return null;
        } finally {
            CustomJdbcUtil.closeResource(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public List<MappingEntity> getOneStationMappingEntity(DataBaseConfigInfoEntity dataBaseConfigInfoEntity) {

        List<MappingEntity> mappingEntities = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;

        try {
            connection = CustomJdbcUtil.getConnection(CustomJdbcUtil.DRIVER,
                    CustomJdbcUtil.getUrl(dataBaseConfigInfoEntity.getIpAndPort()),
                    dataBaseConfigInfoEntity.getUsername(),
                    dataBaseConfigInfoEntity.getPassword());

            sql = "select oilgunno,oilcanno from sm_oilgun";
            logger.info("sql单站枪罐关系---" + sql);

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    MappingEntity mappingEntity = new MappingEntity(resultSet.getInt("oilgunno"), resultSet.getInt("oilcanno"));
                    mappingEntities.add(mappingEntity);
                }
            } else {
                logger.error("未获取到连接---" + dataBaseConfigInfoEntity.getStationShortName());
            }
            return mappingEntities;
        } catch (Exception e) {
            logger.error("查询单站枪罐关系数据出错---" + e.getMessage());
            return null;
        } finally {
            CustomJdbcUtil.closeResource(resultSet, preparedStatement, connection);
        }
    }
}
