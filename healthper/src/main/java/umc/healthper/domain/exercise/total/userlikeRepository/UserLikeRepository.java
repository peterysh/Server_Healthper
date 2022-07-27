package umc.healthper.domain.exercise.total.userlikeRepository;

import umc.healthper.domain.exercise.total.model.GetUserLikeRes;

import java.util.List;

public interface UserLikeRepository {
    void doLike(Long exerciseId, Long userId, int sectionId);
    void undoLike(Long userLikeId);
    List<GetUserLikeRes> getLikeList(int sectionId, Long userId);
    void changePriory(Long relationId, Long target);
}
