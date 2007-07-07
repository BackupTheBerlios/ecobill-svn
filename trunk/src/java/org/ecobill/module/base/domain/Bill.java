package org.ecobill.module.base.domain;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
// @todo document me!

/**
 * Bill.
 * <p/>
 * User: rro
 * Date: 21.07.2005
 * Time: 13:29:28
 *
 * @author Roman R&auml;dle
 * @version $Id: Bill.java,v 1.7 2006/02/02 22:18:27 raedler Exp $
 * @since DAPS INTRA 1.0
 */
public class Bill extends AbstractDomain {

    /**
     * Der Gesch�ftspartner dem diese Rechnung zugeordnet werden soll.
     */
    private BusinessPartner businessPartner;

    /**
     * Die eindeutige Rechnungsnummer.
     */
    private String billNumber;

    /**
     * Das Datum an dem diese Rechnung ausgestellt wurde.
     */
    private Date billDate;

    /**
     * Gibt den Rechnungstyp an.
     */
    // @todo durch ENUM ersetzen
    //private CharacterisationType characterisationType;
    private String characterisationType;

    /**
     * Ein Freitext der optional angegeben werden kann. Dieser Freitext kann bspw. an den
     * Anfang eines Reports gesetzt werden.
     */
    private String prefixFreetext;

    /**
     * Ein Freitext der optional angegeben werden kann. Dieser Freitext kann bspw. ans Ende
     * eines Reports gesetzt werden.
     */
    private String suffixFreetext;

    /**
     * Ein <code>Set</code> mit allen Lieferscheinen von der diese Rechnung
     * erstellt wird.
     */
    private Set<DeliveryOrder> deliveryOrders;


    /**
     * A new bill with an empty delivery orders <code>Set</code>.
     */
    public Bill() {
        deliveryOrders = new HashSet<DeliveryOrder>();
    }

    /**
     * Gibt den zu dieser Rechnung dazugeh�rigen <code>BusinessPartner</code>
     * zur�ck.
     *
     * @return Der zugeh�rige <code>BusinessPartner</code>.
     */
    public BusinessPartner getBusinessPartner() {
        return businessPartner;
    }

    /**
     * Setzt den <code>BusinessPartner</code> der zu dieser Rechnung geh�rt.
     *
     * @param businessPartner Der zugeh�rige <code>BusinessPartner</code>.
     */
    public void setBusinessPartner(BusinessPartner businessPartner) {
        this.businessPartner = businessPartner;
    }

    /**
     * Gibt die Rechnungsnummer zur�ck.
     *
     * @return Die Rechnungsnummer.
     */
    public String getBillNumber() {
        return billNumber;
    }

    /**
     * Setzt die Rechnungsnummer.
     *
     * @param billNumber Die Rechnungsnummer.
     */
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    /**
     * Gibt das Rechnungsdatum, an dem diese Rechnung ausgetellt wurde,
     * zur�ck.
     *
     * @return Das Rechnungsdatum.
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * Setzt das Rechnungsdatum, an dem diese Rechnung ausgestellt wird.
     *
     * @param billDate Das Rechungsdatum.
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * Gibt den <code>CharacterisationType</code> dieser Rechnung zur�ck.
     *
     * @return Der Rechnungs <code>CharacterisationType</code>.
     */
    // @todo siehe oben
    public String getCharacterisationType() {
        return characterisationType;
    }

    /**
     * Setzt den <code>CharacterisationType</code> dieser Rechnung.
     *
     * @param charaterisationType Der Rechnungs <code>CharacterisationType</code>.
     */
    // @todo siehe oben
    public void setCharacterisationType(String charaterisationType) {
        this.characterisationType = charaterisationType;
    }

    /**
     * Gibt den Freitext, der optional angegeben werden kann, zur�ck. Dieser Freitext
     * kann bspw. an den Anfang eines Reports gesetzt werden.
     *
     * @return Der Freitext der optional angegeben werden kann.
     */
    public String getPrefixFreetext() {
        return prefixFreetext;
    }

    /**
     * Setzt den Freitext, der optional angegeben werden kann. Dieser Freitext
     * kann bspw. an den Anfang eines Reports gesetzt werden.
     *
     * @param prefixFreetext Der Freitext der optional angegeben werden kann.
     */
    public void setPrefixFreetext(String prefixFreetext) {
        this.prefixFreetext = prefixFreetext;
    }

    /**
     * Gibt den Freitext, der optional angegeben werden kann, zur�ck. Dieser Freitext
     * kann bspw. ans Ende eines Reports gesetzt werden.
     *
     * @return Der Freitext der optional angegeben werden kann.
     */
    public String getSuffixFreetext() {
        return suffixFreetext;
    }

    /**
     * Setzt den Freitext, der optional angegeben werden kann. Dieser Freitext
     * kann bspw. ans Ende eines Reports gesetzt werden.
     *
     * @param suffixFreetext Der Freitext der optional angegeben werden kann.
     */
    public void setSuffixFreetext(String suffixFreetext) {
        this.suffixFreetext = suffixFreetext;
    }

    /**
     * Gibt ein <code>Set</code> mit <code>DeliveryOrder</code> zur�ck.
     * Von diesen Lieferscheinen wurde diese Rechnung erstellt.
     *
     * @return Ein <code>Set</code> von <code>DeliveryOrder</code>.
     */
    public Set<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    /**
     * Setzt ein <code>Set</code> mit <code>DeliveryOrder</code>.
     *
     * @param deliveryOrders Ein <code>Set</code> von <code>DeliveryOrder</code>.
     */
    public void setDeliveryOrders(Set<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    /**
     * F�gt dem <code>Set</code> mit <code>DeliveryOrder</code> der Rechnung einen
     * Lieferschein hinzu.
     *
     * @param deliveryOrder Eine <code>DeliveryOrder</code> die zur Rechnung hinzugef�gt
     *                      werden soll.
     */
    public void addDeliveryOrder(DeliveryOrder deliveryOrder) {

        if (deliveryOrders == null) {
            deliveryOrders = new HashSet<DeliveryOrder>();
        }

        deliveryOrders.add(deliveryOrder);
    }

    /**
     * Es wird dieser <code>DeliveryOrder</code> mit dem eingehenden Objekt
     * auf Gleichheit �berpr�ft.
     *
     * @see Object#equals(Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Bill bill = (Bill) o;

        if (this.getBillDate() != null ? !this.getBillDate().equals(bill.getBillDate()) : bill.getBillDate() != null)
            return false;
        if (this.getBillNumber() != null ? !this.getBillNumber().equals(bill.getBillNumber()) : bill.getBillNumber() != null)
            return false;
        if (this.getBusinessPartner() != null ? !this.getBusinessPartner().equals(bill.getBusinessPartner()) : bill.getBusinessPartner() != null)
            return false;
        return !(this.getDeliveryOrders() != null ? !this.getDeliveryOrders().equals(bill.getDeliveryOrders()) : bill.getDeliveryOrders() != null);
    }

    /**
     * @see Object#hashCode()
     */
    public int hashCode() {
        int result;
        result = (this.getBusinessPartner() != null ? this.getBusinessPartner().hashCode() : 0);
        result = 29 * result + (this.getBillNumber() != null ? this.getBillNumber().hashCode() : 0);
        result = 29 * result + (this.getBillDate() != null ? this.getBillDate().hashCode() : 0);
        result = 29 * result + (this.getDeliveryOrders() != null ? this.getDeliveryOrders().hashCode() : 0);
        return result;
    }
}