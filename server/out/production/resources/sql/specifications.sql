SELECT component_sequence_id,
       component_item_id,
       component_item,
       description,
       component_quantity,
       component_yield,
       uom,
       segment1
FROM   (SELECT  bic.component_sequence_id,
                bic.component_item_id,
                bic.SUGGESTED_VENDOR_NAME,
                bic.vendor_id,
                bic.unit_price,
                msiv.concatenated_segments component_item,
                msiv.description,
                bic.component_quantity,
                bic.component_yield_factor component_yield,
                msiv.primary_uom_code uom,
                bic.wip_supply_type,
                lu.meaning wip_supply_type_disp,
                bbm.assembly_item_id,
                bic.from_end_item_unit_number,
                bic.to_end_item_unit_number,
                msi.segment1
                FROM    apps.bom_inventory_components bic,
                        apps.mtl_system_items_vl      msiv,
                        apps.mtl_system_items_vl      msi,
                        apps.mfg_lookups              lu,
                        apps.bom_bill_of_materials    bbm
                WHERE   bic.bill_sequence_id = bbm.common_bill_sequence_id
                    AND bic.effectivity_date <= SYSDATE
                    AND (bic.disable_date >= SYSDATE OR bic.disable_date IS NULL)
                    AND msiv.organization_id = :p_organization_id
                    AND msi.organization_id = :p_organization_id
                    AND msiv.inventory_item_id = bic.component_item_id
                    AND msi.inventory_item_id = bbm.assembly_item_id
                    AND lu.lookup_type(+) = 'WIP_SUPPLY'
                    AND lu.lookup_code(+) = bic.wip_supply_type
                    AND bbm.assembly_item_id = :p_inventory_item_id
                    AND bbm.organization_id = :p_organization_id
                    AND bbm.alternate_bom_designator IS NULL
                    AND (bic.wip_supply_type <> 6 OR bic.wip_supply_type IS NULL)
                ORDER  BY item_num, component_sequence_id) QRSLT
ORDER  BY description ASC
