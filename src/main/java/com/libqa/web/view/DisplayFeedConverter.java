package com.libqa.web.view;

import com.google.common.collect.Lists;
import com.libqa.application.util.LoggedUser;
import com.libqa.web.domain.Feed;
import com.libqa.web.domain.FeedReply;
import com.libqa.web.domain.User;
import com.libqa.web.service.FeedActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayFeedConverter {
    @Autowired
    private LoggedUser loggedUser;
    @Autowired
    private FeedActionService feedActionService;

    public DisplayFeedAction toDisplayFeedAction(Integer actionCount, boolean hasViewer) {
        return new DisplayFeedAction(actionCount, hasViewer);
    }

    public List<DisplayFeed> toDisplayFeeds(List<Feed> feeds) {
        User user = loggedUser.getDummyUser(); // TODO fix to realuser
        List<DisplayFeed> displayFeeds = Lists.newArrayList();
        for (Feed feed : feeds) {
            DisplayFeedAction likedFeedAction = toDisplayFeedAction(feed.getLikeCount(), feedActionService.hasLike(feed, user));
            DisplayFeedAction claimedFeedAction = toDisplayFeedAction(feed.getClaimCount(), feedActionService.hasClaim(feed, user));
            displayFeeds.add(new DisplayFeed(feed, likedFeedAction, claimedFeedAction, toDisplayFeedReplies(feed.getFeedReplies())));
        }
        return displayFeeds;
    }

    private List<DisplayFeedReply> toDisplayFeedReplies(List<FeedReply> feedReplies) {
        User user = loggedUser.getDummyUser(); // TODO fix to realuser
        List<DisplayFeedReply> displayFeedReplies = Lists.newArrayList();
        for (FeedReply feedReply : feedReplies) {
            DisplayFeedAction likedFeedAction = toDisplayFeedAction(feedReply.getLikeCount(), feedActionService.hasLike(feedReply, user));
            DisplayFeedAction claimedFeedAction = toDisplayFeedAction(feedReply.getClaimCount(), feedActionService.hasClaim(feedReply, user));
            displayFeedReplies.add(new DisplayFeedReply(feedReply, likedFeedAction, claimedFeedAction));
        }
        return displayFeedReplies;
    }

}