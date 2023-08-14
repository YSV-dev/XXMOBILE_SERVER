select
    DEPARTMENT_ID,
    ORGANIZATION_ID,
    DEPARTMENT_CODE,
    DESCRIPTION,

    CREATION_DATE as CREATED_AT,
    CREATED_BY,
    LAST_UPDATE_DATE as UPDATED_AT,
    LAST_UPDATED_BY as UPDATED_BY,
    REMOVE_DATE as REMOVED_AT,
    REMOVED_BY
from XXMOBILE_DEPARTMENTS_V v
where (rownum <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.DEPARTMENT_ID > :p_last_id)

    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.last_update_date > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)