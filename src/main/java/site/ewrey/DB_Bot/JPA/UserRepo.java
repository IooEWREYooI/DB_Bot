package site.ewrey.DB_Bot.JPA;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    boolean existsByChatId(Long chatId);
    UserEntity findByChatId(Long chatId);

}
