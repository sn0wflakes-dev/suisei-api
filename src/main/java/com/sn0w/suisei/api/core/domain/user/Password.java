package com.sn0w.suisei.api.core.domain.user;

public sealed interface Password permits HashedPassword, RawPassword {
    String getValue();
}
