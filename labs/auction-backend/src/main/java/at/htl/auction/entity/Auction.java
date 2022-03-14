package at.htl.auction.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "auctionSeq", sequenceName = "AUCTION_SEQ", initialValue = 2003, allocationSize = 1)
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auctionSeq")
    private Long id;

    @Column(name = "START_TS")
    private LocalDateTime startTs;
    @Column(name = "END_TS")
    private LocalDateTime endTs;
    @Column(name = "START_PRICE")
    private Double startPrice;

    @ManyToOne
    private Users seller;

    @ManyToOne
    private Product product;


    public Auction() {
    }

    public Auction(LocalDateTime startTs, LocalDateTime endTs, Double startPrice, Users seller, Product product) {
        this.startTs = startTs;
        this.endTs = endTs;
        this.startPrice = startPrice;
        this.seller = seller;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTs() {
        return startTs;
    }

    public void setStartTs(LocalDateTime startTs) {
        this.startTs = startTs;
    }

    public LocalDateTime getEndTs() {
        return endTs;
    }

    public void setEndTs(LocalDateTime endTs) {
        this.endTs = endTs;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Users getSeller() {
        return seller;
    }

    public void setSeller(Users seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
