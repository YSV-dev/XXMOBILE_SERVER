select
    OPERATION_ID,
    QUEST_ID,
    POSITION,
    ORGANIZATION_ID,
    ASSET_STATUS as ASSET_STATUS,
    0 as NODE_SEQ,    --DEPRECATED
    MEASURE_POINT,
    TAG_POSITION,
    NODE,
    QUEST_TYPE,
    SERVICE,
    ANSWER_TYPE_ID,
    MEASURE_UNITS_ID, --DEPRECATED: 2023-06-27
    MEASURE_TYPE,     --DEPRECATED: 2023-06-27
    MEASURE_METHOD,
    MEASURE_UNITS,
    DESCRIPTION,
    DEFAULT_RESULT,
    MIN_VALUE as MIN_VALUE,
    MAX_VALUE as MAX_VALUE,
    TO_CHAR(LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as LAST_UPDATE_DATE,
    TO_CHAR(REMOVE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as REMOVE_DATE
from ( select *
from XXMOBILE_QUEST_OPERATIONS_V v
order by OPERATION_ID ) v
where (rownum <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.OPERATION_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.remove_date is null)
        or (v.remove_date > :p_sync_date
            or  v.last_update_date > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)
