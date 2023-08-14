select
    d.USER_ID,
    d.DATA_NAME,
    d.KEY_NAME,
    d.VALUE
from xxeam.xxmobile_user_data d
where d.REMOVED_DATE is null
and d.USER_ID = :p_user_id
and d.DATA_NAME = :p_data_name
