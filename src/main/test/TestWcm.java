import java.util.HashMap;
import java.util.Map;

public class TestWcm {

    public static void main(String[] args) {
        //获取服务名，方法名
        String sServiceId = "wcm6_document";
        String sMethodName = "query";

        //构造传递参数
        Map oPostData = new HashMap();
        oPostData.put("ChannelId", new Integer(11));

        //调用WCMServiceCaller接口
        //Dispatch oDispatch = WCMServiceCaller.Call(sServiceId, sMethodName, oPostData, false);


    }

}
