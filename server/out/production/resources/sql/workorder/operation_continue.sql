declare
    v_work_order_id NUMBER;
    v_operation_number NUMBER;
    v_user_id NUMBER;
    v_result VARCHAR2(1);
begin
    v_work_order_id := :p_work_order_id;
    v_operation_number := :p_operation_number;
    v_user_id := :p_user_id;
    v_result := XXEAM.XXMOBILE_API_PKG.operation_continue(v_work_order_id, v_operation_number, v_user_id);
    :p_result := v_result;
end;