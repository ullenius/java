# Personnummer
Store and validate Swedish identity numbers (Samordningsnummer and Personnummer)

## Example

```java
class Test {
    public void main(String[] args){
        IdentityNumber one = new PersonalNumber("19130401+2931");
        IdentityNumber two = new PersonalNumber("900101-0017");

        IdentityNumber three = new Samordningsnummer("701063-2391");

        System.out.println(one.shortFormat());
        System.out.println(one.longFormat());
        System.out.println(two.hasLongFormat()); // false

        // Invalid numbers are not allowed
        try {
            new PersonalNumber("9701063-2391");
        } catch (IllegalArgumentException ex) {
            logger.log(ex);
        }

    }
}
```
See [`src/test/java/PersonnummerTest.java`](src/test/java/PersonnummerTest.java) for more examples.

## Licence

[MIT](LICENCE)
