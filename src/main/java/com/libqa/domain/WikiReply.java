package com.libqa.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by songanji on 2015. 2. 8..
 */
@Entity
@Data
public class WikiReply implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer replyId;

    @ManyToOne
    @JoinColumn(referencedColumnName="wikiId",  nullable = false)
    private Wiki wiki;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String contents;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 40)
    private String userNick;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;



}
