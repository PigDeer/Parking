package pigdeer.parking.utils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhutao
 * Date: 12-12-17
 * Time: 下午1:00
 * To change this template use File | Settings | File Templates.
 */
public class PrintHelper {
    public static final String PARKLOT_LABEL = "停车场";
    public static final String PARKBOY_LABEL = "停车仔";
    public static final String TOTAL_LABEL = "Total";

    public static final String NUMBER_LABEL = "编号：";
    public static final String SPACE_LABEL = "车位数：";
    public static final String EMPTY_LABEL = "空位数：";

    public static String getParkLotLabel(String number, int space, int empty, int tabs){
        return  getTabs(tabs)+PARKLOT_LABEL+NUMBER_LABEL+number+"\n"+
                getTabs(tabs)+"\t"+SPACE_LABEL+space+"\n"+
                getTabs(tabs)+"\t"+EMPTY_LABEL+empty+"\n";
    }

    public static String getStatLabel(int space, int empty, int tabs){
        return  getTabs(tabs)+TOTAL_LABEL+SPACE_LABEL+space+"\n"+
                getTabs(tabs)+TOTAL_LABEL+EMPTY_LABEL+empty+"\n";
    }

    public static String getTabs(int tabs){//返回若干个tab键。
        String str = "";
        for(int i=0;i<tabs;i++){
            str += "\t";
        }
        return str;
    }

    public static String getParkBoyLabel(String number, int tabs) {
        return  getTabs(tabs)+PARKBOY_LABEL+NUMBER_LABEL+number+"\n";
    }
}
