package group.bison.automation.executor.meshnet.utils;

import com.chrylis.codec.base58.Base58Codec;

import java.math.BigInteger;

/**
 * Created by BSONG on 2018/6/11.
 */
public class NodeUtil {

    public static BigInteger nodeDistance(String node1, String node2) {
        BigInteger nodeI1 = new BigInteger(Base58Codec.doDecode(node1));
        BigInteger nodeI2 = new BigInteger(Base58Codec.doDecode(node2));
        return nodeI1.subtract(nodeI2).abs().mod(BigInteger.ONE.multiply(BigInteger.valueOf(2)).pow(384));
    }

    public static void main(String[] args) {
        System.out.println(nodeDistance(IDGenerator.getNodeId(), IDGenerator.getNodeId()));
    }
}
