declare
    v_result NUMBER;
    v_inspection_line_id NUMBER;
begin
    v_result := xxeam.xxmobile_api_pkg.add_inspection_line(
        :p_created_by,
        :p_inspection_id,
        :p_position,
        :p_asset_status,
        :p_node_seq,
        :p_node,
        :p_quest_type,
        :p_service,
        :p_operation,
        :p_measure_type,
        :p_description,
        :p_default_result,
        :p_current_result,
        :p_current_value,
        :p_completion_date,
        :p_tag_serial_number,
        :p_measure_device_name,
        :p_measure_device_number,
        :p_measure_device_address,
        v_inspection_line_id
    );
    :p_result := v_result;
    :p_inspection_line_id := v_inspection_line_id;
end;