select *
from (
    select
        WORK_REQUEST_ID,
        WORK_REQUEST_NUMBER,
        ORGANIZATION_ID,
        INSTANCE_ID,
        INSTANCE_NUMBER,
        POSITION_NUMBER,
        STATUS_ID,
        STATUS_NAME,
        WORK_REQUEST_TYPE_ID,
        WORK_REQUEST_TYPE,
        DETECTION_DATE,
        REMARK_TYPE,
        REMARK_OPERATION,
        PRIORITY_NAME,
        EXECUTED_WORK_ID,
        MEASURE,
        CURRENT_VALUE,
        ASSET_STATUS,
        NODE,
        SERVICE,
        PERFORMED_USER,
        DESCRIPTION,
        RESOLVED_DATE,
        WORK_ORDER_ID,
        WORK_ORDER_NUMBER,
        EXECUTED_USER,
        EXECUTED_ORGANIZATION,
        EXECUTED_DESCRIPTION
    from XXEAM.XXMOBILE_WORK_REQUESTS_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
