package umc.healthper.domain.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.healthper.Section;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMadeExerciseReq {
    private String name;
    private Section section;
}
