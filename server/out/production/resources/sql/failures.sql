select *
from (
    select
        FAILURE_ID,
        FAILURE_NUMBER,
        ORGANIZATION_ID,
        WARRANTY_ID,
        STILL_ID,
        INSTANCE_ID,
        INSTANCE_NUMBER,
        POSITION_NUMBER,
        STATUS_ID,
        STATUS_NAME,
        STILL_START,
        STILL_END,
        STILL_TIME,
        STILL_LOSSES,
        INSTANCE_NODE,
        SERVICE,
        FAILURE_CODE,
        CAUSE_CODE,
        RESOLUTION_CODE,
        FAILURE_COMMENTS,
        FAILURE_DESCRIPTION,
        CAUSE_DESCRIPTION,
        RESOLUTION_DESCRIPTION,
        FAILURE_TYPE,
        REASON,
        ROOT_REASON,
        ACT_NUMBER,
        DESCRIPTION,
        REASON_DESCRIPTION,
        ROOT_DESCRIPTION,
        FAILURE_NOTE,
        WO_ACTIVITY,
        CREATED_BY_NAME
    from xxeam.XXMOBILE_FAILURES_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
