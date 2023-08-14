select
    INSTANCE_ID,
    INSTANCE_NUMBER,
    INSTANCE_DESCRIPTION,

    SERIAL_NUMBER,
    DEPARTMENT_CODE,
    DEPARTMENT_NAME,
    BRANCH_NAME,
    POSITION_NUMBER,
    ORGANIZATION_ID,
    ORGANIZATION_NAME,
    GROUP_ID,
    GROUP_NAME,
    CRITICALITY,
    CATEGORY,
    SERVICE,
    SERIAL_NUMBER,
    ASSET_NUMBER,

    TEMPLATES_COUNT,

    --null as inspections_count,
    ---1 as work_orders_count,
    (select count(*) from xxeam.XXMOBILE_INSPECTIONS            i where i.INSTANCE_ID = a.INSTANCE_ID) as inspections_count,
    (select count(*) from xxeam.XXMOBILE_WORK_ORDERS_V          o where o.INSTANCE_ID = a.INSTANCE_ID) as work_orders_count,
    (select count(*) from xxeam.XXMOBILE_WORK_REQUESTS_V        r where r.INSTANCE_ID = a.INSTANCE_ID) as work_requests_count,
    (select count(*) from xxeam.XXMOBILE_FAILURES_V             f where f.INSTANCE_ID = a.INSTANCE_ID) as failures_count,
    (select count(*) from xxeam.XXMOBILE_ACTIVITY_ASSOCIATION_V aa where aa.INSTANCE_ID = a.INSTANCE_ID) as tech_cards_count,

    (select count(*) from xxeam.XXMOBILE_WORK_ORDERS_V   o2 where o2.INSTANCE_ID = a.INSTANCE_ID and o2.EXECUTION_STATUS in (1)) as work_orders_new_count,
    (select count(*) from xxeam.XXMOBILE_WORK_REQUESTS_V r2 where r2.INSTANCE_ID = a.INSTANCE_ID and r2.STATUS_ID in (1)) as work_requests_new_count,
    (select count(*) from xxeam.XXMOBILE_FAILURES_V      f2 where f2.INSTANCE_ID = a.INSTANCE_ID and f2.STATUS_ID in (1)) as failures_new_count,
    
    
    LAST_UPDATE_DATE,
    REMOVE_DATE as REMOVED_DATE

from XXEAM.xxmobile_assets_v a
where a.instance_id = :p_instance_id