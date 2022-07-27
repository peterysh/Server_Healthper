package umc.healthper.domain.exercise.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.healthper.Section;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicExercise {
    private Long id;
    private String name;
    private int section_id;
}
