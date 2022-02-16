package at.htl;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject hello() {

        JsonObject susi = Json
                .createObjectBuilder()
                .add("name", "Susi")
                .add("city", "Leonding")
                .build();


        return susi;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(JsonValue json) {
        if (json.getValueType().equals(JsonValue.ValueType.ARRAY)) {
            return Response.ok("Array").build();
        }
        return Response
                //.ok("kein Array")
                .ok()
                .header("comment","kein Array")
                .build();
    }
}