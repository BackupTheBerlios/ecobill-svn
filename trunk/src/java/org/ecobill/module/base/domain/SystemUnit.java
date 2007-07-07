package org.ecobill.module.base.domain;

/**
 * Die <code>SystemUnit</code> kann �ber einen landesunabh�ngigen Schl�ssel die
 * jeweilige landesspezifische Einheit �ber die <code>WorkArea</code> herausholen.
 * <p/>
 * User: rro
 * Date: 31.07.2005
 * Time: 17:19:20
 *
 * @author Roman R&auml;dle
 * @version $Id: SystemUnit.java,v 1.6 2005/12/11 17:16:01 raedler Exp $
 * @since EcoBill 1.0
 */
public class SystemUnit extends AbstractI18NDomain {

    /**
     * Die Kategorie zu der diese <code>SystemUnit</code> angeh�rt.
     */
    private String category;

    /**
     * Gibt die Kategorie zu der diese <code>SystemUnit</code> angeh�rt, zur�ck.
     *
     * @return Die Kategorie zu der diese <code>SystemUnit</code> angeh�rt.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setzt die Kategorie zu der diese <code>SystemUnit</code> angeh�rt.
     *
     * @param category Die Kategorie zu der diese <code>SystemUnit</code> angeh�rt.
     */
    public void setCategory(String category) {
        this.category = category;
    }


}
