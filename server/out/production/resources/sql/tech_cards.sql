SELECT 
  organization_id, 
  segment1 segment, 
  activity_association_id, 
  asset_activity_id, 
  instance_id, 
  serial_number, 
  inventory_item_id, 
  last_vld_organization_id, 
  attribute9,
  instance_number,
  tp
FROM XXMOBILE_ACTIVITY_ASSOCIATION_V t
WHERE instance_id = :p_instance_id