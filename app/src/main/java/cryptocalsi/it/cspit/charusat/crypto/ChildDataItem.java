package cryptocalsi.it.cspit.charusat.crypto;

import java.io.Serializable;

public class ChildDataItem implements Serializable {
    private String childName;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
