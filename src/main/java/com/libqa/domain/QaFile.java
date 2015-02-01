package com.libqa.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by yong on 15. 2. 1..
 */
@Entity
@Data
public class QaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer fileId;

    @Column(nullable = false)
    private Integer qaId;

    @Column(nullable = true)
    private String realName;

    @Column(nullable = true)
    private String saveName;

    @Column(nullable = true)
    private String path;

    @Column(nullable = true)
    private Integer fileSize;

    @Column(nullable = true)
    private String fileType;

    @Column(nullable = false, columnDefinition="TINYINT(1) DEFAULT 0")
    private String isDeleted;

    @Column(nullable = true)
    private Integer userId;
}