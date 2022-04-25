package at.htl.auction.boundary;

import at.htl.auction.control.AuctionRepository;
import at.htl.auction.control.BidRepository;
import at.htl.auction.control.UsersRepository;
import at.htl.auction.dto.AcceptDto;
import at.htl.auction.dto.BidDto;
import at.htl.auction.entity.Auction;
import at.htl.auction.entity.Bid;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

import at.htl.auction.control.BidRepository;
import at.htl.auction.entity.Bid;
import at.htl.auction.entity.Users;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.*;

@Path("bid")
public class BidResource {

    @Inject
    BidRepository bidRepository;

    @Inject
    AuctionRepository auctionRepository;

    @Inject
    UsersRepository usersRepository;

    @GET
    @Path("last-for-auction/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastBidsByAuction(@PathParam("id") long id) {
        return Response.ok(
                bidRepository.find("auction.id", id)
                        .range(0, 4)
                        .stream()
                        .map(bid -> new BidDto(
                                        bid.id,
                                        bid.amount,
                                        bid.createTs,
                                        bid.bidder
                                )
                        )
                        .toList()
        ).build();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptBid(AcceptDto newBid, @Context UriInfo uriInfo) {
        Auction auction = auctionRepository.findById(newBid.auctionId());
        Users bidder = usersRepository.findById(newBid.userId());
        Bid bid = new Bid(auction, newBid.amount(), bidder);
        bidRepository.persist(bid);
        UriBuilder uriBuilder = uriInfo
                .getAbsolutePathBuilder()
                .path(
                        String.valueOf(bid.id)
                );
        return Response
                .created(uriBuilder.build())
                .entity(new BidDto(
                        bid.id,
                        bid.amount,
                        bid.createTs,
                        bid.bidder
                ))
                .build();
    }
}
