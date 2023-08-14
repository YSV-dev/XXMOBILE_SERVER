package com.uralkali.server.data;

import org.hibernate.dialect.Oracle10gDialect;

/**
 * Касмомный диалект, проверяющий только используемые последовательности.
 * @author brzsmg
 */
public class CustomOracle10gDialect extends Oracle10gDialect {
    
    @Override
    public String getQuerySequencesString() {
        //"PARTITION_COUNT, SESSION_FLAG, KEEP_VALUE\n"+
        //return "select SEQUENCE_OWNER, SEQUENCE_NAME, GREATEST(MIN_VALUE, -9223372036854775807) as MIN_VALUE,\n"+
        //        "LEAST(MAX_VALUE, 9223372036854775808) as MAX_VALUE, INCREMENT_BY, CYCLE_FLAG, ORDER_FLAG, CACHE_SIZE,\n"+
        //        "LEAST(GREATEST(LAST_NUMBER, -9223372036854775807), 9223372036854775808) as LAST_NUMBER\n"+
        //        "from all_sequences where SEQUENCE_NAME in ('XXMOBILE_APP_ID_S')";
        return "select * from all_sequences where SEQUENCE_NAME in ('XXMOBILE_APP_ID_S')";
    }

}
