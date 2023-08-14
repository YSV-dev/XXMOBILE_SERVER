declare
    v_person_id NUMBER;
    v_result VARCHAR2(50);
begin
    v_person_id := :p_person_id;
    v_result := XXEAM.XXMOBILE_API_PKG.reset_person_pin(v_person_id);
    :p_result := v_result;
end;