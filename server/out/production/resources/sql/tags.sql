select 
    SERIAL_NUMBER,
    TAG_TYPE_ID,
    ORGANIZATION_ID,
    ASSET_ID,
    NODE_SEQ,--deprecated
    TAG_POSITION,
    
    TO_CHAR(CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as CREATION_DATE,
    CREATED_BY,
    TO_CHAR(LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as LAST_UPDATE_DATE,
    TO_CHAR(REMOVE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as REMOVE_DATE
from ( select *
from xxeam.xxmobile_tags_v v
order by SERIAL_NUMBER ) v
where (rownum <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.SERIAL_NUMBER > :p_last_id)

    and ((:p_sync_date is null and v.remove_date is null)
        or (v.remove_date > TO_DATE(:p_sync_date,'YYYY-MM-DD HH24:MI:SS')
            or  v.last_update_date > TO_DATE(:p_sync_date,'YYYY-MM-DD HH24:MI:SS')))
            
    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)