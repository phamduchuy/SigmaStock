package admin.sigmastock.sigmastock.Entity;

/**
 * Created by HuyPD on 9/28/2016.
 */
public class CStock {
    private String code="";
    private String last= "";
    private String change ="";
    private String changeRatio = "";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangeRatio() {
        return changeRatio;
    }

    public void setChangeRatio(String changeRatio) {
        this.changeRatio = changeRatio;
    }
}
