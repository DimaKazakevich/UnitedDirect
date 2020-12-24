package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "select Sizes.size_id, Size from Products join SizeDetails on Products.product_id = SizeDetails.product_id join Sizes on SizeDetails.size_id = Sizes.size_id where Products.product_id = ?1", nativeQuery = true)
    List<Size> findAllByProductId(Integer productId);
}
