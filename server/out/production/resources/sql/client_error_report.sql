declare
    v_result VARCHAR2(500);
begin
    v_result := xxeam.xxmobile_api_pkg.client_error_report(
        :p_occurrence_date,
        :p_error_type,
        :p_description,
        :p_app_id,
        :p_app_version,
        :p_organization_id,
        :p_user_id,
        :p_language,
        :p_exception_class,
        :p_exception_message,
        :p_stack_trace
    );
    :p_result := v_result;
end;