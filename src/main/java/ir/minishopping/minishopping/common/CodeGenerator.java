package ir.minishopping.minishopping.common;

import java.security.SecureRandom;
import java.util.Random;

public class CodeGenerator {

    static final private String ALPHABET = "0123456789";
    final private Random random = new SecureRandom();

    public char randomChar() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    public String randomUUID(int length) {
        StringBuilder sb = new StringBuilder();
        while (length > 0) {
            length--;
            sb.append(randomChar());
        }
        return sb.toString();
    }

//func for create random code with spacing char.
/*
    public StringBuilder randomUUID(int length, int spacing, char spacerChar) {
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while (length > 0) {
            if (spacer == spacing) {
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }

        return sb;
    }
*/


}
