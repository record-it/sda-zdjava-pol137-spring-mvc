package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;

import java.util.OptionalLong;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Przykład zapytania w JPQL
    @Query("Select sum(c.rating) from Category as c")
    long sumOfRating();


    // Przykład zapytania natywnego SQL
    @Query(
            value = "Select sum(rating) from CATEGORY where rating > :value",
            nativeQuery = true
    )
    Long sumOfRatingGreaterThanNative(int value);
}
