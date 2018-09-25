package com.yhao.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 杨浩
 * @create 2018-09-19 16:35
 **/
@Slf4j
@Component
public class IpUtil implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.serverPort = webServerInitializedEvent.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }


    //ip 的 获取 如下
    public String getHost() {
        String host = null;
        try

        {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (
                UnknownHostException e)

        {
            log.error("get server host Exception e:", e);
        }

        return host;
    }

    public String getIp(){
        return getHost() + ":" + getPort();
    }
}
