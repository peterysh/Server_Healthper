package umc.healthper.domain.exercise.basic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.healthper.Section;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBasicExerciseReq {
    private String name;
    private Section section;
}
