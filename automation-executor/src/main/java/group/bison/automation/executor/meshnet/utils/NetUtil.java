package group.bison.automation.executor.meshnet.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by BSONG on 2018/6/4.
 */
public class NetUtil {
    private static final Logger LOG = LoggerFactory.getLogger(NetUtil.class);
    private static String lanIp;

    public static String getLanIP() {
        if (!StringUtils.isEmpty(lanIp)) {
            return lanIp;
        }

        try {
            Socket socket = new Socket("220.181.112.244", 80);
            lanIp = ((InetSocketAddress) socket.getLocalSocketAddress()).getHostName();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            lanIp = "127.0.0.1";
        }
        return lanIp;
    }

    public static void main(String[] args){
        LOG.info(getLanIP());
    }
}
