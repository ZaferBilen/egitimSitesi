package com.egitim.egitimSitesi.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
}
