package org.ecobill.module.base.service.impl;

import org.ecobill.module.base.service.BaseService;
import org.ecobill.module.base.dao.BaseDao;
import org.ecobill.module.base.dao.exception.NoSuchSystemLocaleException;
import org.ecobill.module.base.dao.exception.NoSuchArticleException;
import org.ecobill.module.base.domain.*;
import org.ecobill.util.LocalizerUtils;
import org.ecobill.util.exception.LocalizerException;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;

/**
 * Das <code>BaseServiceImpl</code> ist eine Implementation des Interfaces <code>BaseService</code>.
 * <p/>
 * User: rro
 * Date: 15.07.2005
 * Time: 12:31:05
 *
 * @author Roman R&auml;dle
 * @version $Id: BaseServiceImpl.java,v 1.19 2006/01/30 23:43:13 raedler Exp $
 * @see BaseService
 * @since EcoBill 1.0
 */
public class BaseServiceImpl implements BaseService {

    /**
     * Das <code>BaseDao</code> erm�glicht den Zugriff auf die "einfachen/flachen" Objekte die
     * in der Datenbank abgelegt sind.
     */
    private BaseDao baseDao;

    /**
     * Gibt das <code>BaseDao</code>, das direkten Zugriff auf die Datenbank besitzt,
     * zur�ck.
     *
     * @return Das <code>BaseDao</code> mit direktem Zugriff auf die Datenbank.
     * @see org.ecobill.module.base.service.BaseService#getBaseDao()
     */
    public BaseDao getBaseDao() {
        return baseDao;
    }

    /**
     * Setzt das <code>BaseDao</code>, das einen direkten Zugriff zur Datenbank erm�glichen
     * soll.
     *
     * @param baseDao Das <code>BaseDao</code> mit direktem Zugriff auf die Datenbank.
     * @see BaseService#setBaseDao(org.ecobill.module.base.dao.BaseDao)
     */
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * @see BaseService#load(Class, java.io.Serializable)
     */
    public Object load(Class clazz, Serializable id) {
        return baseDao.load(clazz, id);
    }

    /**
     * @see BaseService#loadAll(Class)
     */
    public List loadAll(Class clazz) {
        return baseDao.loadAll(clazz);
    }

    /**
     * @see BaseService#evict(Object)
     */
    public void evict(Object entity) {
        baseDao.evict(entity);
    }

    /**
     * @see BaseService#saveOrUpdate(Object)
     */
    public void saveOrUpdate(Object entity) {
        baseDao.saveOrUpdate(entity);
    }

    /**
     * @see BaseService#delete(Object)
     */
    public void delete(Object entity) {
        baseDao.delete(entity);
    }

    /**
     * L�dt das <code>Object</code> mit der angegebenen Id aus der Datenbank
     * um es danach aus dieser zu l�schen.
     *
     * @param clazz Die Klasse des <code>Object</code>.
     * @param id    Die Id des <code>Object</code>.
     * @see BaseService#delete(Class, java.io.Serializable)
     */
    public void delete(Class clazz, Serializable id) {

        // L�dt das <code>Object</code>.
        Object entity = baseDao.load(clazz, id);

        // L�scht das <code>Object</code> aus der Datenbank.
        baseDao.delete(entity);
    }

    /**
     * @see BaseService#getMaximumByParam(Class, String)
     */
    public Long getMaximumByParam(Class clazz, String param) {
        return baseDao.getMaximumByParam(clazz, param);
    }

    /**
     * @see BaseService#getNumberSequenceByKey(String)
     */
    public NumberSequence getNumberSequenceByKey(String sequenceKey) {
        return baseDao.getNumberSequenceByKey(sequenceKey);
    }

    /**
     * Gibt die n�chste Rechnungsnummer zur�ck.
     *
     * @return Die n�chste Rechnungsnummer.
     * @see org.ecobill.module.base.service.BaseService#getNextBillNumber()
     */
    public Long getNextBillNumber() {
        Long max = baseDao.getMaximumByParam(Bill.class, "billNumber");

        return max + 1L;
    }

    /**
     * @see BaseService#getSystemLocaleBySystemLocaleKey(String)
     */
    public SystemLocale getSystemLocaleBySystemLocaleKey(String systemLocaleKey) {
        return baseDao.getSystemLocaleBySystemLocaleKey(systemLocaleKey);
    }

    /**
     * Gibt die <code>SystemLocale</code>, die der <code>Locale</code> am �hnlichsten ist,
     * zur�ck.
     *
     * @param locale Eine <code>Locale</code> um die <code>SystemLocale</code> zu erhalten.
     * @return Die <code>SystemLocale</code> die der <code>Locale</code> am �hnlichsten ist.
     * @see BaseService#getSystemLocaleByLocale(java.util.Locale)
     */
    public SystemLocale getSystemLocaleByLocale(Locale locale) throws NoSuchSystemLocaleException {

        List systemLocaleList = baseDao.loadAll(SystemLocale.class);

        /*
         * Es wird versucht aus der <code>List</code> mit <code>SystemLocale</code> die <code>SystemLocale</code>
         * herauszufiltern, die der <code>Locale</code> am �hnlichsten ist.
         * -> �hnlich bedeutet, dass die Priorit�t in <code>LocalizerUtils</code> gegen 1 gehen muss.
         */
        Object o;
        try {
            o = LocalizerUtils.getLocalizedObject(systemLocaleList, locale);
        }
        catch (LocalizerException e) {
            throw new NoSuchSystemLocaleException("Es wurde keine SystemLocale gefunden die der Locale [language = " + locale.getLanguage() + " | country = " + locale.getCountry() + " | variant = " + locale.getVariant() + "] �hnelt.");
        }

        /*
         * Erneuter JasperDataSource, um sicher zu gehen, dass das zur�ckgelieferte <code>Object</code> auch wirklich
         * eine <code>SystemLocale</code> ist. Falls es sich nicht um eine <code>SystemLocale</code>
         * gehandelt hat wird abschlie�end eine erneute <code>NoSuchSystemLocaleException</code> geworfen.
         */
        SystemLocale systemLocale = null;
        if (o instanceof SystemLocale) {
            systemLocale = (SystemLocale) o;
        }

        if (systemLocale == null) {
            throw new NoSuchSystemLocaleException("Es wurde keine SystemLocale gefunden die der Locale [language = " + locale.getLanguage() + " | country = " + locale.getCountry() + " | variant = " + locale.getVariant() + "] �hnelt.");
        }

        return systemLocale;
    }

    /**
     * @see org.ecobill.module.base.service.BaseService#getAllBusinessPartnerIds()
     */
    public List getAllBusinessPartnerIds() {
        return baseDao.getAllBusinessPartnerIds();
    }

    /**
     * @see BaseService#getSystemUnitsByCategory(String)
     */
    public List getSystemUnitsByCategory(String category) {
        return baseDao.getSystemUnitsByCategory(category);
    }

    /**
     * @see BaseService#getAllReduplicatedArticleByDOId(Long)
     */
    public List getAllReduplicatedArticleByDOId(Long id) {
        return baseDao.getAllReduplicatedArticleByDOId(id);
    }

    /**
     * @see BaseService#getArticleByArticleNumber(String)
     */
    public Article getArticleByArticleNumber(String articleNumber) throws NoSuchArticleException {
        return baseDao.getArticleByArticleNumber(articleNumber);
    }

    /**
     * @see org.ecobill.module.base.dao.BaseDao#getAllDeliveryOrderByBPID(Long)
     */
    public List getAllDeliveryOrderByBPID(Long id) {
        return baseDao.getAllDeliveryOrderByBPID(id);
    }

    /**
     * @see org.ecobill.module.base.dao.BaseDao#getAllBillsByBPID(Long)
     */
    public List getAllBillsByBPID(Long id) {
        return baseDao.getAllBillsByBPID(id);
    }
}
