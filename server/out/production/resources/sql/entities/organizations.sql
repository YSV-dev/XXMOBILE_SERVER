select
    ORGANIZATION_ID,
    CODE,
    ALIAS,
    NAME,
    ORG_TYPE,
    TO_CHAR(LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as LAST_UPDATE_DATE,
    TO_CHAR(REMOVE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as REMOVE_DATE
from XXMOBILE_ORGANIZATIONS_V v
where (rownum <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.ORGANIZATION_ID > :p_last_id)
    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))

