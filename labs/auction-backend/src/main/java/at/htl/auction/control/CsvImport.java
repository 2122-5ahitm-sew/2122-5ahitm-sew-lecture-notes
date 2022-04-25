package at.htl.auction.control;


import at.htl.auction.entity.Auction;
import at.htl.auction.entity.Product;
import at.htl.auction.entity.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequestScoped
public class CsvImport {
    static final String SEPARATOR = "[;,]";

    @Inject
    UsersRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    AuctionRepository auctionRepository;


    public int importCsv(long userid, String data) {
        Users user = userRepository.findById(userid);
        if (user == null) {
            return 0;
        }

        AtomicInteger count = new AtomicInteger(0);

        List<String> columns = Arrays.stream(data.lines()
                .findFirst()
                .orElse("")
                .split(SEPARATOR))
                .map(String::toUpperCase)
                .map(s -> s.startsWith("\"") ? s.substring(1, s.length() - 1) : s)
                .collect(Collectors.toList());

        data.lines()
                .skip(1)
                .map(s -> s.split(SEPARATOR))
                .forEach(strings -> {
                    String[] vals = Arrays.stream(strings)
                            .map(s -> s.startsWith("\"") ? s.substring(1, s.length() - 1) : s)
                            .toArray(String[]::new);
                    persist(user, columns, vals);
                    count.incrementAndGet();
                });

        return count.intValue();
    }

    @Transactional
    void persist(Users user, List<String> columns, String[] s) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", s[columns.indexOf("PRODUCTNAME")]);
        params.put("description", s[columns.indexOf("DESCRIPTION")]);

        Product product = productRepository.find("name = :name and description = :description", params).firstResult();
        if (product == null) {
            product = new Product((String) params.get("name"), (String) params.get("description"));
            productRepository.persist(product);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
        LocalDateTime start = LocalDateTime.parse(s[columns.indexOf("STARTTS")], dtf);
        LocalDateTime end = LocalDateTime.parse(s[columns.indexOf("ENDTS")], dtf);
        double price = Double.parseDouble(s[columns.indexOf("STARTINGPRICE")]);
        Auction auction = new Auction(price, start, end, product, user);
        auctionRepository.persist(auction);
    }
}
