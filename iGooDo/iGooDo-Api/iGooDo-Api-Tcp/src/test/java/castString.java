import cn.tanjianff.igoodo.common.util.RequestFormatData;
import cn.tanjianff.igoodo.common.util.ResponseFormatData;

/**
 * Created by tanjian on 2017/6/12.
 */
public class castString {
    public static void main(String[] args) {

        System.out.println("=======RequestFormatData==========");
        String ping = "0008PING";
        String chec = "0023CHEC183232619792242";
        RequestFormatData formatData=new RequestFormatData(ping);
        RequestFormatData formatData2=new RequestFormatData(chec);
        System.out.println(formatData.toString());
        System.out.println(formatData2.toString());


        System.out.println("=======ResponseFormatData==========");
        ResponseFormatData data=new ResponseFormatData();
        data.setType(ResponseFormatData.OPEN);
        data.setAppUserId("18323261979");
        data.setNeedCup(1);
        data.setMsg("100");
        String senData=data.FormatToString();
        System.out.println("总长度:"+senData.length()+"\n"+senData);



    }
}
