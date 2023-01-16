package com.eventapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventapp.models.Event;
import com.eventapp.models.Guests;
import com.eventapp.repository.EventRepository;
import com.eventapp.repository.GuestsRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository er;
	
	@Autowired
	private GuestsRepository gr;
	@RequestMapping(value = "/registerEvent", method= RequestMethod.GET)
	public String form() {
		return "event/eventForm";
	}
	
	@RequestMapping(value = "/registerEvent", method= RequestMethod.POST)
	public String form(Event event) {
		er.save(event);
		return "event/eventForm";
	}
	
	@RequestMapping("/events")
	public ModelAndView eventList() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> event = er.findAll();
		mv.addObject("event",event);
		return mv;
	}
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable("code") long code) {
		Event event = er.findByCode(code);
		ModelAndView mv = new ModelAndView("event/eventDetails");
		mv.addObject("event",event);
		
		Iterable<Guests> guests = gr.findByEvent(event);
		mv.addObject("guests", guests);
		return mv;
	}
	
	@RequestMapping("/deleteEvent")
	public String deleteEvent(long code) {
		Event event = er.findByCode(code);
		er.delete(event);
		return "redirect:/events";
	}
	
	@RequestMapping(value = "/{code}", method = RequestMethod.POST)
	public String eventDetailsPost(@PathVariable("code") long code, @Valid Guests guest, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("message", "Check the data fields!");
			return "redirect:/{code}";
		}
		Event event = er.findByCode(code);
		guest.setEvent(event);
		gr.save(guest);
		attributes.addFlashAttribute("message", "The user has been added successfully!");
		return "redirect:/{code}";
	}
	
	@RequestMapping("/deleteGuest")
	public String deleteGuest(String rg) {
		Guests guest = gr.findByRg(rg);
		gr.delete(guest);
		
		Event event = guest.getEvent();
		long longcode = event.getCode();
		String code = "" + longcode;
		
		return "redirect:/" + code;
	}

}
