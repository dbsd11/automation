package group.bison.automation.executor.meshnet.utils;

import com.chrylis.codec.base58.Base58Codec;
import com.chrylis.codec.base58.Base58UUID;
import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MeshConstants;
import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by BSONG on 2018/6/7.
 */
public class IDGenerator {
    private static final String userSalt = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".chars().mapToObj(charI -> charI == 'x' ? Hex.encodeHexString(BigInteger.valueOf(Double.valueOf(Math.random() * 16).intValue()).toByteArray()) : charI == 'y' ? Hex.encodeHexString(BigInteger.valueOf(Double.valueOf(Math.random() * 16).intValue()).and(BigInteger.valueOf(0x3)).or(BigInteger.valueOf(0x8)).toByteArray()) : String.valueOf((char) charI)).collect(Collectors.joining());

    public static String getRandomId() {
        return new Base58UUID().encode(UUID.randomUUID());
    }

    public static String getNodeId() {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            MessageDigest sha384 = MessageDigest.getInstance("SHA-384");
            sha256.update(String.join(MeshConstants.subnet, MeshConstants.netVersion, "ARRAYBUFFER").getBytes());
            sha384.update(String.join(NetUtil.getLanIP(), String.valueOf(MeshConstants.netPort), Base58Codec.doEncode(sha256.digest()), userSalt).getBytes());
            String nodeId = Base58Codec.doEncode(sha384.digest());
            return nodeId;
        } catch (Exception e) {
            throw new BusinessException("生成NodeId失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(getNodeId());
    }
}
