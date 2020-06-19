package dev.personnummer;

public interface IdentityNumber {
	
	boolean hasLongFormat();
	String longFormat();
	String shortFormat();

}
