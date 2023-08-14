select
    a.APP_ID,
    a.APP_NAME,

    a.VERSION,
    a.DEV_MODE,
    a.TOKEN,
    a.SOFTWARE_ID,
    a.IMEI,
    a.DEVICE_NAME,
    a.SYSTEM_NAME,
    a.SYSTEM_VERSION,
    a.DESCRIPTION,

    TO_CHAR(a.CREATION_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as CREATED_AT,
    a.CREATED_BY,
    TO_CHAR(a.LAST_UPDATE_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as UPDATED_AT,
    a.UPDATED_BY,
    TO_CHAR(a.REMOVED_DATE - 5/24, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') as REMOVED_AT
from xxeam.xxmobile_apps a
where a.remove_date is null
and a.APP_ID = :p_app_id