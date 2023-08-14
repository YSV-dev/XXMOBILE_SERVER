select
    QUEST_ID,
    ORGANIZATION_ID,
    QUEST_NAME,

    CREATION_DATE as CREATED_AT,
    CREATED_BY,
    LAST_UPDATE_DATE as UPDATED_AT,
    LAST_UPDATED_BY as UPDATED_BY,
    REMOVE_DATE as REMOVED_AT,
    REMOVED_BY
from ( select *
from XXMOBILE_QUESTS_V v
where 1=1
    and ((:p_last_id is null) or v.QUEST_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)
order by QUEST_ID) vv
where (rownum <= coalesce(:p_limit, 100))
