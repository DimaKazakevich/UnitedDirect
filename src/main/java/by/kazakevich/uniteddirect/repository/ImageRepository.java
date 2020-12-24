package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
