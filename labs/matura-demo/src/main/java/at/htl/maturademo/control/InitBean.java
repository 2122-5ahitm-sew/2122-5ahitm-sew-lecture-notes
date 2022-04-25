package at.htl.maturademo.control;

import at.htl.maturademo.entity.Vehicle;
import at.htl.maturademo.entity.Vehicle2;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    VehicleRepository vehicleRepository;

    @Inject
    Vehicle2Repository vehicle2Repository;

    @Transactional
    void init(@Observes StartupEvent event) {

        vehicleRepository.persist(new Vehicle("VW", "Kaefer", 1400, 34));
        vehicleRepository.persist(new Vehicle("Opel", "Kapitaen", 1200, 30));
        vehicleRepository.persist(new Vehicle("Fiat", "Tipo", 1400, 100));
        vehicleRepository.persist(new Vehicle("BMW", "3", 2000, 150));
        vehicleRepository.persist(new Vehicle("VW", "Polo", 1200, 60));

        vehicle2Repository.persist(new Vehicle2("VW", "Kaefer", 1400, 34));
        vehicle2Repository.persist(new Vehicle2("Opel", "Kapitaen", 1200, 30));
        vehicle2Repository.persist(new Vehicle2("Fiat", "Tipo", 1400, 100));
        vehicle2Repository.persist(new Vehicle2("BMW", "3", 2000, 150));
        vehicle2Repository.persist(new Vehicle2("VW", "Polo", 1200, 60));

    }

}
