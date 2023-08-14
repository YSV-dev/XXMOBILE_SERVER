declare
    v_attachment_id NUMBER;
begin
    v_attachment_id := xxeam.xxmobile_api_pkg.add_attachment(
        :p_inspection_id,
        :p_inspection_line_id,
        :p_position,
        :p_file_name,
        :p_file_path,
        :p_thumbnail_path,
        :p_url,
        :p_created_by
    );
    :p_result := v_attachment_id;
end;