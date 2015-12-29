import java.util.*;
import GEOLocation.GEOLocation;

/**
 * Created by xuch on 2015/12/29.
 */
public class UnitTest {
    public static  void main(String[] args) {
        ArrayList<String> addresses = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座", "远洋国际中心A座", "中国银行大厦", "", "fuck you", "---23dsf@@E$")); //{"北京市朝阳区远洋国际中心A座", };
        for (String address : addresses) {
            HashMap<String, String> response = GEOLocation.getGEOLocation(address);
            Collection entries = response.entrySet();
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
            System.out.print("\n");
        }

        System.exit(0);
    }
}
