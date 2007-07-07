package org.ecobill.module.base.domain;

import org.ecobill.util.LocalizerUtils;
import org.ecobill.util.exception.LocalizerException;
import org.ecobill.core.system.WorkArea;

import java.util.*;

/**
 * Ein <code>Article</code> beinhaltet alle Informationen �ber eine bestimmten Artikel. Es sind
 * bspw der Einzelpreis, die Einheit, der Lagerbestand, Gebindeeinheit und -menge und die
 * verschiedenen landesspezifischen Beschreibungen des Artikels angegeben.
 * Desweiteren enth�lt diese Klasse Business Methoden um eine landesspezifische Beschreibung zu
 * erhalten.
 * Wird dieses Objekt von <code>AbstractDomain</code> abgeleitet ist es auch m�glich jede Instanz
 * von dieser Klasse in der Datenbank und somit Persistent abzulegen.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 00:37:27
 *
 * @author Roman R&auml;dle
 * @version $Id: Article.java,v 1.8 2006/01/30 23:43:13 raedler Exp $
 * @since EcoBill 1.0
 */
public class Article extends AbstractDomain {

    /**
     * Die Artikelnummer des Artikels.
     */
    private String articleNumber;

    /**
     * Diese <code>SystemUnit</code> gibt die Einheit an des Artikels an.
     * <br/>
     * Bspw. "weight" -> Dieser Schl�ssel ist in einem <code>ResourceBundle</code>
     * enthalten und kann somit darin landesspezifisch angegeben werden.
     */
    private SystemUnit unit;

    /**
     * Der Einzelpreis des Artikels.
     */
    private double price;

    /**
     * Der aktuelle Lagerbestand des Artikels.
     */
    private double inStock;

    /**
     * Die Gebindemenge des Artikels.
     */
    private double bundleCapacity;

    /**
     * Diese <code>SystemUnit</code> gibt die Gebindeeinheit des Artikels an.
     *
     * @see SystemUnit
     */
    private SystemUnit bundleUnit;

    /**
     * Dieses <code>Set</code> beinhaltet alle Beschreibungen des Artikels.
     * Jede Beschreibung ist eine Instanz der Klasse <code>ArticleDescription</code>
     * - diese Klasse implementiert das Interface <code>Localizable</code> -
     * somit kann die landesspezifische Beschreibung herausgefiltert werden.
     */
    private Set<ArticleDescription> descriptions = new HashSet<ArticleDescription>();

    /**
     * Gibt die Artikelnummer des Artikels zur�ck.
     * Diese Nummer sollte eindeutig sein.
     *
     * @return Die Artikelnummer des Artikels.
     */
    public String getArticleNumber() {
        return articleNumber;
    }

    /**
     * Setzt die Artikelnummer des Artikels.
     *
     * @param articleNumber Die Artikelnummer des Artikels.
     */
    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    /**
     * Gibt die <code>SystemUnit</code>, unter dessen Key man in einem
     * <code>ResourceBundle</code> den dazugeh�rigen Wert finden kann,
     * zur�ck.
     * Der <code>SystemUnit</code> Key weist auf eine landesspezifische
     * Einheit wie bspw. (unit) St�ck.
     *
     * @return Die <code>SystemUnit</code> dessen Key auf den landesspezifischen
     *         Wert in einem <code>ResourceBundle</code> zeigen kann.
     */
    public SystemUnit getUnit() {
        return unit;
    }

    /**
     * Setzt die <code>SystemUnit</code> unter dessen Key man in einem
     * <code>ResourceBundle</code> den dazugeh�rigen Wert finden kann.
     * Der <code>SystemUnit</code> Key weist auf eine landesspezifische
     * Einheit wie bspw. (unit) St�ck.
     *
     * @param systemUnit Die <code>SystemUnit</code> dessen Key auf den landesspezifischen
     *                   Wert in einem <code>ResourceBundle</code> zeigen kann.
     */
    public void setUnit(SystemUnit unit) {
        this.unit = unit;
    }

    /**
     * Gibt den Einzelpreis des Artikels zur�ck.
     *
     * @return Der Einzelpreis des Artikels.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setzt den Einzelpreis des Artikels.
     *
     * @param price Der Einzelpreis des Artikels.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gibt den aktuellen Lagerbestand des Artikels zur�ck.
     *
     * @return Der aktuelle Lagerbestand des Artikels.
     */
    public double getInStock() {
        return inStock;
    }

    /**
     * Setzt den aktuellen Lagerbestand des Artikels.
     *
     * @param inStock Der aktuelle Lagerbestand des Artikels.
     */
    public void setInStock(double inStock) {
        this.inStock = inStock;
    }

    /**
     * Gibt die Gebindemenge des Artikels zur�ck.
     *
     * @return Die Gebindemenge des Artikels.
     */
    public double getBundleCapacity() {
        return bundleCapacity;
    }

    /**
     * Setzt die Gebindemenge des Artikels.
     *
     * @param bundleCapacity Die Gebindemenge des Artikels.
     */
    public void setBundleCapacity(double bundleCapacity) {
        this.bundleCapacity = bundleCapacity;
    }

    /**
     * Gibt die Gebinde <code>SystemUnit</code> des Artikles zur�ck.
     *
     * @return Die Gebindeeinheit des Artikels.
     * @see this#getUnit()
     */
    public SystemUnit getBundleUnit() {
        return bundleUnit;
    }

    /**
     * Setzt die Gebinde <code>SystemUnit</code> des Artikles.
     *
     * @param bundleUnit Die Gebindeeinheit des Artikels.
     * @see this#setUnit(SystemUnit)
     */
    public void setBundleUnit(SystemUnit bundleUnit) {
        this.bundleUnit = bundleUnit;
    }

    /**
     * F�gt dem Artikel Beschreibungs <code>Set</code> eine <code>ArticleDescription</code>
     * hinzu, falls diese in diesem <code>Set</code> noch nicht vorhanden ist.
     *
     * @param articleDescription Eine <code>ArticleDescription</code> die die landesspezifische
     *                           Beschreibung f�r einen Artikel enth�lt.
     */
    public void addArticleDescription(ArticleDescription articleDescription) {
        if (descriptions == null) {
            descriptions = new HashSet<ArticleDescription>();
        }

        if (!descriptions.contains(articleDescription)) {
            descriptions.add(articleDescription);
        }
    }

    /**
     * Gibt das <code>Set</code> aller <code>ArticleDescription</code> zur�ck
     * die zu diesem <code>Article</code> geh�ren.
     *
     * @return Ein <code>Set</code> von <code>ArticleDescription</code>.
     */
    public Set<ArticleDescription> getDescriptions() {
        return descriptions;
    }

    /**
     * Setzt ein <code>Set</code> mit <code>ArticleDescription</code>.
     *
     * @param descriptions Ein <code>Set</code> von <code>ArticleDescription</code>.
     */
    public void setDescriptions(Set<ArticleDescription> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Gibt die landesspezifische Beschreibung von diesem Artikel zur�ck.
     *
     * @return Die landesspezifische Beschreibung von diesem Artikel.
     */
    public String getLocalizedDescription() {

        /*
         * Hier wird die eingestellte <code>Locale</code> aus der <code>WorkArea</code>
         * geholt. Sollte keine <code>Locale</code> eingestellt sein wird die Default
         * <code>Locale</code> zur�ckgegeben.
         */
        Locale locale = WorkArea.getLocale();

        /*
         * Hole die landesspezifische Beschreibung die der <code>Locale</code> am meisten
         * �hnelt.
         */
        ArticleDescription articleDescription = null;
        try {
            articleDescription = (ArticleDescription) LocalizerUtils.getLocalizedObject(this.getDescriptions(), locale);
        }
        catch (LocalizerException e) {
            // Es kann keine Localized Beschreibung gefunden werden.
        }

        /*
         * Falls es eine Localized Beschreibung gibt wird der R�ckgabe <code>String</code> mit
         * der landesspezifischen Beschreibung ersetzt, ansonsten wird eine leerer <code>String</code>
         * zur�ckgegeben.
         */
        String returnDescription = "";
        if (articleDescription != null) {
            returnDescription = articleDescription.getDescription();
        }

        /*
        * Hier wird die Beschreibung zur�ckgegeben.
        */
        return returnDescription;
    }

    /**
     * Gibt die landesspezifische <code>ArticleDescription</code> von diesem Artikel zur�ck.
     *
     * @return Die landesspezifische <code>ArticleDescription</code> von diesem Artikel.
     */
    public ArticleDescription getLocalizedArticleDescription() throws LocalizerException {

        /*
         * Hier wird die eingestellte <code>Locale</code> aus der <code>WorkArea</code>
         * geholt. Sollte keine <code>Locale</code> eingestellt sein wird die Default
         * <code>Locale</code> zur�ckgegeben.
         */
        Locale locale = WorkArea.getLocale();

        /*
         * Hole die landesspezifische Beschreibung die der <code>Locale</code> am meisten
         * �hnelt und zur�ckgegeben.
         */
        return (ArticleDescription) LocalizerUtils.getLocalizedObject(this.getDescriptions(), locale);
    }

    /**
     * Es wird dieser <code>Article</code> mit dem eingehenden Objekt auf Gleichheit
     * �berpr�ft.
     *
     * @see Object#equals(Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Article article = (Article) o;

        if (Double.compare(article.bundleCapacity, bundleCapacity) != 0) return false;
        if (Double.compare(article.inStock, inStock) != 0) return false;
        if (Double.compare(article.price, price) != 0) return false;
        if (articleNumber != null ? !articleNumber.equals(article.articleNumber) : article.articleNumber != null)
            return false;
        if (bundleUnit != null ? !bundleUnit.equals(article.bundleUnit) : article.bundleUnit != null) return false;
        if (descriptions != null ? !descriptions.equals(article.descriptions) : article.descriptions != null)
            return false;
        return !(unit != null ? !unit.equals(article.unit) : article.unit != null);
    }

    /**
     * @see Object#hashCode()
     */
    public int hashCode() {
        int result;
        long temp;
        result = (articleNumber != null ? articleNumber.hashCode() : 0);
        result = 29 * result + (unit != null ? unit.hashCode() : 0);
        temp = price != +0.0d ? Double.doubleToLongBits(price) : 0L;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        temp = inStock != +0.0d ? Double.doubleToLongBits(inStock) : 0L;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        temp = bundleCapacity != +0.0d ? Double.doubleToLongBits(bundleCapacity) : 0L;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        result = 29 * result + (bundleUnit != null ? bundleUnit.hashCode() : 0);
        result = 29 * result + (descriptions != null ? descriptions.hashCode() : 0);
        return result;
    }
}
