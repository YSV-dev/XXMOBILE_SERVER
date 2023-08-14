select
    u.USER_ID,
    u.PERSON_ID,
    u.EMAIL_ADDRESS,
    u.USER_NAME,
    u.FULL_NAME,
    u.ENCRYPTED_PIN,
    u.COMPANY,
    u.AVATAR,

    'Y' as TRUNCATED_DATA,

    u.CREATION_DATE,
    u.CREATED_BY,
    u.LAST_UPDATE_DATE,
    u.LAST_UPDATED_BY,
    u.LAST_UPDATE_LOGIN,
    u.REMOVED_DATE
from XXMOBILE_USERS_V u
where u.REMOVED_DATE is null
    /*%CONDITIONS%*/
/*%SORTING%*/
fetch first :p_limit rows only
