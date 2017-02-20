package com.sandata.lab.data.model.cache;

import com.sandata.lab.data.model.base.BaseObject;
import org.joda.time.LocalDateTime;

/**
 * Date: 8/24/15
 * Time: 9:03 AM
 */

public class Cached<T> extends BaseObject {

    private static final long serialVersionUID = 1L;

    private LocalDateTime cachedDateTime;
    private T data;

    public void cacheData(final T data) {

        this.cachedDateTime = LocalDateTime.now();
        this.data = data;
    }

    public LocalDateTime getCachedDateTime() {
        return cachedDateTime;
    }

    public T getData() {
        return data;
    }
}
