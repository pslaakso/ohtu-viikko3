package ohtu.mini.service;

import java.util.List;
import ohtu.mini.domain.SampleObject;
import ohtu.mini.repository.SampleObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleObjectServiceImpl implements SampleObjectService{

	@Autowired
	SampleObjectRepository sampleObjectRepository;

	@Override
	public List<SampleObject> list() {
		return sampleObjectRepository.findAll();
	}

}
