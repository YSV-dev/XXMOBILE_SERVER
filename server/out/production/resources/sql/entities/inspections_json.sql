select
    json_arrayagg(
        v.JSON_ROW
        order by v.INSPECTION_ID
        returning CLOB
    ) as JSON
from (
select
    i.INSPECTION_ID,
    json_object(
        'inspection_id' value i.INSPECTION_ID,
        'instance_id' value i.INSTANCE_ID,
        'organization_id' value i.ORGANIZATION_ID,
        'asset_number' value i.ASSET_NUMBER,
        'position_number' value i.POSITION_NUMBER,
        'department_name' value i.DEPARTMENT_NAME,
        'branch_name' value i.BRANCH_NAME,
        'inspection_date' value TO_CHAR(i.INSPECTION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'completion_date' value TO_CHAR(i.COMPLETION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'asset_status' value i.ASSET_STATUS,
        'quest_id' value i.QUEST_ID,
        'quest_name' value i.QUEST_NAME,
        'collection_id' value i.COLLECTION_ID,
        'expired_date' value TO_CHAR(i.EXPIRED_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'brigade' value i.BRIGADE,
        'shift' value i.SHIFT,
        'app_id' value i.APP_ID,
        'created_at' value TO_CHAR(i.CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'created_by' value i.CREATED_BY,
        'updated_at' value TO_CHAR(i.LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'updated_by' value i.LAST_UPDATED_BY,
        'removed_at' value TO_CHAR(i.REMOVED_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'removed_by' value i.REMOVED_BY,
        'lines' value nvl(
            (select json_arrayagg(
                json_object(
                    'inspection_line_id' value l.INSPECTION_LINE_ID,
                    'inspection_id' value l.INSPECTION_ID,
                    'position' value l.POSITION,
                    'node' value l.NODE,
                    'quest_type' value l.QUEST_TYPE,
                    'service' value l.SERVICE,
                    'operation' value l.OPERATION,
                    'measure_type' value l.MEASURE_TYPE,
                    'description' value l.DESCRIPTION,
                    'default_result' value l.DEFAULT_RESULT,
                    'current_result' value l.CURRENT_RESULT,
                    'current_value' value l.CURRENT_VALUE,
                    'completion_date' value TO_CHAR(l.COMPLETION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                    'created_at' value TO_CHAR(l.CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                    'created_by' value l.CREATED_BY,
                    'updated_at' value TO_CHAR(l.LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                    'updated_by' value l.LAST_UPDATED_BY,
                    'removed_at' value TO_CHAR(l.REMOVE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
                    --'removed_by' value l.REMOVED_BY
                    null on null
                    returning CLOB
                )
                order by l.POSITION
                returning CLOB
            ) as JSON
            from XXMOBILE_INSPECTION_LINES l
            where i.INSPECTION_ID = l.INSPECTION_ID),
            '[]'
        ) format json
        null on null
        returning CLOB
    ) as JSON_ROW
from XXMOBILE_INSPECTIONS_V i
where ((:p_last_id is null) or i.INSPECTION_ID > :p_last_id)
    
    and ((:p_sync_date is null and i.REMOVED_DATE is null)
        or (i.REMOVED_DATE > :p_sync_date
            or  i.LAST_UPDATE_DATE > :p_sync_date))

    and ((:p_organization_id is null) or i.ORGANIZATION_ID = :p_organization_id)
    
    --and l.INSPECTION_ID is not null --Осмотры без строк не считаються
    and i.EXPIRED_DATE is null
order by i.INSPECTION_ID
fetch first nvl(:p_limit, 100) rows only

) v
