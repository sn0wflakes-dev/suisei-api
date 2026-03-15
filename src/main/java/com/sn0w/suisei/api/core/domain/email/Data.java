package com.sn0w.suisei.api.core.domain.email;

import java.util.Map;

public class Data {
    private final Map<String, Object> data;

    private Data(Map<String, Object> data) {
        this.data = data;
    }

    public static Data of(Map<String, Object> data) {
        return new Data(data);
    }

    public Map<String, Object> getData() {
        return data;
    }
}
