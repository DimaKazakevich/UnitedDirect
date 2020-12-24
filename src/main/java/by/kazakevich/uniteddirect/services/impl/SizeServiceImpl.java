package by.kazakevich.uniteddirect.services.impl;

import by.kazakevich.uniteddirect.domain.Size;
import by.kazakevich.uniteddirect.repository.SizeRepository;
import by.kazakevich.uniteddirect.services.SizeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> findAllByProductId(Integer productId) {
        return sizeRepository.findAllByProductId(productId);
    }

    @Override
    public List<Size> findAll(Sort sort) {
        return sizeRepository.findAll(sort);
    }

    @Override
    public Page<Size> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> findAllById(Iterable<Integer> iterable) {
        return sizeRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return sizeRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        sizeRepository.deleteById(integer);
    }

    @Override
    public void delete(Size size) {
        sizeRepository.delete(size);
    }

    @Override
    public void deleteAll(Iterable<? extends Size> iterable) {
        sizeRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        sizeRepository.deleteAll();
    }

    @Override
    public <S extends Size> S save(S s) {
        return sizeRepository.save(s);
    }

    @Override
    public <S extends Size> List<S> saveAll(Iterable<S> iterable) {
        return sizeRepository.saveAll(iterable);
    }
}
