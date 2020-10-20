package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "select Size from Products join SizeDetails on Products.Id = SizeDetails.ProductId join Sizes on SizeDetails.SizeId = Sizes.Id where Products.Id = ?1", nativeQuery = true)
    List<String> findAllByProductId(Integer productId);
}
