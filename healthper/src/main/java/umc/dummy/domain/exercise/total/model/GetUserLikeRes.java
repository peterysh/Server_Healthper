package umc.dummy.domain.exercise.total.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserLikeRes {
    private Long user_like_id;
    private Long exercise_id;
    private Long priori;
    private int section_id;
}
