package org.ecobill.module.base.dao.impl;

import org.ecobill.module.base.dao.BaseDao;
import org.ecobill.module.base.dao.exception.NoSuchSystemLocaleException;
import org.ecobill.module.base.dao.exception.NonUniqueHibernateResultException;
import org.ecobill.module.base.dao.exception.NoSuchArticleException;
import org.ecobill.module.base.domain.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.io.Serializable;

/**
 * Das <code>BaseDaoImpl</code> ist eine Implementation des Interfaces <code>BaseDao</code>.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 12:29:43
 *
 * @author Roman R&auml;dle
 * @version $Id: BaseDaoImpl.java,v 1.17 2006/02/08 01:25:54 raedler Exp $
 * @see BaseDao
 * @since EcoBill 1.0
 */
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

    /**
     * L�dt das <code>Object</code> mit der entsprechenden id von der Datenbank.
     *
     * @param clazz Die Klasse des <code>Object</code>.
     * @param id    Die id des <code>Object</code>.
     * @return Gibt das gefundene <code>Object</code> zur�ck, ansonsten eine neu
     *         erzeugte Instanz der Klasse.
     * @see BaseDao#load(Class, java.io.Serializable)
     */
    public Object load(Class clazz, Serializable id) {
        return getHibernateTemplate().load(clazz, id);
    }

    /**
     * L�dt alle Objekte der persistenten Klasse und gibt diese in einer <code>List</code>
     * zur�ck.
     *
     * @param clazz Die Klasse zu der diese Objekte geh�ren.
     * @return Die <code>List</code> die alle persistenten Objekte beinhaltet.
     * @see BaseDao#loadAll(Class)
     */
    public List loadAll(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * L�st eine Entit�t von der aktuellen <code>Session</code>.
     *
     * @param entity Die zu l�sende Entit�t.
     * @see BaseDao#evict(Object)
     */
    public void evict(Object entity) {
        getHibernateTemplate().evict(entity);
    }

    /**
     * Speichert oder �ndert das <code>Object</code> in der Datenbank.
     *
     * @param entity Das zu speichernde <code>Object</code>.
     * @see BaseDao#saveOrUpdate(Object)
     */
    public void saveOrUpdate(Object entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * L�scht das <code>Object</code> aus der Datenbank.
     *
     * @param entity Das zu l�schende <code>Object</code>.
     */
    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * Sucht den maximalen Wert einer Spalte in der Datenbank.
     *
     * @param clazz Die Klasse zu der dieser Parameter geh�rt.
     * @param param Der Parameter aus dem der maximale Wert herausgeholt werden soll.
     * @return Der maximale Wert.
     * @see BaseDao#getMaximumByParam(Class, String)
     */
    public Long getMaximumByParam(Class clazz, String param) {
        List l = getHibernateTemplate().find("select max(" + param + ") from " + clazz.getName());

        Long max = (Long) l.get(0);

        if (max != null) {
            return max;
        }

        return 0L;
    }

    /**
     * Gibt die Nummern Sequenz zur�ck, deren Schl�ssel auf den im Parameter festgelegten
     * Schl�ssel passt.
     *
     * @param sequenceKey Der Schl�ssel der Sequenz.
     * @return Die <code>NumberSequence</code> deren Schl�ssel mit dem Schl�ssel des Parameters
     *         �bereinstimmt.
     * @see BaseDao#getNumberSequenceByKey(String)
     */
    public NumberSequence getNumberSequenceByKey(String sequenceKey) {
        List sequenceList = getHibernateTemplate().find("from " + NumberSequence.class.getName() + " as numberSequence where numberSequence.key = ?", new Object[]{sequenceKey});

        if (sequenceList.size() > 1) throw new NonUniqueHibernateResultException("Es wurde keine eindeutige Nummern Sequenz gefunden.");

        return (NumberSequence) sequenceList.get(0);
    }

    /**
     * Gibt die <code>SystemLocale</code>, deren localeKey des Parameter localeKey entspricht,
     * zur�ck.
     *
     * @param systemLocaleKey Der Schl�ssel unter dem eine <code>SystemLocale</code> gefunden werden
     *                        soll.
     * @return Die <code>SystemLocale</code> die unter diesem localeKey gefunden wurde.
     * @throws DataAccessException         Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                                     aufgetritt.
     * @throws NoSuchSystemLocaleException Diese wird geworfen falls keine <code>SystemLocale</code>
     *                                     unter diesem Schl�ssel gefunden wurde.
     * @throws NonUniqueHibernateResultException
     *                                     Diese wird geworfen falls mehr als eine <code>SystemLocale</code>
     *                                     zur�ckgeliefert wird und das Ergebnis somit nicht eindeutig ist.
     * @see NoSuchSystemLocaleException
     * @see NonUniqueHibernateResultException
     * @see BaseDao#getSystemLocaleBySystemLocaleKey(String)
     */
    public SystemLocale getSystemLocaleBySystemLocaleKey(String systemLocaleKey) throws DataAccessException, NoSuchSystemLocaleException, NonUniqueHibernateResultException {
        List systemLocaleList = getHibernateTemplate().find("from " + SystemLocale.class.getName() + " as systemLocale where systemLocale.key = ?", new Object[]{systemLocaleKey});

        if (systemLocaleList.size() < 1)
            throw new NoSuchSystemLocaleException("Es wurde keine SystemLocale mit dem Schl�ssel [key = " + systemLocaleKey + "] gefunden.");
        if (systemLocaleList.size() > 1)
            throw new NonUniqueHibernateResultException("Es wurde keine eideutige SystemLocale mit dem Schl�ssel [key = " + systemLocaleKey + "] gefunden. Bitte �berpr�fen Sie Ihren Datenbankbestand.");

        return (SystemLocale) systemLocaleList.get(0);
    }

    /**
     * Gibt eine <code>List</code> mit <code>SystemUnit</code>, die zu einer bestimmten Kategorie geh�rt,
     * zur�ck.
     *
     * @param category Die Kategorie zu dieser die <code>SystemUnit</code> geh�ren.
     * @return Eine <code>List</code> mit <code>SystemUnit</code> einer bestimmten Kategorie.
     * @throws DataAccessException Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                             auftritt.
     * @see org.ecobill.module.base.dao.BaseDao#getSystemUnitsByCategory(String)
     */
    public List getSystemUnitsByCategory(String category) throws DataAccessException {
        return getHibernateTemplate().find("from " + SystemUnit.class.getName() + " as systemUnit where systemUnit.category = ?", new Object[]{category});
    }

    /**
     * Gibt eine <code>List</code> mit allen <code>SystemUnit</code> die in der Datenbank verf�gbar
     * sind zur�ck.
     *
     * @return Eine <code>List</code> mit allen <code>SystemUnit</code> in der Datenbank.
     * @throws DataAccessException Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                             auftritt.
     * @see org.ecobill.module.base.dao.BaseDao#getAllSystemUnits()
     */
    public List getAllBusinessPartnerIds() throws DataAccessException {
        return getHibernateTemplate().find("select bp.id from org.ecobill.module.base.domain.BusinessPartner as bp");
    }

    /**
     * Gibt den <code>ReduplicateArticle</code>, dessen ID der Parameter ID entspricht, zur�ck.
     *
     * @param id Die ID unter der ein <code>ReduplicateArticle</code> in der Datenbank abgelegt
     *           ist.
     * @return Liste <code>ReduplicateArticle</code> der unter dieser DeliverOrderID gefunden wurde.
     * @throws org.springframework.dao.DataAccessException
     *          Diese wird geworfen falls ein Fehler beim Datenzugriff
     *          aufgetritt.
     * @see BaseDao#getAllReduplicatedArticleByDOId(Long)
     */
    public List getAllReduplicatedArticleByDOId(Long id) throws DataAccessException {
        return getHibernateTemplate().find("from org.ecobill.module.base.domain.ReduplicatedArticle as ra where ra.deliveryOrder = ?", new Object[]{id});
    }

    /**
     * Gibt den <code>Article</code>, dessen Artikelnummer dem <code>String</code> articleNumber
     * entspricht zur�ck.
     *
     * @param articleNumber Die eindeutitge Artikelnummer unter der ein Artikel in der
     *                      Datenbank abgelegt ist.
     * @return Der <code>Article</code> der unter dieser Artikelnummer gefunden wurde.
     * @throws DataAccessException    org.springframework.dao.DataAccessException
     *                                Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                                aufgetritt.
     * @throws NoSuchArticleException Diese wird geworfen falls kein <code>Article</code>
     *                                unter dieser articleNumber gefunden wurde.
     * @throws NonUniqueHibernateResultException
     *                                Diese wird geworfen falls mehr als ein <code>Article</code>
     *                                zur�ckgeliefert wird und das Ergebnis somit nicht eindeutig ist.
     * @see NoSuchArticleException
     * @see NonUniqueHibernateResultException
     * @see BaseDao#getArticleByArticleNumber(String)
     */
    public Article getArticleByArticleNumber(String articleNumber) throws DataAccessException, NoSuchArticleException, NonUniqueHibernateResultException {
        List articleList = getHibernateTemplate().find("from " + Article.class.getName() + " as article where article.articleNumber = ?", new Object[]{articleNumber});

        if (articleList.size() < 1)
            throw new NoSuchArticleException("Es wurde kein Article mit der Artikelnummer [articleNumber = " + articleNumber + "] gefunden.");
        if (articleList.size() > 1)
            throw new NonUniqueHibernateResultException("Es wurde kein eideutiger Article mit der Artikelnummer [articleNumber = " + articleNumber + "] gefunden. Bitte �berpr�fen Sie Ihren Datenbankbestand.");

        return (Article) articleList.get(0);
    }

    /**
     * Gibt eine <code>List</code> mit allen <code>DerliveryOrder</code> die in der Datenbank verf�gbar
     * sind zur�ck.
     *
     * @param id Die <code>DeliveryOrder</code> die in der Datenbank gespeichert oder ge�ndert werden
     *           soll.
     * @return Eine <code>List</code> mit allen <code>DerliveryOrder</code> in der Datenbank.
     * @throws DataAccessException Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                             auftritt.
     * @see org.ecobill.module.base.dao.BaseDao#getAllDeliveryOrderByBPID(Long)
     */
    public List getAllDeliveryOrderByBPID(Long id) throws DataAccessException {
        return getHibernateTemplate().find("select do.id from org.ecobill.module.base.domain.DeliveryOrder as do where do.businessPartner = ?", new Object[]{id});
    }


    /**
     * Gibt eine <code>List</code> mit allen <code>Bills</code> die in der Datenbank verf�gbar
     * sind zur�ck.
     *
     * @param id Die <code>Bills</code> die in der Datenbank gespeichert oder ge�ndert werden
     *           soll.
     * @return Eine <code>List</code> mit allen <code>Bills</code> in der Datenbank.
     * @throws DataAccessException Diese wird geworfen falls ein Fehler beim Datenzugriff
     *                             auftritt.
     * @see org.ecobill.module.base.dao.BaseDao#getAllBillsByBPID(Long)
     */
    public List getAllBillsByBPID(Long id) throws DataAccessException {
        return getHibernateTemplate().find("from org.ecobill.module.base.domain.Bill as bill where bill.businessPartner.id = ?", new Object[]{id});
    }
}
