package dev.personnummer;

public class Samordningsnummer extends Personnummer implements Comparable<Samordningsnummer> {

	public Samordningsnummer(CharSequence number) {
		super(number, a -> (a-60));
	}
	
	@Override
	public int compareTo(Samordningsnummer o) {
		return this.longFormat().compareTo(o.longFormat());
	}
	
	@Override
	public int hashCode() {
		return longFormat().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Samordningsnummer other = (Samordningsnummer) obj;
		return longFormat().contentEquals(other.longFormat());
	}
	
}