package at.htl.auction.dto;

import java.time.LocalDateTime;

public record AuctionDto(
        Long id,
        Integer bids,
        LocalDateTime endTS,
        String name,
        Double price
        ) {
}
