package at.htl.maturademo.control;

import at.htl.maturademo.entity.Vehicle2;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Vehicle2Repository implements PanacheRepository<Vehicle2> {
}
