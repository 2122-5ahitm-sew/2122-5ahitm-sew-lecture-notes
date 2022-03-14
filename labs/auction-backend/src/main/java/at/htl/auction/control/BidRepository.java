package at.htl.auction.control;

import at.htl.auction.entity.Bid;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BidRepository implements PanacheRepository<Bid> {
}
