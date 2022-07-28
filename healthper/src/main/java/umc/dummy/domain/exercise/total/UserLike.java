package umc.dummy.domain.exercise.total;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserLike {
    private Long user_like_id;
    private Long exerciseId;
    private Long userId;
    private Boolean like;
    private Long priori;
    private int section_id;
}
