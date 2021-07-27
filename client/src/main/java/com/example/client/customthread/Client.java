package com.example.client.customthread;

import com.example.client.entity.Message;
import com.example.client.operatingfile.AlarmMessage;
import com.example.client.operatingfile.DeliveryMessage;
import com.example.client.operatingfile.InventoryMessage;
import com.example.client.operatingfile.SensorMessage;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: client
 * @description: 客户端发送数据
 * @author: dzp
 * @create: 2021-07-22 13:33
 **/
@Getter
@Setter
public class Client implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String SUCCESS = "Success";

    private String ip;
    private Integer port;
    /**
     * 是否发送罐存数据
     */
    private boolean isSendInventory;
    /**
     * 是否发送报警数据
     */
    private boolean isSendAlarm;
    /**
     * 是否发送传感器数据
     */
    private boolean isSendSensor;
    /**
     * 是否发送卸油数据
     */
    private boolean isSendDelivery;
    /**
     * 线程编号
     */
    private Integer threadNumber;
    /**
     * 根路径
     */
    private String rootPath;
    /**
     * 要发送数据的服务端
     */
    private String serverName;
    /**
     * 执行周期
     */
    private Integer cycle;

    private ScheduledExecutorService scheduledExec;

    public Client(String ip, Integer port, boolean isSendInventory, boolean isSendAlarm, boolean isSendSensor, boolean isSendDelivery, Integer threadNumber, String rootPath, String serverName, Integer cycle) {
        this.ip = ip;
        this.port = port;
        this.isSendInventory = isSendInventory;
        this.isSendAlarm = isSendAlarm;
        this.isSendSensor = isSendSensor;
        this.isSendDelivery = isSendDelivery;
        this.threadNumber = threadNumber;
        this.rootPath = rootPath;
        this.serverName = serverName;
        this.cycle = cycle;
        this.scheduledExec = new ScheduledThreadPoolExecutor(1);
    }

    @Override
    public void run() {
        String filePath = this.rootPath + this.serverName;
        List<Message> all = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        //获取要发送的罐存数据
        if (this.isSendInventory) {
            InventoryMessage inventoryMessage = new InventoryMessage(filePath);
            List<Message> inventoryMessages = inventoryMessage.getMessages();
            if (inventoryMessages != null && inventoryMessages.size() > 0) {
                all.addAll(inventoryMessages);
            }
        }
        //获取要发送的卸油数据
        if (this.isSendAlarm) {
            AlarmMessage alarmMessage = new AlarmMessage(filePath);
            List<Message> alarmMessages = alarmMessage.getMessages();
            if (alarmMessages != null && alarmMessages.size() > 0) {
                all.addAll(alarmMessages);
            }
        }
        //获取要发送的传感器数据
        if (this.isSendSensor) {
            SensorMessage sensorMessage = new SensorMessage(filePath);
            List<Message> sensorMessages = sensorMessage.getMessages();
            if (sensorMessages != null && sensorMessages.size() > 0) {
                all.addAll(sensorMessages);
            }
        }
        //获取要发送的卸油数据
        if (this.isSendDelivery) {
            DeliveryMessage deliveryMessage = new DeliveryMessage(filePath);
            List<Message> deliveryMessages = deliveryMessage.getMessages();
            if (deliveryMessages != null && deliveryMessages.size() > 0) {
                all.addAll(deliveryMessages);
            }
        }
        if (all.size() > 0) {
            //定时向服务端发送消息
            this.scheduledExec.scheduleWithFixedDelay(() -> {
                Socket socket = null;
                OutputStream out = null;
                InputStream in = null;
                BufferedReader reader = null;
                try {
                    //建立连接
                    socket = new Socket(this.ip, this.port);
                    System.out.println(this.threadNumber + "--与客户端链接成功！");

                    //向服务端发送消息
                    out = socket.getOutputStream();
                    //超过size大小从头开始发
                    if (atomicInteger.get() == all.size()) {
                        atomicInteger.set(0);
                    }
                    Message message = all.get(atomicInteger.get());
                    out.write(message.toString().getBytes());
                    logger.info("----" + this.threadNumber + "油站向服务端发送第" + atomicInteger + "条消息成功--" + message.toString() + "--" + new Date());

                    //接收服务端的反馈
                    in = socket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    String info = null;
                    while ((info = reader.readLine()) != null) {
                        logger.info("----" + this.threadNumber + "油站发送的第" + atomicInteger + "条消息接收到服务端返回响应：" + info + "--" + new Date());
                        if (SUCCESS.equals(info)) {
                            atomicInteger.incrementAndGet();
                        }
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }, 1, this.cycle, TimeUnit.SECONDS);
        }
    }
}
