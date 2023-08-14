select *
from (
    select
        i.INSPECTION_ID,
        i.INSTANCE_ID,
        i.QUEST_NAME,
        i.ASSET_NUMBER,
        i.POSITION_NUMBER,
        i.ASSET_STATUS,
        i.BRANCH_NAME,
        i.BRIGADE,
        i.SHIFT,
        i.SOURCE,
        i.INSPECTION_DATE,
        i.COMPLETION_DATE,
        i.CREATION_DATE,
        i.CREATED_BY,
        u.DESCRIPTION as CREATED_BY_FULL_NAME,
        (select count(*) from XXEAM.XXMOBILE_INSPECTION_LINES l where l.inspection_id = i.inspection_id and l.DEFAULT_RESULT != l.CURRENT_RESULT) as FAILED_COUNT
    from XXEAM.XXMOBILE_INSPECTIONS i
    left join apps.fnd_user u on u.user_id = i.CREATED_BY
    where INSTANCE_ID = nvl(:p_instance_id, INSTANCE_ID)
    order by INSPECTION_ID desc
) where rownum <= 20

