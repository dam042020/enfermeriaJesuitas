package org.jesuitasrioja.proyectoFinalEval.modelo.profesor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesorDTOConverter {

	@Autowired
	private final ModelMapper modelMapper;

	public ProfesorDTOConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ProfesorDTO convertProfesorToProfesorDTO(Profesor profesor) {

		ProfesorDTO dto = modelMapper.map(profesor, ProfesorDTO.class);

		return dto;
	}

	public ProfesorDTO2 convertProfesorToProfesorDTO2(Profesor profesor) {

		ProfesorDTO2 dto = modelMapper.map(profesor, ProfesorDTO2.class);

		return dto;
	}

}
