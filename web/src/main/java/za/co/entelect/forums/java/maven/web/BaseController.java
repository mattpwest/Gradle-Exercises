package za.co.entelect.forums.java.maven.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Gantry;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        Map<Vehicle, BigDecimal> vehicleBills = getVehicleBills();

        model.addAttribute("vehicles", vehicleBills.keySet());
        model.addAttribute("bills", vehicleBills);
        logger.debug("Showing vehicle bills : " + vehicleBills.toString());

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;
    }

    private Map<Vehicle, BigDecimal> getVehicleBills() {
        List<Gantry> gantries = App.getGantryList();
        Map<Vehicle, BigDecimal> vehicleBill = new HashMap<>();

        for (Gantry gantry : gantries) {
            for (Vehicle vehicle : gantry.getVehicles()) {
                if (!vehicleBill.containsKey(vehicle)) {
                    vehicleBill.put(vehicle, new BigDecimal(gantry.getToll()));
                } else {
                    BigDecimal value = vehicleBill.get(vehicle);
                    vehicleBill.put(vehicle, value.add(new BigDecimal(gantry.getToll())));
                }
            }
        }

        return vehicleBill;
    }
}
