package group.bison.automation.executor.meshnet.common;

/**
 * Created by BSONG on 2018/6/9.
 */
public enum MessageType {

    HANDSHAKE("握爪"), HANDSHAKE_ACK("握爪ACK"), ACK("建立连接"), PEERS("获取邻居"), REQUEST("请求"), RESPONSE("响应"), NOTIFY("通知"), RENEGOTIATE("重连"), RESEND("重发"),
    STORE("存储"), RETRIEVE("查询"), RETRIEVED("查询命中"), FORWARD(""), NEW_PATHS(""), REVOKE_PATHS(""), DELTA("");

    private String desc;

    MessageType(String desc) {
        this.desc = desc;
    }
}
