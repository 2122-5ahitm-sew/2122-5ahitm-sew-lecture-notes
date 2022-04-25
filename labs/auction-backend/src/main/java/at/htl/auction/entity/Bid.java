package at.htl.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "bidSeq",
        sequenceName = "BIS_SEQ",
        initialValue = 10003,
        allocationSize = 1
)
public class Bid extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bidSeq")
    public Long id;

    public Double amount;

    public Bid() {
        createTs = LocalDateTime.now();
    }

    public Bid(Auction auction, Double amount, Users bidder) {
        this();
        this.amount = amount;
        this.bidder = bidder;
        this.auction = auction;
    }

    @Column(name = "CREATE_TS")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createTs;

    @ManyToOne
    @JoinColumn(name = "BIDDER_ID")
    public Users bidder;

    @ManyToOne
    @JoinColumn(name = "AUCTION_ID")
    public Auction auction;

}
