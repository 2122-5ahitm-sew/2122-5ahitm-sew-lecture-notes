package at.htl.auction.boundary;

import at.htl.auction.control.AuctionRepository;
import at.htl.auction.control.BidRepository;
import at.htl.auction.dto.AuctionDto;
import at.htl.auction.entity.Auction;
import at.htl.auction.entity.Bid;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("auction")
public class AuctionResource {

    @Inject
    AuctionRepository auctionRepository;

    @Inject
    BidRepository bidRepository;

    @GET
    @Path("get-running")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRunningAuctions() {
        List<AuctionDto> dtoList = auctionRepository
                .findRunningAuctions(LocalDateTime.now())
                .stream()
                .map(this::createAuctionDto)
                .toList();
        return Response.ok(
                dtoList
        ).build();
    }

    private AuctionDto createAuctionDto(Auction auction) {
        List<Bid> bids = bidRepository
                .find("auction.id", auction.getId())
                .list();

        int noOfBids = bids.size();
        double maxBidAmount = bids
                .stream()
                .mapToDouble(b -> b.amount)
                .max()
                .orElse(auction.getStartPrice());

        return new AuctionDto(
                auction.getId(),
                noOfBids,
                auction.getEndTs(),
                auction.getProduct().getName(),
                maxBidAmount
        );
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        Auction auction = auctionRepository.findById(id);

        if (auction != null) {
            return Response.ok(auction).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
