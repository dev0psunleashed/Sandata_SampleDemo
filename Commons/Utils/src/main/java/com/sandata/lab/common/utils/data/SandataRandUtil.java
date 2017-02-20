package com.sandata.lab.common.utils.data;

import java.util.concurrent.ThreadLocalRandom;

public class SandataRandUtil {

    public static long RandomLong() {
        return ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
    }
}
