package com.sn0w.suisei.api.core.exception.otp;

import com.sn0w.suisei.api.core.exception.DomainException;

public class CodeGenerationFail extends DomainException {

    private static final String CODE = "FAILED_GENERATE_CODE";

    public CodeGenerationFail() {
        super(CODE, "Failed to generate otp code");
    }
}
