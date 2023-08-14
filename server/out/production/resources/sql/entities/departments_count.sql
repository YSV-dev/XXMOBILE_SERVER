select
    'ALL_COUNT' as COUNT_TYPE,
    count(*) as COUNT_VALUE
from XXMOBILE_DEPARTMENTS_V v
where v.REMOVE_DATE is null
    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)

union all

select
    'QUERY_COUNT' as COUNT_TYPE,
    count(*) as COUNT_VALUE
from XXMOBILE_DEPARTMENTS_V v
where ((:p_last_id is null) or v.DEPARTMENT_ID > :p_last_id)

    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.last_update_date > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)