package umc.dummy.domain.exercise.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicExercise {
    private Long id;
    private String name;
    private int section_id;
}
