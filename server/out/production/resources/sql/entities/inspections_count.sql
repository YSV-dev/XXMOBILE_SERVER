select 'ALL_COUNT' as COUNT_TYPE, count(*) as COUNT_VALUE
from XXMOBILE_INSPECTIONS_V v
where v.REMOVED_DATE is null and v.ORGANIZATION_ID = :p_organization_id and v.EXPIRED_DATE is null

union all

select 'QUERY_COUNT' as COUNT_TYPE, count(*) as COUNT_VALUE
from XXMOBILE_INSPECTIONS_V v
where ((:p_last_id is null) or v.INSPECTION_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.REMOVED_DATE is null)
        or (v.REMOVED_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))
            
    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)
    
    and v.EXPIRED_DATE is null
