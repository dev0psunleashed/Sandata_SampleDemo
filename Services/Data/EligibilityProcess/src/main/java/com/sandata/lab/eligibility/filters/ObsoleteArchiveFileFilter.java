package com.sandata.lab.eligibility.filters;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class ObsoleteArchiveFileFilter<T> implements GenericFileFilter<T> {

    /* Retention duration in days obsolete*/
    private long retentionDuration;

    @Override
    public boolean accept(GenericFile<T> file) {
        if (file.isDirectory()) {
            return true;
        }

        return System.currentTimeMillis() - file.getLastModified() >= daysToMiliseconds(getRetentionDuration());
    }

    public long getRetentionDuration() {
        return retentionDuration;
    }

    public void setRetentionDuration(long retentionDuration) {
        this.retentionDuration = retentionDuration;
    }

    private Long daysToMiliseconds(long days) {
        return days * 24 * 60 * 60 * 1000;
    }
}
