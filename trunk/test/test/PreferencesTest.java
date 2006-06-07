package test;

import ecobill.module.base.ui.article.ArticleTable;
import ecobill.core.system.Constants;

import java.util.prefs.Preferences;

/**
 * Created by IntelliJ IDEA.
 * User: Roman Georg Rädle
 * Date: 29.09.2005
 * Time: 10:33:31
 * To change this template use File | Settings | File Templates.
 */
public class PreferencesTest {

    public static void main(String[] args) {

        Preferences prefs = Preferences.userNodeForPackage(ArticleTable.class);

        Preferences tableOrder = prefs.node("table_order");

        tableOrder.putInt(Constants.ARTICLE_NR, 0);
        tableOrder.putInt(Constants.UNIT, 1);
        tableOrder.putInt(Constants.SINGLE_PRICE, 2);
        tableOrder.putInt(Constants.DESCRIPTION, 3);
        tableOrder.putInt(Constants.IN_STOCK, 4);
        tableOrder.putInt(Constants.BUNDLE_UNIT, 5);
        tableOrder.putInt(Constants.BUNDLE_CAPACITY, 6);
    }
}
