select
    INSTANCE_ID,
    INSTANCE_NUMBER,
    INSTANCE_DESCRIPTION,
    
    INVENTORY_ITEM_ID, --DEPRECATED
    SERIAL_NUMBER,
    DEPARTMENT_CODE,
    DEPARTMENT_NAME,
    BRANCH_NAME,
    POSITION_NUMBER,
    SERVICE,
    ORGANIZATION_ID,
    ORGANIZATION_NAME,
    GROUP_ID,
    GROUP_NAME,
    CRITICALITY,
    CATEGORY,
    SERIAL_NUMBER,
    ASSET_NUMBER,

    TEMPLATES_COUNT,

    CREATION_DATE as CREATED_AT,
    CREATED_BY,
    LAST_UPDATE_DATE as UPDATED_AT,
    LAST_UPDATED_BY as UPDATED_BY,
    REMOVE_DATE as REMOVED_AT,
    REMOVED_BY
from ( select *
from XXMOBILE_ASSETS_V v
order by INSTANCE_ID) v
where (ROWNUM <= nvl(:p_limit, 100))
    and ((:p_last_id is null) or v.INSTANCE_ID > :p_last_id)
    
    and ((:p_sync_date is null and v.REMOVE_DATE is null)
        or (v.REMOVE_DATE > :p_sync_date
            or  v.LAST_UPDATE_DATE > :p_sync_date))

    and ((:p_organization_id is null) or v.ORGANIZATION_ID = :p_organization_id)

    /*and ((p_instance_id is null) or v.INSTANCE_ID = p_instance_id)
    and ((p_instance_number is null) or v.instance_number like '%'||p_instance_number||'%')
    and ((p_position_number is null) or v.instance_number like '%'||p_position_number||'%')
    and ((p_templates_exists is null) or (p_templates_exists = 'N') or ((p_templates_exists = 'Y') and v.templates_count > 0))*/
    
    --and templates_count > 0