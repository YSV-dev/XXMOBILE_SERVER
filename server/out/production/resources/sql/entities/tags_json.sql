select
    json_arrayagg(
        v.JSON_ROW
        order by NLSSORT(v.SERIAL_NUMBER, 'NLS_SORT=BINARY')
        returning CLOB
    ) as JSON
from (
select
    t.SERIAL_NUMBER,
    json_object(
        'serial_number' value t.SERIAL_NUMBER,
        'tag_type_id' value t.TAG_TYPE_ID,
        'organization_id' value t.ORGANIZATION_ID,
        'category' value t.CATEGORY,
        'app_id' value t.APP_ID,
        'created_at' value TO_CHAR(t.CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'created_by' value t.CREATED_BY,
        'updated_at' value TO_CHAR(t.LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'updated_by' value t.LAST_UPDATED_BY,
        'removed_at' value TO_CHAR(t.REMOVED_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
        'removed_by' value t.REMOVED_BY,
        'objects' value nvl(
            (select json_arrayagg(
                json_object(
                   'tag_object_number' value o.TAG_OBJECT_NUMBER,
                   'serial_number' value o.SERIAL_NUMBER,
                   'organization_id' value o.ORGANIZATION_ID,
                   'object_type' value o.OBJECT_TYPE,
                   'object_id' value o.OBJECT_ID,
                   'tag_position' value o.TAG_POSITION,
                   'created_at' value TO_CHAR(o.CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                   'created_by' value o.CREATED_BY,
                   'updated_at' value TO_CHAR(o.LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                   'updated_by' value o.LAST_UPDATED_BY,
                   'removed_at' value TO_CHAR(o.REMOVED_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"'),
                   'removed_by' value o.REMOVED_BY
                   null on null
                   returning CLOB
               )
               order by o.TAG_POSITION
               returning CLOB
            ) as JSON
            from XXEAM.XXMOBILE_TAG_OBJECTS o
            where t.SERIAL_NUMBER = o.SERIAL_NUMBER),
            '[]'
        ) format json
        null on null
        returning CLOB
    ) as JSON_ROW
from XXEAM.XXMOBILE_TAGS_V t
--left join  XXEAM.XXMOBILE_TAG_OBJECTS o on t.SERIAL_NUMBER = o.SERIAL_NUMBER
--where i.INSPECTION_ID >= 118764 --and l.POSITION < 500
where ((:p_last_id is null) or t.SERIAL_NUMBER > :p_last_id)
    
    and ((:p_sync_date is null and t.removed_date is null)
        or (t.removed_date > :p_sync_date
            or  t.last_update_date > :p_sync_date))

    and ((:p_organization_id is null) or t.ORGANIZATION_ID = :p_organization_id)
order by NLSSORT(t.SERIAL_NUMBER, 'NLS_SORT=BINARY')
fetch first nvl(:p_limit, 100) rows only

) v
