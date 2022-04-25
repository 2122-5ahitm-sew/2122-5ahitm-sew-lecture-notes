package at.htl.auction.dto;

public record AcceptDto(
        Long auctionId,
        Long userId,
        Double amount
) {
}
