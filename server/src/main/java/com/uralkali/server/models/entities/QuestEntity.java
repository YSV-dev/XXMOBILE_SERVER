package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author brzsmg
 */
@Entity
@Table(name = "XXEAM.XXMOBILE_QUEST")
public class QuestEntity implements Serializable {

    @Id
    @Column(name = "quest_id")
    private Long quest_id;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "quest_name")
    private String quest_name;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "created_by")
    private Long created_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updated_at;
    
    @Column(name = "updated_by")
    private Long updated_by;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removed_at;
    
    @Column(name = "removed_by")
    private Long removed_by;

    public Long getQuestId() {
        return this.quest_id;
    }

    public Long getOrganizationId() {
        return organization_id;
    }

    public String getQuestName() {
        return this.quest_name;
    }

    public void setQuestId(Long quest_id) {
        this.quest_id = quest_id;
    }

    public void setOrganizationId(Long organization_id) {
        this.organization_id = organization_id;
    }

    public void setQuestName(String quest_name) {
        this.quest_name = quest_name;
    }
    
    
    
}
