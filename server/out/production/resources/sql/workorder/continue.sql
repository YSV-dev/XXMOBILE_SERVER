declare
    v_work_order_id NUMBER;
    v_user_id NUMBER;
    v_result VARCHAR2(1);
begin
    v_work_order_id := :p_work_order_id;
    v_user_id := :p_user_id;
    v_result := XXEAM.XXMOBILE_API_PKG.work_order_continue(v_work_order_id, v_user_id);
    :p_result := v_result;
end;