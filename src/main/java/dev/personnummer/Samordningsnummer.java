package dev.personnummer;

public class Samordningsnummer extends Personnummer implements Comparable<Samordningsnummer> {

	public Samordningsnummer(CharSequence number) {
		super(number, a -> (a-60));
	}
	
	@Override
	public int compareTo(Samordningsnummer o) {
		return Long.compare(this.asLong(), o.asLong());
	}
}