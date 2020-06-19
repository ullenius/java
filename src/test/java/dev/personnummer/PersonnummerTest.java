package dev.personnummer;
import static org.junit.Assert.*;

import org.junit.Test;

public class PersonnummerTest {

    @Test
    public void testWithControlDigit() {
        new PersonalNumber("6403273813");
        new PersonalNumber("510818-9167");
        new PersonalNumber("19900101-0017");
        new PersonalNumber("19130401+2931");
        new PersonalNumber("196408233234");
        new PersonalNumber("0001010107");
        new PersonalNumber("000101-0107");
        new PersonalNumber("1010101010");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithoutControlDigit() {
        new PersonalNumber("640327-381");
        new PersonalNumber("510818-916");
        new PersonalNumber("19900101-001");
        new PersonalNumber("100101+001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithInvalidValues() {
        new PersonalNumber("A string");
        new PersonalNumber("Two");
        new PersonalNumber("222");
        new PersonalNumber(null);
        new PersonalNumber("9701063-2391");
    }

    @Test
    public void testCoordinationNumbers() {
        new Samordningsnummer("701063-2391");
        new Samordningsnummer("640883-3231");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithBadCoordinationNumbers() {
        new Samordningsnummer("900161-0017");
        new Samordningsnummer("640893-3231");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPersonnummerAsCoordinationNumbers() {
    	 new Samordningsnummer("6403273813");
         new Samordningsnummer("510818-9167");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void tesCoordinationNumbersAsPersonnummer() {
        new PersonalNumber("701063-2391");
        new PersonalNumber("640883-3231");
    }
    
    @Test
    public void testEquals() {
    	 Samordningsnummer one = new Samordningsnummer("701063-2391");
    	 Samordningsnummer two = new Samordningsnummer("701063-2391");
    	 assertEquals(one,two);
    }
    
    @Test
    public void testNonEquals() {
     	 Samordningsnummer one = new Samordningsnummer("701063-2391");
    	 Samordningsnummer two = new Samordningsnummer("640883-3231");
    	 assertNotEquals(one,two);
    }

    @Test
    public void testShortFormat() {
    	
    	IdentityNumber one = new PersonalNumber("19900101-0017");
    	assertEquals("900101-0017", one.shortFormat());
    	assertTrue(one.hasLongFormat());
    }
    
    @Test
    public void testNoLongFormat() {
    	
    	IdentityNumber one = new PersonalNumber("900101-0017");
    	assertEquals("900101-0017", one.shortFormat());
    	assertEquals(one.longFormat(), one.shortFormat());
    	assertFalse(one.hasLongFormat());
    }
    
    @Test
    public void testEqualHashCode() {
    	
    	IdentityNumber one = new PersonalNumber("900101-0017");
    	IdentityNumber two = new PersonalNumber("900101-0017");
    	
    	assertNotSame(one,two); // don't cheat
    	assertEquals(one.hashCode(), two.hashCode());
    }
    
    
    @Test
    public void testNotEqualHashCode() {
    	
    	IdentityNumber one = new PersonalNumber("900101-0017");
    	IdentityNumber two = new PersonalNumber("19130401+2931");
    	
    	assertNotEquals(one.hashCode(), two.hashCode());
    }
    
    
}
