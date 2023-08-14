select *
from (
    select
        WORK_ORDER_ID,
        OPERATION_NUMBER,
        ORGANIZATION_ID,
        DEPARTMENT_ID,
        DESCRIPTION,
        FIRST_UNIT_START_DATE,
        FIRST_UNIT_COMPLETION_DATE,
        LAST_UNIT_START_DATE,
        LAST_UNIT_COMPLETION_DATE,
        SCHEDULED_START_DATE,
        SCHEDULED_END_DATE,
        ACTUAL_START_DATE,
        ACTUAL_END_DATE,
        UNITS,
        AMOUNT,
        AMOUNT_FACT,
        BASIS,
        NORM,
        REASON,
        REASON_DESCRIPTION,
        OVERTIME_FACTOR,
        INTERVAL_COMPLETED,
        EXECUTION_CONTROL_CODE,
        CREATED_BY,
        CREATION_DATE,
        LAST_UPDATED_BY,
        LAST_UPDATE_DATE
    from XXEAM.XXMOBILE_WO_OPEARATIONS_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
