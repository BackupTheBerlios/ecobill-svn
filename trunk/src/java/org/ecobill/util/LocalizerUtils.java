package org.ecobill.util;

import org.ecobill.module.base.domain.SystemLocale;
import org.ecobill.util.exception.LocalizerException;

import java.util.Collection;
import java.util.Locale;

/**
 * Die Klasse <code>LocalizerUtils</code> erm�glicht es �ber das Interface <code>Localizable</code>
 * aus einer <code>Collection</code> ein landesspezifisches Objekt herauszufiltern.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 16:07:00
 *
 * @author Roman R&auml;dle
 * @version $Id: LocalizerUtils.java,v 1.5 2006/01/30 23:43:14 raedler Exp $
 * @since EcoBill 1.0
 */
public class LocalizerUtils {

    /**
     * Filtert aus einer <code>Collection</code> ein landesspezifisches Objekt heraus und
     * gibt dieses zur�ck. Zum Vergleich wird die Default <code>Locale</code> herangezogen.
     *
     * @param localizables Eine <code>Collection</code> die Objekte beinhaltet die das
     *                     Interface <code>Localizable</code> implementieren.
     * @return Gibt das landesspezifische Objekt zur�ck.
     */
    public static Object getLocalizedObject(Collection localizables) throws LocalizerException {

        /*
         * Hier wird das localizedObject abgelegt.
         */
        Object localizedObject;

        /*
         * Filtere das Objekt aus der <code>Collection</code> das der Default <code>Locale</code>
         * am meisten �hnelt.
         */
        localizedObject = localize(localizables, Locale.getDefault());

        /*
         * Wird kein Objekt gefunden das der Default <code>Locale</code> �hnelt wird eine
         * <code>LocalizerException</code> geworfen.
         */
        if (localizedObject == null)
            throw new LocalizerException("Es ist kein localizedObject in der Collection vorhanden.");

        return localizedObject;
    }

    /**
     * Filtert aus einer <code>Collection</code> ein landesspezifisches Objekt heraus und
     * gibt dieses zur�ck. Zum Vergleich wird die <code>Locale</code> herangezogen die als
     * Parameter �bergeben wird.
     *
     * @param localizables Eine <code>Collection</code> die Objekte beinhaltet die das
     *                     Interface <code>Localizable</code> implementieren.
     * @param locale       Die <code>Locale</code> die einem Objekt in der <code>Collection</code>
     *                     �hneln muss.
     * @return Gibt das landesspezifische Objekt zur�ck.
     */
    public static Object getLocalizedObject(Collection localizables, Locale locale) throws LocalizerException {

        /*
         * Hier wird das localizedObject abgelegt.
         */
        Object localizedObject;

        /*
         * Filtere das Objekt aus der <code>Collection</code> das der <code>Locale</code>
         * am meisten �hnelt.
         */
        localizedObject = localize(localizables, locale);

        /*
         * Sollte noch kein Objekt gefunden worden sein das der <code>Locale</code> locale entspricht
         * wird das selbe nochmal mit der Default <code>Locale</code> versucht.
         */
        if (localizedObject == null) {
            localizedObject = getLocalizedObject(localizables);
        }

        return localizedObject;
    }

    /**
     * Gibt ein <code>Object</code> aus der <code>Collection</code> zur�ck, falls eines davon mit der
     * <code>Locale</code> �bereinstimmt. Falls es keine �bereinstimmung mit einem <code>Object</code>
     * geben sollte wird eine <code>LocalizerException</code> geworfen.
     *
     * @param localizables Eine <code>Collection</code> die Objekte beinhaltet die das
     *                     Interface <code>Localizable</code> implementieren.
     * @param locale       Die <code>Locale</code> die einem Objekt in der <code>Collection</code>
     *                     exakt gleichen muss.
     * @return Gibt das landesspezifische Objekt zur�ck.
     * @throws LocalizerException Diese <code>LocalizerException</code> wird geworfen falls kein
     *                            passendes <code>Object</code> gefunden wurde.
     */
    public static Object getExactLocalizedObject(Collection localizables, Locale locale) throws LocalizerException {

        for (Object o : localizables) {

            if (o instanceof Localizable) {
                Localizable localizable = (Localizable) o;

                SystemLocale systemLocale = localizable.getSystemLocale();
                if (systemLocale.equalsLocale(locale)) {
                    return localizable;
                }
            }
        }

        throw new LocalizerException("Es ist kein localizedObject [Locale=\"" + locale + "\"] in der Collection vorhanden.");
    }

    /**
     * Diese Methode erm�glicht es einer <code>Collection</code> ein landesspezifisches Objekt
     * herauszufiltern, allerdings m�ssen die Objekte das Interface <code>Localzibale</code>
     * implementieren.
     *
     * @param localizables Eine <code>Collection</code> die Objekte beinhaltet die das
     *                     Interface <code>Localizable</code> implementieren.
     * @param locale       Die <code>Locale</code> die einem Objekt in der <code>Collection</code>
     *                     �hneln muss.
     * @return Gibt das landesspezifische Objekt zur�ck.
     */
    private static Object localize(Collection localizables, Locale locale) {

        Object localizedObject = null;

        /*
         * Die Priorit�t wird auf Maximum gesetzt.
         */
        int priority = Integer.MAX_VALUE;

        /*
         * Iteriere �ber die <code>Collection</code>.
         */
        for (Object o : localizables) {

            if (o instanceof Localizable) {
                Localizable localizable = (Localizable) o;

                SystemLocale systemLocale = localizable.getSystemLocale();

                int systemLocalePriority;
                if ((systemLocalePriority = systemLocale.compareTo(locale)) < priority) {
                    priority = systemLocalePriority;

                    localizedObject = o;
                }
            }
            else if (o instanceof SystemLocale) {
                SystemLocale systemLocale = (SystemLocale) o;

                int systemLocalePriority;
                if ((systemLocalePriority = systemLocale.compareTo(locale)) < priority) {
                    priority = systemLocalePriority;

                    localizedObject = o;
                }
            }
        }

        return localizedObject;
    }
}