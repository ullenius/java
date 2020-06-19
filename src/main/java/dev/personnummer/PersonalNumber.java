package dev.personnummer;

public class PersonalNumber extends Personnummer implements Comparable<PersonalNumber> {
	
	public PersonalNumber(CharSequence number) {
		super(number, a -> a);
	}

	@Override
	public int compareTo(PersonalNumber o) {
		return longFormat().compareTo(o.longFormat());
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
		PersonalNumber other = (PersonalNumber) obj;
		return longFormat().contentEquals(other.longFormat());
	}
	
}
