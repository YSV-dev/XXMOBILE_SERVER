declare
    v_work_order_id NUMBER;
    v_user_id NUMBER;
    v_execution_control NUMBER;
    v_result VARCHAR2(1);
begin
    v_work_order_id := :p_work_order_id;
    v_user_id := :p_user_id;
    v_execution_control := :p_execution_control;
    v_result := XXEAM.XXMOBILE_API_PKG.execution_control(v_work_order_id, v_user_id, v_execution_control);
    :p_result := v_result;
end;