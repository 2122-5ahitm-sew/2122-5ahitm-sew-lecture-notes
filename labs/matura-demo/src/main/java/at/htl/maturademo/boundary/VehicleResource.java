package at.htl.maturademo.boundary;

import at.htl.maturademo.control.Vehicle2Repository;
import at.htl.maturademo.control.VehicleRepository;
import at.htl.maturademo.entity.Vehicle;
import at.htl.maturademo.entity.Vehicle2;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vehicle")
public class VehicleResource {

    @Inject
    VehicleRepository vehicleRepository;

    @Inject
    Vehicle2Repository vehicle2Repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response
                .ok(
                        vehicleRepository.listAll()
                ).build();
    }

    @GET
    @Path("v2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll2() {
        // Hole alle Vehicles aus DB
        List<Vehicle> vehicles = vehicleRepository.listAll();

        JsonArrayBuilder vehiclesArrayBuilder = Json.createArrayBuilder();

        // durchlaufe die Liste
        for (Vehicle vehicle : vehicles) {
            vehiclesArrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", vehicle.getId())
                            .add("brand", vehicle.getBrand())
                            .add("model", vehicle.getModel())
                            .add("motor", Json.createObjectBuilder()
                                    .add("ccm", vehicle.getCcm())
                                    .add("hp", vehicle.getHp())
                                    .build()
                            ).build());
        }

        return Response.ok(vehiclesArrayBuilder
                .build()
                .toString()
        ).build();

    }


    @GET
    @Path("v3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll3() {
        return Response.ok(
                vehicle2Repository.listAll()
        ).build();
    }
}
