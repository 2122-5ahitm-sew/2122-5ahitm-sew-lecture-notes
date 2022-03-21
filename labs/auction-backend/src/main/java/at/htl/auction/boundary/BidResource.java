package at.htl.auction.boundary;

import at.htl.auction.control.BidRepository;
import at.htl.auction.entity.Bid;

import javax.inject.Inject;
import javax.ws.rs.*;

import at.htl.auction.control.BidRepository;
import at.htl.auction.entity.Bid;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("bid")
public class BidResource {

    @Inject
    BidRepository bidRepository;

    @GET
    @Path("last-for-auction/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastBidsByAuction(@PathParam("id") long id) {
        return Response.ok(
                bidRepository.find("auction.id", id)
                .stream()
                .limit(5)
                .toList()
        ).build();
    }
}
