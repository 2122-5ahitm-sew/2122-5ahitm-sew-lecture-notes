package at.htl.auction.dto;

import at.htl.auction.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BidDto(
        Long id,
        Double amount,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        Users user
) { }
