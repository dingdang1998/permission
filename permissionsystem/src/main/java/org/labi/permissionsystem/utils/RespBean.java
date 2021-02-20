package org.labi.permissionsystem.utils;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName RespBean.java
 * @Description 返回消息封装类
 * @createTime 2021年01月26日 11:03:00
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object object;

    public RespBean() {
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public RespBean(Integer status, String msg, Object object) {
        this.status = status;
        this.msg = msg;
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static RespBean respBean(Integer status, String msg, Object o) {
        RespBean respBean = new RespBean();
        respBean.setStatus(status);
        respBean.setMsg(msg);
        respBean.setObject(o);
        return respBean;
    }
}
