package com.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.models.dto.JobDto;
import com.epam.service.JobPortalServiceImplementation;

@Controller
public class HomeController {
	@Autowired
	private JobPortalServiceImplementation jobPortalServiceImplementation;
	@RequestMapping("/addJob")
	public String addJob(JobDto jobUiModel,Model model) {
		jobPortalServiceImplementation.addJob(jobUiModel);
		model.addAttribute("list",jobPortalServiceImplementation.getAllJobs());
		return "printJobs";
	}
	@RequestMapping("/addJobView")
	public String addJobView() {
		return "AddJob";
	}
	@RequestMapping("/")
	public String getAllJobs(Model model) {
		model.addAttribute("list",jobPortalServiceImplementation.getAllJobs());
		return "printJobs";
	}
	@RequestMapping("/updateJob/{jobId}")
	public String updateJobView(@PathVariable int jobId,Model model) {
		model.addAttribute("job", jobPortalServiceImplementation.getJobById(jobId));
		return "updateJob";
	}
	@RequestMapping("/updateJobSave")
	public String updateJobSave(JobDto jobUiModel,Model model) {
		jobPortalServiceImplementation.updateJob(jobUiModel);
		return "redirect:/";
	}
	@RequestMapping("/deleteJob/{jobId}")
	public String deleteJob(@PathVariable int jobId,Model model) {
		jobPortalServiceImplementation.deleteJob(jobId);
		return "redirect:/";
	}
}
