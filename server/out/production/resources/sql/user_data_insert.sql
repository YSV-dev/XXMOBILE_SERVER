insert into xxeam.xxmobile_user_data (
    USER_ID,
    DATA_NAME,
    KEY_NAME,
    VALUE,
    LAST_UPDATE_DATE,
    LAST_UPDATED_BY
) values (
    :p_user_id,
    :p_data_name,
    :p_key_name,
    :p_value,
    :p_last_update_date,
    :p_last_updated_by
)
