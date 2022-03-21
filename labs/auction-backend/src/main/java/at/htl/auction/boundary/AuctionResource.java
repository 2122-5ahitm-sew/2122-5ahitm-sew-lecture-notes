package at.htl.auction.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("api/auction")
public class AuctionResource {

    @GET
    @Path("get-running")
    public Response getRunningAuctions() {
        return Response.ok().build();
    }


}
