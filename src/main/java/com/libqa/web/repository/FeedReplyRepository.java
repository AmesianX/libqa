package com.libqa.web.repository;

import com.libqa.web.domain.FeedReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedReplyRepository extends JpaRepository<FeedReply, Long> {
    List<FeedReply> findByFeedFeedId(Long feedId);
}
