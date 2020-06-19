package dev.personnummer;

public class Samordningsnummer extends Personnummer {

	public Samordningsnummer(CharSequence number) {
		super(number, a -> (a-60));
	}
}