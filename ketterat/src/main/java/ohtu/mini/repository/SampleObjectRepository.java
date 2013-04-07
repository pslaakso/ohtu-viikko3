package ohtu.mini.repository;

import java.util.List;
import ohtu.mini.domain.SampleObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleObjectRepository extends JpaRepository<SampleObject, Long>{

	public SampleObject findById(long id);
	public List<SampleObject> findByAwesome(boolean awesome);

}
