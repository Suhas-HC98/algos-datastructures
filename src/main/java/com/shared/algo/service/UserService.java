package com.shared.algo.service;

import com.shared.algo.model.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
public final class UserService implements UserDetailsService {

    public final static String AWT = "AWT";
    public final static String WWT = "WWT";
    public final static String RWT = "RWT";
    public final static String INVALID_WT = "INVALID";

    @Autowired
    private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Logic to get the user form the Database
        return new User("suhas", "$2a$10$wdrTYak5lHz9uf9UrrY3WO7KXf9b.NUThyY1Bv3aoawyj7fb59VXm", new ArrayList<>());
    }


    public String getSigmaCode(String referenceNumber) {
        ClientDetails clientDetails = clientService.getClientDetails(referenceNumber);
        List<Map.Entry<String, ClientDetails>> entries = processCombination(clientDetails);

        if (entries.isEmpty()) {
            return INVALID_WT;
        }
        return entries.get(0).getKey();
    }

    private List<Map.Entry<String, ClientDetails>> processCombination(ClientDetails clientDetails) {
        BiPredicate<String, String> bi = (segment, subSegment) ->
                segment.equalsIgnoreCase(clientDetails.segment()) && subSegment.equalsIgnoreCase(clientDetails.subSegment());

        return dataForProcessing().entrySet().stream()
                .filter(entries -> bi.test(entries.getValue().segment(), entries.getValue().subSegment())).collect(Collectors.toList());
    }

    private Map<String, ClientDetails> dataForProcessing() {
        Map<String, ClientDetails> data = new LinkedHashMap<>();
        data.put(AWT, new ClientDetails("A1", "A2"));
        data.put(WWT, new ClientDetails("W1", "W2"));
        data.put(RWT, new ClientDetails("R1", "R2"));
        return data;
    }

}
