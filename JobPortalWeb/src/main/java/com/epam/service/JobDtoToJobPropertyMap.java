package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import com.epam.models.JobSkills;
import com.epam.models.Jobs;
import com.epam.models.dto.JobDto;

public class JobDtoToJobPropertyMap extends PropertyMap<JobDto, Jobs> {
	Converter<String , List<JobSkills>> stringToList = new Converter<String , List<JobSkills>>() {
		public List<JobSkills> convert(MappingContext<String , List<JobSkills>> context) {
			String source = context.getSource();
			List<JobSkills> destination = new ArrayList<>();
			for(String skill:source.split(",")) {
				destination.add(new JobSkills(skill));
			}
			return destination;
		}
	};

	@Override
	protected void configure() {
		using(stringToList).map(source.getSkillSet()).setSkillSet(null);;
	}
}