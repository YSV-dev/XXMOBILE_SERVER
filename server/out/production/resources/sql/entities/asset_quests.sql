select
    ASSET_QUEST_ID,
    INSTANCE_ID,
    QUEST_ID,
    ORGANIZATION_ID,

    TO_CHAR(LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as LAST_UPDATE_DATE,
    TO_CHAR(REMOVE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as REMOVE_DATE
from ( select *
from XXMOBILE_ASSET_QUESTS_V v
order by ASSET_QUEST_ID) v
where (rownum <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.ASSET_QUEST_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)
