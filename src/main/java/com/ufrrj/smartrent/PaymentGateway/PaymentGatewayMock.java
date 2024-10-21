package com.ufrrj.smartrent.PaymentGateway;

import com.ufrrj.smartrent.payment.model.Charge;
import com.ufrrj.smartrent.payment.model.Transaction;
import com.ufrrj.smartrent.payment.service.PaymentGateway;
import org.springframework.stereotype.Component;

import java.util.*;

/*
* Mock de um gateway de pagamento onde exite mais de 70% de uma transação ter sucesso.
*/
@Component
public class PaymentGatewayMock implements PaymentGateway {

    private final Map<String, String> mapResponse;

    private final List<String> codesToRetry;

    public PaymentGatewayMock() {
        mapResponse = new HashMap<>();

        mapResponse.put("00", "transact approved");
        mapResponse.put("14", "invalid number card");
        mapResponse.put("33", "expired card");
        mapResponse.put("51", "insufficient funds");

        codesToRetry = List.of("51");
    }

    @Override
    public Transaction pay(Charge charge) {
        var entry = getRandomEntry();
        var success = entry.getKey().equals("00");

        var retry = codesToRetry.contains(entry.getKey()) && !success;

        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .code(entry.getKey())
                .message(entry.getValue())
                .success(success)
                .retry(retry)
                .build();
    }

    private Map.Entry<String, String> getRandomEntry() {
        var random = new Random();

        var entries = new ArrayList<>(mapResponse.entrySet());

        if (random.nextDouble() >= 0.7) {
            return entries.getFirst();
        } else {
            return entries.get(random.nextInt(entries.size()));
        }
    }

}
