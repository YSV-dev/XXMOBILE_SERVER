select *
from (
    select
        WORK_ORDER_ID,
        ORGANIZATION_ID,
        OPERATION_NUMBER,
        DEPARTMENT_ID,
        INVENTORY_ITEM_ID,
        POSITION_CODE,
        REQUIRED_QUANTITY,
        RELEASED_QUANTITY,
        FACT_QUANTITY,
        UNITS,
        DESCRIPTION,
        CREATED_BY,
        CREATION_DATE,
        LAST_UPDATED_BY,
        LAST_UPDATE_DATE
    from XXEAM.XXMOBILE_WO_MATERIALS_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
