package org.ecobill.module.base.service;

import org.ecobill.module.base.dao.BaseDao;
import org.ecobill.module.base.dao.exception.NoSuchArticleException;
import org.ecobill.module.base.domain.*;
import org.ecobill.core.system.service.Service;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;

/**
 * Der <code>BaseService</code> erm�glicht es mit Hilfe von DataAccessObject komplexere Daten
 * von der Datenbank zu laden und auch wieder dorthin abzulegen.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 12:30:55
 *
 * @author Roman R&auml;dle
 * @version $Id: BaseService.java,v 1.17 2006/01/30 23:43:13 raedler Exp $
 * @since EcoBill 1.0
 */
public interface BaseService extends Service {

    /**
     * Gibt das <code>BaseDao</code>, das direkten Zugriff auf die Datenbank besitzt,
     * zur�ck.
     *
     * @return Das <code>BaseDao</code> mit direktem Zugriff auf die Datenbank.
     */
    public BaseDao getBaseDao();

    /**
     * Setzt das <code>BaseDao</code>, das einen direkten Zugriff zur Datenbank erm�glichen
     * soll.
     *
     * @param baseDao Das <code>BaseDao</code> mit direktem Zugriff auf die Datenbank.
     */
    public void setBaseDao(BaseDao baseDao);

    /**
     * @see BaseDao#load(Class, java.io.Serializable)
     */
    public Object load(Class clazz, Serializable id);

    /**
     * @see BaseDao#loadAll(Class)
     */
    public List loadAll(Class clazz);

    /**
     * @see BaseDao#evict(Object)
     */
    public void evict(Object entity);

    /**
     * @see BaseDao#saveOrUpdate(Object)
     */
    public void saveOrUpdate(Object entity);

    /**
     * @see BaseDao#delete(Object)
     */
    public void delete(Object entity);

    /**
     * L�dt das <code>Object</code> mit der angegebenen Id aus der Datenbank
     * um es danach aus dieser zu l�schen.
     *
     * @param clazz Die Klasse des <code>Object</code>.
     * @param id    Die Id des <code>Object</code>.
     */
    public void delete(Class clazz, Serializable id);

    /**
     * @see BaseDao#getMaximumByParam(Class, String)
     */
    public Long getMaximumByParam(Class clazz, String param);

    /**
     * @see BaseDao#getNumberSequenceByKey(String)
     */
    public NumberSequence getNumberSequenceByKey(String sequenceKey);

    /**
     * Gibt die n�chste Rechnungsnummer zur�ck.
     *
     * @return Die n�chste Rechnungsnummer.
     */
    public Long getNextBillNumber();

    /**
     * @see BaseDao#getSystemLocaleBySystemLocaleKey(String)
     */
    public SystemLocale getSystemLocaleBySystemLocaleKey(String systemLocaleKey);

    /**
     * Gibt die <code>SystemLocale</code>, die der <code>Locale</code> am �hnlichsten ist,
     * zur�ck.
     *
     * @param locale Eine <code>Locale</code> um die <code>SystemLocale</code> zu erhalten.
     * @return Die <code>SystemLocale</code> die der <code>Locale</code> am �hnlichsten ist.
     */
    public SystemLocale getSystemLocaleByLocale(Locale locale);

    /**
     * @see BaseDao#getSystemUnitsByCategory(String)
     */
    public List getSystemUnitsByCategory(String category);

    /**
     * @see org.ecobill.module.base.dao.BaseDao#getAllBusinessPartnerIds()
     */
    public List getAllBusinessPartnerIds();

    /**
     * @see BaseDao#getAllReduplicatedArticleByDOId(Long)
     */
    public List getAllReduplicatedArticleByDOId(Long id);

    /**
     * @see BaseDao#getArticleByArticleNumber(String)
     */
    public Article getArticleByArticleNumber(String articleNumber) throws NoSuchArticleException;

    /**
     * @see org.ecobill.module.base.dao.BaseDao#getAllDeliveryOrderByBPID(Long)
     */
    public List getAllDeliveryOrderByBPID(Long id);

    /**
     * @see org.ecobill.module.base.dao.BaseDao#getAllBillsByBPID(Long)
     */
    public List getAllBillsByBPID(Long id);
}
