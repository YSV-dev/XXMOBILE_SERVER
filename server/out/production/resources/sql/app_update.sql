declare
    v_date DATE;
begin
    v_date := SYSDATE;
    update xxeam.xxmobile_apps
    set
        VERSION = :p_version,
        DEV_MODE = :p_dev_mode,
        LAST_UPDATE_DATE = v_date,
        UPDATED_BY = -1
    where app_id = :app_id;
    :p_result = v_date;
end;
