select *
from (
    select
        u.USER_ID,
        u.PERSON_ID,
        u.EMAIL_ADDRESS,
        u.USER_NAME,
        u.FULL_NAME,
        u.ENCRYPTED_PIN,
        u.AVATAR,

        u.SUPER_USER,

        u.CREATION_DATE,
        u.CREATED_BY,
        u.LAST_UPDATE_DATE,
        u.LAST_UPDATED_BY,
        u.LAST_UPDATE_LOGIN,
        u.REMOVED_DATE
    from xxeam.xxmobile_users_v u
    where u.REMOVED_DATE is null
        /*%CONDITIONS%*/
    /*%SORTING%*/
) where rownum <= :p_limit
