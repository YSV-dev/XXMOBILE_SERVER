select *
from (
    select
        WORK_ORDER_ID,
        ORGANIZATION_ID,
        OPERATION_NUMBER,
        RESOURCE_NUMBER,
        RESOURCE_ID,

        PERSON_ID,
        EMPLOYEE_NUMBER,
        EMPLOYEE_FULL_NAME,
        EMPLOYEE_JOB_NAME,
        EMPLOYEE_DEPARTMENT,

        DEPARTMENT_ID,
        DESCRIPTION,
        RESOURCE_CODE,
        UOM_CODE,
        SCHEDULED_UNITS,
        START_DATE,
        COMPLETION_DATE,
        CREATED_BY,
        CREATION_DATE,
        LAST_UPDATED_BY,
        LAST_UPDATE_DATE
    from XXEAM.XXMOBILE_WO_RESOURCES_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
