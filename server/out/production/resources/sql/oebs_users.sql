select *
from (
    select
        u.USER_ID,
        u.PERSON_ID,
        u.EMAIL_ADDRESS,
        u.USER_NAME,
        u.FULL_NAME,
        u.AVATAR,

        u.CREATION_DATE,
        u.CREATED_BY,
        u.LAST_UPDATE_DATE,
        u.LAST_UPDATED_BY,
        u.LAST_UPDATE_LOGIN
    from xxeam.xxmobile_oebs_users_v u
    where 1=1
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
