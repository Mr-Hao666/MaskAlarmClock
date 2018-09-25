package com.yhao.service;

import com.yhao.domain.CommentMapper;
import com.yhao.domain.DiscussMapper;
import com.yhao.domain.FaceScoreMapper;
import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.Comment;
import com.yhao.domain.entity.Discuss;
import com.yhao.domain.entity.User;
import com.yhao.enums.ResultStatus;
import com.yhao.result.BaseResult;
import com.yhao.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 12:21
 **/
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private FaceScoreService faceScoreService;

    @Autowired
    private UserMapper userMapper;

    public List<Comment> findByDesc(String desc) {
        List<Comment> commentList = null;
        switch (desc) {
            case "STAR_DESC":
                commentList = commentMapper.findByTimeDesc();
                break;
            case "STAR_ASC":
                commentList = commentMapper.findByTimeAsc();
                break;
            case "TIME_DESC":
                commentList = commentMapper.findByStarDesc();
                break;
            case "TIME_ASC":
                commentList = commentMapper.findByStarAsc();
                break;
            case "COUNT_DESC":
                commentList = commentMapper.findByCountDesc();
                break;
            case "COUNT_ASC":
                commentList = commentMapper.findByCountAsc();
                break;
            default:
                break;

        }
        return commentList;
    }

    @Transactional
    public Discuss addDiscuss(Integer userId, Integer commentId, Integer star, String remark) {
        Comment comment = commentMapper.findById(commentId);
        List<Discuss> discussList = discussMapper.findByCommentId(commentId);
        User user = userMapper.findById(userId);
        Discuss discuss = new Discuss();
        discuss.setCommentId(commentId);
        discuss.setUserId(userId);
        discuss.setIcon(user.getIcon());
        discuss.setName(user.getName());
        discuss.setStar(star);
        discuss.setRemark(remark);
        discussMapper.insert(discuss);
        discussList.add(discuss);
        int count = 0;
        int star1 = 0;
        for (Discuss discuss1 : discussList) {
            star1 += discuss1.getStar();
            count++;
        }
        comment.setStar(star1/count);
        commentMapper.update(comment);
        return discuss;
    }

    @Transactional
    public Comment addComment(Integer userId, String pictrue, String data) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPicture(pictrue);
        comment.setData(data);
        faceScoreService.addStyle6(userId);
        commentMapper.insert(comment);
        return comment;
    }


}
