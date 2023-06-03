package pl.sda.zdjavapol137.mvcspringquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.zdjavapol137.mvcspringquiz.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
