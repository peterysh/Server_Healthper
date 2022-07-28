package umc.dummy.domain.exercise.total.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.healthper.Section;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExerciseRes {
    private Long exercise_id;
    private String name;
    private Section section;
}
