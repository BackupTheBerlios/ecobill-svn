package org.ecobill;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.BeansException;
import org.ecobill.core.ui.ConnectionSettings;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.applet.Applet;

/**
 * Starting Economy Bill Agenda as an applet.
 * <p/>
 * User: rro
 * Date: 13.02.2006
 * Time: 17:47:38
 *
 * @author Roman R&auml;dle
 * @version $Id$
 * @since Ecobill 1.1.1
 */
public class StartApplet extends Applet {

    private Start start;

    /**
     * @see java.applet.Applet#init()
     */
    public void init() {
        start = new Start();

        new ConnectionSettings(start);
    }

    public void startUp(String className, String url, String username, String password) {

        Class clazz = null;
        try {
            clazz = getClass().getClassLoader().loadClass(className);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        if (clazz == null) {
            new ConnectionSettings(start);
            return;
        }

        Object o = null;

        try {
            o = clazz.newInstance();
        }
        catch (InstantiationException ie) {
            ie.printStackTrace();
        }
        catch (IllegalAccessException iae) {
            iae.printStackTrace();
        }

        if (o instanceof Driver) {

            Driver drv = (Driver) o;

            try {
                drv.acceptsURL(url);
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();

                new ConnectionSettings(start);
                return;
            }

            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);

            try {
                drv.connect(url, props);
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();

                new ConnectionSettings(start);
                return;
            }
        }

        try {
            new ClassPathXmlApplicationContext("ecobill/config/applicationContext.xml");
        }
        catch (BeansException be) {
            be.printStackTrace();

            System.exit(100);
        }
    }
}