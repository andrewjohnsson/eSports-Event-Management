import by.bsuir.spp.ils.lab.entity.DocumentPK;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testDocumentKey {

    @Test
    public void getId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setId(1);
        assertEquals(1, documentPK.getId());
    }

    @Test
    public void setId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setId(1);
        assertEquals(1, documentPK.getId());
    }

    @Test
    public void getEventId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setTypeId(1);
        assertEquals(1, documentPK.getTypeId());
    }

    @Test
    public void setEventId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setTypeId(1);
        assertEquals(1, documentPK.getTypeId());
    }

    @Test
    public void getUserId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setUserId(1);
        assertEquals(1, documentPK.getUserId());
    }

    @Test
    public void setUserId(){
        DocumentPK documentPK = new DocumentPK();
        documentPK.setUserId(1);
        assertEquals(1, documentPK.getUserId());
    }
}
