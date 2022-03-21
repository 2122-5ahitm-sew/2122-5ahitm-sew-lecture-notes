package at.htl.auction.control;

import at.htl.auction.entity.Auction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AuctionRepository implements PanacheRepository<Auction> {


    public List<Auction> findRunningAuctions(LocalDateTime ts) {

//        return find("endTs > CURRENT_TIMESTAMP and startTs < CURRENT_TIMESTAMP ")
//                .list();

        return find("endTs > ?1 and startTs < ?1 ", ts)
                .list();

    }

}
