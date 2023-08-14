select
    'ALL_COUNT' as COUNT_TYPE,
    count(*) as COUNT_VALUE
from XXMOBILE_ASSETS_V v
where v.REMOVE_DATE is null
    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)

union all

select
    'QUERY_COUNT' as COUNT_TYPE,
    count(*) as COUNT_VALUE
from XXMOBILE_ASSETS_V v
where ((:p_last_id is null) or v.INSTANCE_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)
