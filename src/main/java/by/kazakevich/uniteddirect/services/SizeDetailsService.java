package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.SizeDetails;

public interface SizeDetailsService {
    <S extends SizeDetails> S save(S s);
}
