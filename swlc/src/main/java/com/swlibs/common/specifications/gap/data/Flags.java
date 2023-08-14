package com.swlibs.common.specifications.gap.data;

import com.swlibs.common.specifications.gap.FlagsMode;

public class Flags {
    byte mFlags;

    public Flags(byte value) {
        this.mFlags = value;
    }

    public boolean isLimited() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_LIMITED) == FlagsMode.GAP_ADTYPE_FLAGS_LIMITED;
    }

    public boolean isGeneral() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_GENERAL) == FlagsMode.GAP_ADTYPE_FLAGS_GENERAL;
    }

    public boolean isBredrNotSupported() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_BREDR_NOT_SUPPORTED) == FlagsMode.GAP_ADTYPE_FLAGS_BREDR_NOT_SUPPORTED;
    }

    public boolean isBrerdCapableCtrl() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_BRERD_CAPABLE_CTRL) == FlagsMode.GAP_ADTYPE_FLAGS_BRERD_CAPABLE_CTRL;
    }

    public boolean isBrerdCapableHost() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_BRERD_CAPABLE_HOST) == FlagsMode.GAP_ADTYPE_FLAGS_BRERD_CAPABLE_HOST;
    }

    public boolean isReserved1() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_RESERVED1) == FlagsMode.GAP_ADTYPE_FLAGS_RESERVED1;
    }

    public boolean isReserved2() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_RESERVED2) == FlagsMode.GAP_ADTYPE_FLAGS_RESERVED2;
    }

    public boolean isReserved3() {
        return (mFlags & FlagsMode.GAP_ADTYPE_FLAGS_RESERVED3) == FlagsMode.GAP_ADTYPE_FLAGS_RESERVED3;
    }

    @Override
    public /*@NonNull*/ String toString() {
        if(mFlags == 0) {
            return "N/A";
        }
        StringBuilder result = new StringBuilder();
        if(isLimited()) {
            result.append("Limited, ");
        }
        if(isGeneral()) {
            result.append("General, ");
        }
        if(isBredrNotSupported()) {
            result.append("BredrNotSupported, ");
        }
        if(isBrerdCapableCtrl()) {
            result.append("BrerdCapableCtrl, ");
        }
        if(isBrerdCapableHost()) {
            result.append("BrerdCapableHost, ");
        }
        if(isReserved1()) {
            result.append("Reserved1, ");
        }
        if(isReserved2()) {
            result.append("Reserved2, ");
        }
        if(isReserved3()) {
            result.append("Reserved3, ");
        }
        return result.toString().trim();
    }
}
