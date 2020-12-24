package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.SizeDetails;
import by.kazakevich.uniteddirect.domain.SizeDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeDetailsRepository extends JpaRepository<SizeDetails, SizeDetailsId> {
}
