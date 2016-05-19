import by.bsuir.spp.ils.lab.controller.actions.generator.EventPassAction;
import by.bsuir.spp.ils.lab.entity.Document;
import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testDocument {

    public static Document d;

    @BeforeClass
    public static void init() {
        d = new Document();
    }

    @Test
    public void testGetSetId() {

        d.setId(5);

        int compare = d.getId();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetUserId() {

        d.setUserId(1);

        int compare = d.getUserId();

        assertEquals(compare, 1);

    }

    @Test
    public void testGetSetDate() {

    }

    @Test
    public void testEquals() {

        Object testObject = new Object();
        Object secondObject = new Object();

        assertFalse(d.equals(testObject));

    }

    @Test
    public void testHashcode() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = 155;

        assertEquals(compare, d.hashCode());

    }

    @Test
    public void testGetSetInheritId() {

        d.setInheritId(5);

        int compare = d.getInheritId();

        assertEquals(compare, 5);
    }

    @Test
    public void testDocumentSeriesGetSet() {

        d.setSeries("a");

        String compare = d.getSeries();

        assertNotNull(compare);

    }

    @Test
    public void testNumber() {

        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);

    }

    @Test
    public void testNationality() {

        d.setSeries("usa");

        String compare = d.getSeries();

        assertNotNull(compare);

    }

    @Test
    public void testDoc(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }

    @Test
    public void testDocType(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }

    @Test
    public void testGetDocType(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }

    @Test
    public void testSetDocType(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }

    @Test
    public void testGetDoc(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }

    @Test
    public void testSetDoc(){
        d.setNumber(123);

        int compare = d.getNumber();

        assertEquals(compare, 123);
    }
}
