package org.ecobill.module.base.domain;

import java.util.Locale;

/**
 * Die <code>SystemLocale</code> wird verwendet um diese Anwendung sprachunabh�ngig zu
 * gestalten. Sie beinhaltet die Sprache, das Land und eine evtl. Variante der Sprache.
 * Desweiteren ist es auch m�glich eine <code>SystemLocale</code> mit einer
 * <code>Locale</code> zu vergleichen.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 16:09:34
 *
 * @author Roman R&auml;dle
 * @version $Id: SystemLocale.java,v 1.6 2006/01/30 23:43:13 raedler Exp $
 * @since EcoBill 1.0
 */
public class SystemLocale extends AbstractDomain {

    /**
     * Diese Konstanten werden verwendet um diese <code>SystemLocale</code> mit
     * einer <code>java.util.Locale</code> zu vergleichen. Stimmt eine
     * <code>SystemLocale</code> komplett �berein wird 1 zur�ckgegeben. Sollte
     * diese sich immer mehr bis komplett unterscheiden sinkt die Priorit�t.
     */
    private static final int LANGUAGE_COUNTRY_VARIANT = 1;
    private static final int LANGUAGE_COUNTRY = 2;
    private static final int LANGUAGE = 3;
    private static final int NOTHING = Integer.MAX_VALUE;

    /**
     * Der Schl�ssel der diese <code>SystemLocale</code> eindeutig identifiziert.
     * Bspw. "de_AT" -> Zusammengestzt aus language + "_" + country + "_" + variant.
     */
    private String key;

    /**
     * Die Sprache, bzw. deren K�rzel die zu dieser <code>SystemLocale</code>
     * geh�rt.
     * Bspw. "de" f�r Deutsch.
     */
    private String language = "";

    /**
     * Das Land in dem diese Sprache gesprochen wird.
     * Bspw. "AT" f�r �sterreich.
     */
    private String country = "";

    /**
     * Die Variante dieser Sprache. Kann ein Dialekt sein.
     * Meist aber nicht angegeben.
     */
    private String variant = "";

    /**
     * Die <code>SystemLanguage</code> der <code>SystemLocale</code>.
     */
    private SystemLanguage systemLanguage;

    /**
     * Die <code>SystemCountry</code> der <code>SystemLocale</code>.
     */
    private SystemCountry systemCountry;

    /**
     * Gibt den Schl�ssel zur�ck der diese <code>SystemLocale</code> indentifiziert.
     * <br/>
     * Bspw. "de_AT" f�r die Deutsche Sprache und Land �sterreich.
     *
     * @return Der Schl�ssel der diese <code>SystemLocale</code> identifiziert.
     */
    public String getKey() {
        return key;
    }

    /**
     * Setzt den Schl�ssel der diese <code>SystemLocale</code> eindeutig identifiziert.
     * <br/>
     * Bspw. "de_AT" f�r die Deutsche Sprache und Land �sterreich.
     *
     * @param key Der Schl�ssel der diese <code>SystemLocale</code> identifiziert.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gibt die Sprache zur�ck die dieser <code>SystemLocale</code> zugeordndet ist.
     * <br/>
     * Bspw. "de" f�r die Sprache Deutsch.
     *
     * @return Der Schl�ssel der Sprache -> bspw. "en" f�r English.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Setzt die Sprache f�r diese <code>SystemLocale</code>.
     *
     * @param language Der Schl�ssel der Sprache -> bspw. "en" f�r English.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gibt das Land zur�ck das dieser <code>SystemLocale</code> zugeordnet ist.
     * <br/>
     * Bspw. "AT" f�r das Land �sterreich.
     *
     * @return Der Schl�ssel des Landes -> bspw. "CH" f�r Schweiz.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setzt das Land f�r diese <code>SystemLocale</code>.
     *
     * @param country Der Schl�ssel des Landes -> bspw. "CH" f�r Schweiz.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gibt die Variante dieser <code>SystemLocale</code> zur�ck.
     * Diese Variante kann z.B. ein Dialekt einer Sprache sein.
     * <br/>
     * Bspw. Schw�bisch.
     *
     * @return Der Schl�ssel dieser Variante.
     */
    public String getVariant() {
        return variant;
    }

    /**
     * Setzt die Variante f�r diese <code>SystemLocale</code>.
     * Diese Variante kann z.B. ein Dialekt einer Sprache sein.
     * <br/>
     * Bspw. Schw�bisch.
     *
     * @param variant Der Schl�ssel dieser Variante.
     */
    public void setVariant(String variant) {
        this.variant = variant;
    }

    /**
     * Gibt die <code>SystemLanguage</code> der <code>SystemLocale</code>
     * zur�ck.
     *
     * @return Die <code>SystemLanguage</code> der <code>SystemLocale</code>.
     */
    public SystemLanguage getSystemLanguage() {
        return systemLanguage;
    }

    /**
     * Setzt die <code>SystemLanguage</code> der <code>SystemLocale</code>.
     *
     * @param systemLanguage Die <code>SystemLanguage</code> der <code>SystemLocale</code>.
     */
    public void setSystemLanguage(SystemLanguage systemLanguage) {
        this.systemLanguage = systemLanguage;
    }

    /**
     * Gibt die <code>SystemCountry</code> der <code>SystemLocale</code>
     * zur�ck.
     *
     * @return Die <code>SystemCountry</code> der <code>SystemLocale</code>.
     */
    public SystemCountry getSystemCountry() {
        return systemCountry;
    }

    /**
     * Setzt die <code>SystemCountry</code> der <code>SystemLocale</code>.
     *
     * @param systemCountry Die <code>SystemCountry</code> der <code>SystemLocale</code>.
     */
    public void setSystemCountry(SystemCountry systemCountry) {
        this.systemCountry = systemCountry;
    }

    /**
     * Es wird ein <code>java.util.Locale</code> mit dieser <code>SystemLocale</code>
     * verglichen und die Priorit�t - der Rang - zur�ckgegeben.
     * Je h�her die Priorit�t, desto mehr stimmt diese <code>Locale</code> mit der
     * <code>SystemLocale</code> �berein.
     *
     * @param locale Die <code>Locale</code> mit der diese <code>SystemLocale</code>
     *               verglichen werden soll.
     * @return Gibt die Priorit�t - Grad der �bereinstimmung - zur�ck.
     */
    public int compareTo(Locale locale) {
        if (locale.getLanguage().equals(this.getLanguage()) &&
                locale.getCountry().equals(this.getCountry()) &&
                locale.getVariant().equals(this.getVariant())) {
            return LANGUAGE_COUNTRY_VARIANT;
        }
        else if (locale.getLanguage().equals(this.getLanguage()) &&
                locale.getCountry().equals(this.getCountry())) {
            return LANGUAGE_COUNTRY;
        }
        else if (locale.getLanguage().equals(this.getLanguage())) {
            return LANGUAGE;
        }

        return NOTHING;
    }

    /**
     * Vergleicht eine <code>Locale</code> mit der <code>SystemLocale</code> und gibt
     * true zur�ck falls diese beiden �bereinstimmen, ansonsten wird false zur�ckgegeben.
     *
     * @param locale Die <code>Locale</code> die mit dieser <code>SystemLocale</code>
     *               verglichen werden soll.
     * @return Es wird true zur�ckgegeben falls die <code>Locale</code> und die
     *         <code>SystemLocale</code> �bereinstimmen, ansonsten wird false
     *         zur�ckgegeben.
     */
    public boolean equalsLocale(Locale locale) {

        return locale.getLanguage().equals(this.getLanguage()) &&
                locale.getCountry().equals(this.getCountry()) &&
                locale.getVariant().equals(this.getVariant());

    }

    /**
     * Es wird diese <code>SystemLocale</code> mit dem eingehenden Objekt auf
     * Gleichheit �berpr�ft.
     *
     * @see Object#equals(Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SystemLocale that = (SystemLocale) o;

        if (this.getCountry() != null ? !this.getCountry().equals(that.getCountry()) : that.getCountry() != null)
            return false;
        if (this.getKey() != null ? !this.getKey().equals(that.getKey()) : that.getKey() != null) return false;
        if (this.getLanguage() != null ? !this.getLanguage().equals(that.getLanguage()) : that.getLanguage() != null)
            return false;
        return !(this.getVariant() != null ? !this.getVariant().equals(that.getVariant()) : that.getVariant() != null);
    }

    /**
     * @see Object#hashCode()
     */
    public int hashCode() {
        int result;
        result = (this.getKey() != null ? this.getKey().hashCode() : 0);
        result = 29 * result + (this.getLanguage() != null ? this.getLanguage().hashCode() : 0);
        result = 29 * result + (this.getCountry() != null ? this.getCountry().hashCode() : 0);
        result = 29 * result + (this.getVariant() != null ? this.getVariant().hashCode() : 0);
        return result;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return "key = " + key + " | country = " + country + " | variant = " + variant;
    }
}