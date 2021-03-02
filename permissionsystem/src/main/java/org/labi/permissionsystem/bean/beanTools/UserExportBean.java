package org.labi.permissionsystem.bean.beanTools;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: permissionsystem
 * @description: 基本资料导出数据bean
 * @author: dzp
 * @create: 2021-03-02 11:08
 **/
@Data
public class UserExportBean {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("用户名")
    private String username;
}
