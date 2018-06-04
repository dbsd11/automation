package group.bison.automation.executor.meshnet.protocol;

/**
 * Created by BSONG on 2018/6/4.
 */
public abstract class Protocol {
    private String id;
    private String subnet;
    private String encryption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }
}
