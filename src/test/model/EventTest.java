package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("User logged in");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("User logged in", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "User logged in", e.toString());
	}

    @Test
    public void testEquals() {
        assertNotEquals(e, d);
        assertFalse(e.equals(null));
        assertNotEquals(e, new Event("User"));
        assertEquals(e, e);
        assertEquals(new Event("test"), new Event("test"));
    }

    @Test
    public void testHashCode() {
        assertEquals(new Event("test").hashCode(), new Event("test").hashCode());
    }
}
