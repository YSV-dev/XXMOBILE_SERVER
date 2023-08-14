select *
from (
    select
        DEFECT_ID,
        WORK_REQUEST_ID,
        WORK_REQUEST_NUMBER,
        ORGANIZATION_ID,
        INSTANCE_ID,
        INSTANCE_NUMBER,
        POSITION_NUMBER,
        DEFECT_GROUP,
        DEFECT_TYPE,
        DEFECT_DESCRIPTION,
        CAUSE_UPDATED_BY,
        CAUSE,
        CAUSE_DESCRIPTION,
        CREATED_BY,
        CREATION_DATE,
        LAST_UPDATED_BY,
        LAST_UPDATE_DATE,
        LAST_UPDATE_LOGIN
    from XXEAM.XXMOBILE_DEFECTS_V
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
