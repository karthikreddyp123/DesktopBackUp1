package com.epam.service;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import com.epam.models.JobSkills;
import com.epam.models.Jobs;
import com.epam.models.dto.JobDto;

public class JobToJobDtoPropertyMap extends PropertyMap<Jobs,JobDto> {
	Converter<List<JobSkills>,String> listToString = new Converter<List<JobSkills>,String>() {
		public String convert(MappingContext<List<JobSkills>,String> context) {
			List<JobSkills> source = context.getSource();
			StringBuilder destination = new StringBuilder();
			for(JobSkills skill:source) {
				destination.append(skill.getSkill()+",");
			}
			if(destination.length()>0) {
				destination.deleteCharAt(destination.length()-1);
			}
			return destination.toString();
		}
	};

	@Override
	protected void configure() {
		using(listToString).map(source.getSkillSet()).setSkillSet(null);
	}
}