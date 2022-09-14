package umc.healthper.dto.completeExercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.healthper.domain.completeExercise.CompleteExerciseInfo;
import umc.healthper.global.collectionValid.CustomValid;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data @Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PostExercises {
    @NotNull
    private String exerciseName;
    @Min(0)
    @Max(9)
    private int sectionId;
    @Min(1)
    private Long exerciseTime;
    @NotEmpty
    @CustomValid
    private List<CompleteExerciseInfo> details;
}
