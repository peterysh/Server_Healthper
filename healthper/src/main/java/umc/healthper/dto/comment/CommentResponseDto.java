package umc.healthper.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.healthper.domain.comment.Comment;
import umc.healthper.domain.comment.CommentStatus;
import umc.healthper.domain.member.Member;
import umc.healthper.dto.member.MemberInfoDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private MemberInfoDto writer;
    private String content;
    private Integer likeCount;
    private CommentStatus status;
    private List<NestedCommentResponseDto> children = new ArrayList<>();
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        Member writer = comment.getMember();
        this.setWriter(new MemberInfoDto(writer.getId(), writer.getNickname(), writer.getStatus()));
        this.setCommentId(comment.getId());
        this.setContent(comment.getContent());
        this.setLikeCount(comment.getLikes().size());
        this.setStatus(comment.getStatus());
        this.setCreatedAt(comment.getCreatedAt());
        comment.getChildren().forEach(child -> this.getChildren().add(new NestedCommentResponseDto(child)));
    }
}
