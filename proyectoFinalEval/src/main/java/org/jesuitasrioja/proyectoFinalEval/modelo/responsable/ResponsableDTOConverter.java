package org.jesuitasrioja.proyectoFinalEval.modelo.responsable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponsableDTOConverter {

	@Autowired
	private final ModelMapper modelMapper;

	public ResponsableDTOConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ResponsableDTO convertResponsableToResponsableDTO(Responsable responsable) {

		ResponsableDTO dto = modelMapper.map(responsable, ResponsableDTO.class);

		return dto;
	}

	public ResponsableDTO2 convertResponsableToResponsableDTO2(Responsable responsable) {

		ResponsableDTO2 dto = modelMapper.map(responsable, ResponsableDTO2.class);

		return dto;
	}

}
