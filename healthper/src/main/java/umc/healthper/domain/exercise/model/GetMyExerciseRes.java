package umc.healthper.domain.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.healthper.Section;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMyExerciseRes {
    private Long user_made_id;
    private String name;
    private Section section;
}
