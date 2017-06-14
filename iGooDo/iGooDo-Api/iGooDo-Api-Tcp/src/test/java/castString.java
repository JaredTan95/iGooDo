import cn.tanjianff.igoodo.api.tcp.util.FormatData;

/**
 * Created by tanjian on 2017/6/12.
 */
public class castString {
    public static void main(String[] args) {
        String string = "0024CHEC1832326197922453";
        FormatData formatData=new FormatData(string);
        System.out.println(formatData.toString());
    }
}
