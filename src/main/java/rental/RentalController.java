package rental;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rental.dto.CarRentDto;
import rental.jpa.CarRent;
import rental.jpa.CarRentRepo;
import rental.jpa.CarRepo;
import rental.util.Constant;
import rental.util.OverbookedException;
import rental.util.RentUtil;

@RestController
public class RentalController {
    private Constant constant = new Constant();
    @Autowired
    CarRentRepo carRentRepo;

    @Autowired
    CarRepo carRepo;

    private synchronized int getId() {
        // check db existing id, and generate a unique one.
        Set<Integer> ids = new HashSet<>();
        ids.addAll(carRentRepo.findAllId());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (!ids.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    @GetMapping(value = "/find")
    public List<CarRent> find(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "type", required = false) String type) {
        if (id != null) {
            return carRentRepo.findById(id);
        }
        if (type != null) {
            return carRentRepo.findByType(type);
        }
        return carRentRepo.findAll();
    }

    @PostMapping(value = "/add")
    public synchronized String add(@RequestBody CarRentDto crdto) {
        // check if: car.available > count(rows of that perticular cartype)
        String cartype = crdto.getCartype();
        List<CarRent> crlist = carRentRepo.findByType(cartype);
        if (RentUtil.isOverbooked(crlist, carRepo.findByType(cartype).get(0).getAvailable())) {
            throw new OverbookedException(String.format("\'%s\' is not available.", cartype));
        }
        CarRent cr = new CarRent(0, cartype, crdto.getStartdt(), crdto.getDays());
        int id = getId();
        cr.setId(id);
        int n = carRentRepo.insert(cr);
        return String.format("\'%s\' reserved successfully with ID: %d.", cartype, id);
    }

    @PutMapping(value = "/change")
    public synchronized String change(@RequestBody CarRentDto cr, @RequestParam(value = "id") Integer id) {
        String cartype = cr.getCartype();
        List<CarRent> crlist = carRentRepo.findByType(cartype);
        if (RentUtil.isOverbooked(crlist, carRepo.findByType(cartype).get(0).getAvailable())) {
            throw new OverbookedException(String.format("\'%s\' is not available.", cartype));
        }
        int n = carRentRepo.update(new CarRent(id, cartype, cr.getStartdt(), cr.getDays()));
        return String.format("Reservation \'%d\' was updated successfully!", id);
    }

    @DeleteMapping(value = "/delete")
    public synchronized int delete(@RequestParam(value = "id") Long id) {
        return carRentRepo.deleteById(id);
    }
}
