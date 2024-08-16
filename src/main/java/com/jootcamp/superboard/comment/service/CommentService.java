package com.jootcamp.superboard.comment.service;

import com.jootcamp.superboard.comment.repository.CommentRepository;
import com.jootcamp.superboard.comment.repository.entity.CommentEntity;
import com.jootcamp.superboard.comment.repository.exception.CommentNotFoundException;
import com.jootcamp.superboard.comment.service.dto.Comment;
import com.jootcamp.superboard.comment.service.dto.CommentPage;
import com.jootcamp.superboard.comment.service.dto.UpsertComment;
import com.jootcamp.superboard.common.dto.PageMetadata;
import com.jootcamp.superboard.common.exception.UnauthorizedException;
import com.jootcamp.superboard.post.repository.PostRepository;
import com.jootcamp.superboard.post.repository.entity.PostEntity;
import com.jootcamp.superboard.post.repository.execption.PostNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    public long addComments(UpsertComment upsertComment){
        existPost(upsertComment.getPostId());
        CommentEntity comment = commentRepository.save(upsertComment.toEntity());
        return comment.getId();
    }

    // 댓글 전체 조회
    public CommentPage<Comment> getComments(long postId, Pageable pageable){
        /*
        @ comments : Page<CommentEntity>
        @ existComments: List<CommentEntity> 작성자를 추가하기 위해 comments에서 CommentEntity만 추출
         */

        existPost(postId);

        Page<CommentEntity> comments = commentRepository.findAllByPostIdAndIsDeletedIsFalse(postId, pageable);

        if(comments.getContent().isEmpty()) // 댓글이 존재하지 않음
            return new CommentPage<>(new ArrayList<>(), new PageMetadata());

        PageMetadata metadata = PageMetadata.of(pageable, comments);

        List<Comment> existComments = comments.getContent().stream()
                .map(Comment::from).toList();

        return new CommentPage<>(existComments, metadata);

    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(long commentId, long postId, long userId){
        existPost(postId);

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        authorized(userId, comment);

        comment.setDeleted(false);

    }

    // 댓글 수정
    @Transactional
    public void updateComment(UpsertComment upsertComment, long commentId){
        // 1. 해당 게시글 존재 하느가?
        existPost(upsertComment.getPostId());

        // 2. 해당 댓글이 존재하느가?
        CommentEntity comment = commentRepository.findByIdAndIsDeletedIsFalse(commentId)
                .orElseThrow(()->new CommentNotFoundException(commentId));

        // 3. 해당하는 사용자가 권한이 있는가?
        authorized(upsertComment.getUserId(), comment);

        // 4. 수정
        comment.updateContent(upsertComment.getContent());

    }

    private void existPost(long postId){

        // 존재하지 않은 게시글을 조회할 때
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if(post.isDeleted()) // 삭제된 게시글을 조회할 때
            throw new PostNotFoundException(postId);

    }

    private void authorized(long userId, CommentEntity comment){
        if(comment.getUserId()!=userId)
            throw new UnauthorizedException("권한이 없습니다");
    }

}
