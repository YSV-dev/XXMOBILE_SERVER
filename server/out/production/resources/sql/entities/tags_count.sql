select 'ALL_COUNT' as COUNT_TYPE, count(*) as COUNT_VALUE
from XXEAM.XXMOBILE_TAGS_V v
where v.removed_date is null and v.ORGANIZATION_ID = :p_organization_id

union all

select 'QUERY_COUNT' as COUNT_TYPE, count(*) as COUNT_VALUE
from XXEAM.XXMOBILE_TAGS_V v
where ((:p_last_id is null) or v.SERIAL_NUMBER > :p_last_id)
    
    and ((:p_sync_date is null and v.removed_date is null)
        or (v.removed_date > :p_sync_date
            or  v.last_update_date > :p_sync_date))
    
    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)