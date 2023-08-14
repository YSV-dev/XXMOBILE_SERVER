declare
    v_result NUMBER;
    v_inspection_id NUMBER;
    v_created_date DATE;
begin
    v_result := xxeam.xxmobile_api_pkg.add_inspection(
        :p_created_by,
        :p_organization_id,
        :p_instance_id,
        :p_instance_number,
        :p_position_number,
        :p_branch_name,
        :p_department_name,
        :p_service,
        :p_inspection_date,
        :p_completion_date,
        :p_asset_status,
        :p_quest_id,
        :p_quest_name,
        :p_brigade,
        :p_shift,
        :p_source,
        :p_app_id,
        v_inspection_id,
        v_created_date
    );
    :p_result := v_result;
    :p_inspection_id := v_inspection_id;
    :p_created_date := v_created_date;
end;