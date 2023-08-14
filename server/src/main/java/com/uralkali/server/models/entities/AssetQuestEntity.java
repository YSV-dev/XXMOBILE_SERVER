package com.uralkali.server.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Table(name = "XXEAM.XXMOBILE_ASSET_QUESTS")
public class AssetQuestEntity implements Serializable {

    @Column(name = "asset_quest_id")
    private String assetQuestId;
    
    @Column(name = "instance_id")
    private Long instanceId;

    @Column(name = "quest_id")
    private Long questId;

    @Column(name = "organization_id")
    private Long organizationId;    

    public String getAssetQuestId() {
        return assetQuestId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public Long getQuestId() {
        return this.questId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
    
}
