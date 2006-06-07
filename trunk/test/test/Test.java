package test;

import junit.framework.TestCase;
import junit.framework.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import ecobill.module.base.domain.*;
import ecobill.module.base.service.BaseService;
import ecobill.util.exception.LocalizerException;

/**
 * Der <code>PersistenceTest</code> überprüft mit ausgewählten Tests das System, Hibernate und die
 * Datenbank auf Korrektheit.
 * <p/>
 * User: rro
 * Date: 14.07.2005
 * Time: 23:45:45
 *
 * @author Roman R&auml;dle
 * @version $Id: PersistenceTest.java,v 1.2 2005/08/03 13:06:09 raedler Exp $
 * @since EcoBill 1.0
 */
public class Test extends TestCase {

    /**
     * Der <code>ApplicationContext</code> der beim Hochfahren der Anwendung erzeugt wird.
     */
    protected ApplicationContext ac = null;

    /**
     * Der <code>BaseService</code> ist die Business Logik.
     */
    protected BaseService baseService = null;

    /**
     * Wird vor allen Tests ausgeführt.
     *
     * @throws Exception Diese wird bei einem Fehler der Ausführung des Testes
     *                   geworfen.
     */
    protected void setUp() throws Exception {
        super.setUp();

        /*
         * Hole den Application Context mit all seinen erzeugten Beans.
         */
        ac = new ClassPathXmlApplicationContext("test/applicationContext.xml");

        baseService = (BaseService) ac.getBean("baseService");
    }

    /**
     * Testet die Artikel Persistenz.
     * <br/>
     * Hier wird ein Artikel geladen, verglichen, gändert, gespeichert,
     * wieder verglichen.
     *
     * @throws ecobill.util.exception.LocalizerException Diese wird geworfen falls das Localizable Objekt nicht
     *                            gefiltert werden kann.
     */
    public void testArticle() throws LocalizerException {

        System.out.println("LISTE: " + baseService.getAllBillsByBPID((long) 1));
    }
}
