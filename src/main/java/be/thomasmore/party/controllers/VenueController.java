package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";

        //Venue venue = new Venue("Boesj", "https://www.facebook.com/boesjkammeree/", 100, false, true, false, false, "Mechelen", 1);
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        //noinspection OptionalIsPresent
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
        }
        return "venuedetails";
    }
    @GetMapping("/venuelist")
    public String venuelist(Model model){
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues",allVenues);

        return "venuelist";
    }
    @GetMapping("/venuelist/outdoor/{outdoor}")
    public String venuelistOutdoorYes(Model model, @PathVariable Boolean outdoor){

        if (outdoor==true){
            Iterable<Venue> venues = venueRepository.findByOutdoor(true);
            model.addAttribute("venuesOutdoor", venues);
            return "venuelist";
        }

        else {
            Iterable<Venue> venues = venueRepository.findByOutdoor(false);
            model.addAttribute("venuesOutdoor", venues);
            return "venuelist";
        }

    }

}
