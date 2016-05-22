import by.bsuir.spp.ils.lab.helper.MD5Helper;
import com.opensymphony.xwork2.ActionSupport;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testAuthService {

    @Test
    public void testLogin(){
        assertEquals(ActionSupport.SUCCESS, "success");
    }


    @Test
    public void md5checker(){
        MD5Helper md5 = new MD5Helper();
        String res = md5.md5Custom("12345");
        assertEquals("827ccb0eea8a706c4c34a16891f84e7b", res);
    }
    @Test
    public void md5md5(){
        MD5Helper md5 = new MD5Helper();
        String res = md5.md5Custom("827ccb0eea8a706c4c34a16891f84e7b");
        assertEquals("1f32aa4c9a1d2ea010adcf2348166a04", res);
    }
    @Test
    public void md5String(){
        MD5Helper md5 = new MD5Helper();
        String res = md5.md5Custom("asdasdasd");
        assertEquals("a3dcb4d229de6fde0db5686dee47145d", res);
    }
    @Test
    public void md5Salt(){
        MD5Helper md5 = new MD5Helper();
        String res = md5.md5Custom("a3dcb4d229de6fde0db5686dee47145dasdasdasd");
        assertEquals("902635c61aca9b53126f230bcfd3216a", res);
    }
}
