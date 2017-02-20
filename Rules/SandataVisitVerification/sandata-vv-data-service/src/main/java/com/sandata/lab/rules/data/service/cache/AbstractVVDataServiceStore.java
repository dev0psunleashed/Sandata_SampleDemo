package com.sandata.lab.rules.data.service.cache;

/**
 * Created by thanhxle on 10/14/2016.
 */
public abstract class AbstractVVDataServiceStore {

    /**
     * stores call preference of a BE_ID in json format
     * with key is bussiness entity id
     * @param bsntIdKey String
     * @param jsonCallPreferences String
     */
    public abstract void storeData(String cachedKey ,String jsonFormatData);

    /**
     * get call preference from redis by BE_ID
     * @param bsntIdKey String
     */
    public abstract String getCachedData(String cachedKey);

}
