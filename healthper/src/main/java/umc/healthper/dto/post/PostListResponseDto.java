package umc.healthper.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListResponseDto<T> {
    private T content;
}
