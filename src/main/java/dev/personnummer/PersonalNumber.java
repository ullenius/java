package dev.personnummer;

public class PersonalNumber extends Personnummer implements Comparable<PersonalNumber> {
	
	public PersonalNumber(CharSequence number) {
		super(number, a -> a);
	}

	@Override
	public int compareTo(PersonalNumber o) {
		return Long.compare(this.asLong(), o.asLong());
	}
	
}
