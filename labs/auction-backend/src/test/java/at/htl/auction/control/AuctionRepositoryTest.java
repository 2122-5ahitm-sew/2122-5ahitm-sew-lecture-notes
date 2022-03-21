package at.htl.auction.control;

import at.htl.auction.entity.Auction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AuctionRepositoryTest {

    @Inject
    AuctionRepository auctionRepository;

    @Test
    void testFindRunningAuctions() {
        List<Auction> auctions = auctionRepository.findRunningAuctions(LocalDateTime.now());
        auctions.forEach(System.out::println);
    }


}