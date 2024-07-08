package ModelClass;

public class BladeInfo {
    private int id;
    private String BladeName;

    public BladeInfo(int id, String bladeName) {
        this.id = id;
        BladeName = bladeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBladeName() {
        return BladeName;
    }

    public void setBladeName(String bladeName) {
        BladeName = bladeName;
    }

    @Override
    public String toString() {
        return "BladeInfo{" +
                "id=" + id +
                ", BladeName='" + BladeName + '\'' +
                '}';
    }
}
