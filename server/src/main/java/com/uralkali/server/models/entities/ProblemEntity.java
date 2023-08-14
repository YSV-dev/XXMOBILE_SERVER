package com.uralkali.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "XXMOBILE_PROBLEMS")
public class ProblemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="G_PROBLEM_S")
    @SequenceGenerator(name="G_PROBLEM_S", sequenceName="XXMOBILE_PROBLEMS_S", allocationSize=1)
    @Column(name="PROBLEM_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="ORGANIZATION_ID")
    private Long organizationId;

    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private Long createdBy;
}
