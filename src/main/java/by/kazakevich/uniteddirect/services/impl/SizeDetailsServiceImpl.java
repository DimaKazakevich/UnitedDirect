package by.kazakevich.uniteddirect.services.impl;

import by.kazakevich.uniteddirect.domain.Order;
import by.kazakevich.uniteddirect.domain.SizeDetails;
import by.kazakevich.uniteddirect.repository.SizeDetailsRepository;
import by.kazakevich.uniteddirect.services.SizeDetailsService;

public class SizeDetailsServiceImpl implements SizeDetailsService {
    private final SizeDetailsRepository detailsRepository;

    public SizeDetailsServiceImpl(SizeDetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    @Override
    public <S extends SizeDetails> S save(S s) {
        return detailsRepository.save(s);
    }
}
