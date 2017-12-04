package l7;

public class FiveExc extends Exception {
    private int errCode;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}