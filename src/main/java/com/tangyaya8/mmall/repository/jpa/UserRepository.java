package com.tangyaya8.mmall.repository.jpa;

import com.tangyaya8.mmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:14
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    @Query(value = "SELECT question FROM mmall_user WHERE user_name = ?1", nativeQuery = true)
    String selectQuestionByUserName(String userName);

    long countByUserNameAndQuestionAndAnswer(String userName, String question, String answer);

    @Query("UPDATE User  SET password = ?1 WHERE userName = ?2")
    long updatePassword(String password, String userName);

    long countByPasswordAndId(String newPassword, long userId);

    @Query("UPDATE User SET password = ?1 WHERE id = ?2")
    long resetPassword(String newPassword, long id);

    @Query(value = "SELECT count(1) FROM mmall_user WHERE email=?1 AND id=?2", nativeQuery = true)
    long existsByEmailAndId(String email, long id);
}
