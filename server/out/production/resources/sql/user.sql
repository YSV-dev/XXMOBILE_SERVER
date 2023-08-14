select
    u.USER_ID,
    u.PERSON_ID,
    u.EMAIL_ADDRESS,
    u.USER_NAME,
    u.FULL_NAME,
    u.ENCRYPTED_PIN,
    u.COMPANY,
    u.AVATAR,
    null as PHOTO, --u.PHOTO,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID = 20419) as DEVELOPER,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID in (51540, 23119)) as SUPER_USER,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID in (53024)) as RELIABILITY_USER,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID in (52844, 23118, 53024, 51804)) as TECHNOLOGIST,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID = 51443) as CUSTOMER,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID in (51440, 51441, 51442)) as REPAIRER,

    (select DECODE(count(*), 0, 'N', 'Y')
    from XXMOBILE_USER_RESP_V ug
    where ug.USER_ID = u.USER_ID
    and ug.RESPONSIBILITY_ID in (53924)) as SUBCONTRACTOR,

    'N' as TRUNCATED_DATA,

    u.CREATION_DATE,
    u.CREATED_BY,
    u.LAST_UPDATE_DATE,
    u.LAST_UPDATED_BY,
    u.LAST_UPDATE_LOGIN,
    u.REMOVED_DATE
from xxmobile_users_v u
where u.REMOVED_DATE is null
    and u.USER_ID = :p_user_id
