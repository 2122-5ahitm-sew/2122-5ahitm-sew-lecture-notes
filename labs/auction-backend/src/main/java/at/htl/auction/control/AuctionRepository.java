package at.htl.auction.control;

import at.htl.auction.entity.Auction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuctionRepository implements PanacheRepository<Auction> {
}
