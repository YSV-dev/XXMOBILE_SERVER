select 
    SERIAL_NUMBER,
    TAG_TYPE_ID,
    ORGANIZATION_ID,
    ASSET_ID,
    NODE_SEQ,--deprecated
    TAG_POSITION,
    
    CREATION_DATE as CREATION_DATE,
    CREATED_BY,
    LAST_UPDATE_DATE as LAST_UPDATE_DATE,
    REMOVED_DATE as REMOVED_AT
from xxeam.xxmobile_tag_objects v
where SERIAL_NUMBER = :p_serial_number
order by TAG_TYPE_ID, OBJECT_ID