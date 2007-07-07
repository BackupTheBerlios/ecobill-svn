package org.ecobill.util;

/**
 * Der <code>NumberGenerator</code> erm�glicht es Operationen auf Nummern auszuf�hren. Bspw ist es
 * m�glich aus einem mit Nullen gef�llten Zahlenstring die n�chste fortlaufende Nummer zu erzeugen.
 * <p/>
 * User: rro
 * Date: 25.10.2005
 * Time: 09:50:57
 *
 * @author Roman R&auml;dle
 * @version $Id: NumberGenerator.java,v 1.2 2006/01/30 23:43:14 raedler Exp $
 * @since EcoBill 1.0
 */
public class NumberGenerator {

    /**
     * Es wird ein mit Nullen gef�llter Zahlenstring in die n�chste fortlaufende,
     * mit Nullen gef�llte, Zahl erweitert. Es kann hier die L�nge der r�ckkehrenden
     * Nummer angegeben werden.
     *
     * @param actualNumber Die aktuelle, mit Nullen gef�llte, Nummer.
     * @param length       Die L�nge der Nummer die zur�ckgegeben wird.
     * @return Die n�chste fortlaufende, mit Nullen gef�llte, Nummer.
     */
    public static String createNextZerofilledNumber(String actualNumber, int length) {

        Long number = Long.parseLong(actualNumber);

        number++;

        String zerofilled = number.toString();

        for (int i = zerofilled.length(); i < length; i++) {
            zerofilled = "0" + zerofilled;
        }

        return zerofilled;
    }

    /**
     * Es wird ein mit Nullen gef�llter Zahlenstring in die n�chste fortlaufende,
     * mit Nullen gef�llte, Zahl erweitert.
     *
     * @param actualNumber Die aktuelle, mit Nullen gef�llte, Nummer.
     * @return Die n�chste fortlaufende, mit Nullen gef�llte, Nummer.
     */
    public static String createNextZerofilledNumber(String actualNumber) {

        int length = actualNumber.length();

        return createNextZerofilledNumber(actualNumber, length);
    }
}